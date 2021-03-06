package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import commands.StudyGroupCommand;
import exception.CommandExecutionException;
import locale.ServerBundle;
import studygroup.StudyGroup;

/**
 * Класс-команда, реализующая обновление элемента коллекции по его id
 */
public class UpdateCommand extends AbstractCommand implements StudyGroupCommand {
    private final CollectionControl StudyGroupManager;
    private String[] args;

    public UpdateCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(StudyGroup studyGroup) throws CommandExecutionException {
        try {
            if (args.length == 1) {
                StudyGroupManager.updateId(Integer.parseInt(args[0]), studyGroup);
            } else {
                throw new CommandExecutionException(String.format(ServerBundle.getString("exception.expected_got"),
                        1, args.length));
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException(ServerBundle.getString("exception.invalid_format_error"));
        }
    }
}
