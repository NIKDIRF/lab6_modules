package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import studygroup.StudyGroup;

public class PrintFieldDescendingFormOfEducationCommand extends AbstractCommand {
    private final CollectionControl StudyGroupManager;

    public PrintFieldDescendingFormOfEducationCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        StudyGroupManager.printFieldDescendingFormOfEducation();
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
