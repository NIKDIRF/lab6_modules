package server;

import collection.CollectionControl;
import collection.HashSetControl;
import command.*;
import connection.ConnectionListener;
import connection.ConnectionListenerImpl;
import exception.*;
import file.*;
import io.ConsoleIO;
import io.UserIO;
import locale.ServerBundle;
import log.Log;
import request.RequestHandler;
import request.RequestHandlerImpl;
import request.RequestReader;
import request.RequestReaderImpl;
import response.Creator;
import response.ResponseCreator;
import response.ResponseSender;
import response.ResponseSenderImpl;
import studygroup.Request;
import studygroup.Response;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;
import java.util.Locale;

public class Application {
    private boolean isRunning = true;

    public void start(String address, int port, String fileName) {
        Locale.setDefault(new Locale("ru", "RU"));
        Selector selector;
        Creator creator = new ResponseCreator();
        CollectionControl routeManager = new HashSetControl(creator);
        StudyGroupReader reader = new JSONReader(fileName);
        StudyGroupWriter writer = new JSONWriter(routeManager, fileName);
        CommandHistory commandHistory = new CommandHistory();
        CommandInvoker commandInvoker = new CommandInvoker(commandHistory);

        try {
            routeManager.addStudyGroups(reader.read());
        } catch (InvalidArgumentException | StudyGroupReadException | StudyGroupBuildException e) {
            log.Log.getLogger().error(e.getMessage());
        }
        ConnectionListener connectionListener = new ConnectionListenerImpl();
        RequestReader requestReader = new RequestReaderImpl();
        RequestHandler requestHandler = new RequestHandlerImpl(commandInvoker, creator);
        ResponseSender responseSender = new ResponseSenderImpl();
        putCommands(commandInvoker, routeManager, creator, commandHistory);
        putServerCommands(commandInvoker, writer, connectionListener);
        consoleStart(commandInvoker);

        while (isRunning) {
            try {
                connectionListener.openConnection(address, port);
                try {
                    selector = connectionListener.listen();
                } catch (ClosedSelectorException e){
                    return;
                }
                Request request = requestReader.getRequest(selector);
                log.Log.getLogger().info(ServerBundle.getString("server.got_request"));
                try {
                    log.Log.getLogger().info(ServerBundle.getString("server.request_handling"));
                    Response response = requestHandler.handleRequest(request);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                } catch (CommandNotFoundException | CommandExecutionException e) {
                    Log.getLogger().error("error", e);
                    Response response = new Response(e.getMessage(), false, false);
                    responseSender.sendResponse(selector, response);
                    connectionListener.stop();
                }
                Log.getLogger().info(request.toString());
            } catch (IOException | ClassNotFoundException ioe) {
                Log.getLogger().error("error", ioe);
                try {
                    connectionListener.stop();
                } catch (IOException e) {
                    Log.getLogger().error("error", e);
                    isRunning = false;
                }
            }
        }
    }

    private void consoleStart(CommandInvoker commandInvoker) {
        Thread consoleThread = new Thread(() -> {
            UserIO userIO = new ConsoleIO();
            while(!Thread.interrupted()) {
                userIO.printUserPrompt();
                try {
                    String str = userIO.readLine();
                    commandInvoker.execute(str);
                } catch (IOException | CommandNotFoundException | CommandExecutionException ioe) {
                    userIO.printErrorMessage(ioe.getMessage());
                }
            }
        });
        consoleThread.setDaemon(true);
        consoleThread.start();
    }

    private void putCommands(CommandInvoker commandInvoker, CollectionControl manager, Creator creator, CommandHistory commandHistory) {
        commandInvoker.addCommand("help", new HelpCommand(false, creator));
        commandInvoker.addCommand("info", new InfoCommand(manager, false));
        commandInvoker.addCommand("clear", new ClearCommand(manager, false));
        commandInvoker.addCommand("show", new ShowCommand(manager, false));
        commandInvoker.addCommand("remove_by_id", new RemoveByIdCommand(manager, false));
        commandInvoker.addCommand("history", new HistoryCommand(commandHistory, creator, false));
        commandInvoker.addCommand("add", new AddCommand(manager, true));
        commandInvoker.addCommand("update", new UpdateCommand(manager, true));
        commandInvoker.addCommand("add_if_max", new AddIfMaxCommand(manager, true));
        commandInvoker.addCommand("remove_greater", new RemoveGreaterCommand(manager, true));
        commandInvoker.addCommand("remove_lower", new RemoveLowerCommand(manager, true));
        commandInvoker.addCommand("print_ascending", new PrintAscendingCommand(manager, false));
        commandInvoker.addCommand("print_unique_expelled_students", new PrintUniqueExpelledStudentsCommand(manager, false));
        commandInvoker.addCommand("print_field_descending_form_of_education", new PrintFieldDescendingFormOfEducationCommand(manager, false));
    }

    private void putServerCommands(CommandInvoker commandInvoker, StudyGroupWriter studyGroupWriter, ConnectionListener connectionListener) {
        commandInvoker.addServerCommand("exit", new ExitCommand(studyGroupWriter, connectionListener, this));
        commandInvoker.addServerCommand("save", new SaveCommand(studyGroupWriter));
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
