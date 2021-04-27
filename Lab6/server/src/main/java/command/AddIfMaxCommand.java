package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import commands.StudyGroupCommand;
import studygroup.StudyGroup;

public class AddIfMaxCommand extends AbstractCommand implements StudyGroupCommand {
    private final CollectionControl StudyGroupManager;

    public AddIfMaxCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(StudyGroup studyGroup) {
        StudyGroupManager.addStudyGroupByIdIfMax(studyGroup);
    }
}
