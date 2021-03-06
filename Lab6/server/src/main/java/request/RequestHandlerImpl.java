package request;

import command.CommandInvoker;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import locale.ServerBundle;
import log.Log;
import response.Creator;
import studygroup.Request;
import studygroup.RequestType;
import studygroup.Response;

public class RequestHandlerImpl implements RequestHandler{
    private final Creator responseCreator;
    private final CommandInvoker commandInvoker;

    public RequestHandlerImpl(CommandInvoker commandInvoker, Creator responseCreator) {
        this.commandInvoker = commandInvoker;
        this.responseCreator = responseCreator;
    }

    public Response handleRequest(Request request) throws CommandNotFoundException, CommandExecutionException {
        if (request.getType().equals(RequestType.STUDYGROUP_REQUEST)) {
            return handleStudyGroupRequest(request);
        } else {
            return handleCommandRequest(request);
        }
    }

    private Response handleStudyGroupRequest(Request request) throws CommandNotFoundException {
        Log.getLogger().info(ServerBundle.getString("server.ask_route_requirement"));
        if (commandInvoker.checkStudyGroupRequirement(request.getUserString())) {
            Log.getLogger().info(ServerBundle.getString("server.ask_route_positive"));
            return responseCreator.createResponse("", true, true);
        } else {
            Log.getLogger().info(ServerBundle.getString("server.ask_route_negative"));
            return responseCreator.createResponse("", true, false);
        }
    }

    private Response handleCommandRequest(Request request) throws CommandExecutionException, CommandNotFoundException {
        Log.getLogger().info(ServerBundle.getFormattedString("server.execute_attempt", request.getUserString()));
        commandInvoker.execute(request.getUserString(), request.getStudyGroup());
        return responseCreator.createResponse();
    }
}
