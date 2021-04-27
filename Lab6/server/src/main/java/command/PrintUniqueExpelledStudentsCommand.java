package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import studygroup.StudyGroup;

public class PrintUniqueExpelledStudentsCommand extends AbstractCommand {
    private final CollectionControl StudyGroupManager;

    public PrintUniqueExpelledStudentsCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        StudyGroupManager.printUniqueExpelledStudents();
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
