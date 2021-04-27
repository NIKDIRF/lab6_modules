package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import commands.StudyGroupCommand;
import studygroup.StudyGroup;

public class RemoveLowerCommand extends AbstractCommand implements StudyGroupCommand {
    private final CollectionControl StudyGroupManager;

    public RemoveLowerCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(StudyGroup studyGroup) {
        StudyGroupManager.removeLower(studyGroup);
    }
}
