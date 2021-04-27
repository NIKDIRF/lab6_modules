package commands;

import exception.CommandExecutionException;
import studygroup.StudyGroup;

public interface StudyGroupCommand extends Command {
    void execute(StudyGroup studyGroup) throws CommandExecutionException;
}
