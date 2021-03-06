package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import locale.ServerBundle;
import studygroup.StudyGroup;

/**
 * Класс-команда, реализующая удаление элемента коллекции по его ID
 */
public class RemoveByIdCommand extends AbstractCommand implements Command {
    private final CollectionControl StudyGroupManager;
    private String[] args;

    public RemoveByIdCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                StudyGroupManager.removeById(Integer.parseInt(args[0]));
            } else {
                throw new CommandExecutionException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        }  catch (NumberFormatException nfe) {
            throw new CommandExecutionException(ServerBundle.getString("exception.invalid_format_error"));
        }

    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
