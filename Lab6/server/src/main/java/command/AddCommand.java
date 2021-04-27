package command;

import commands.AbstractCommand;
import commands.StudyGroupCommand;
import collection.CollectionControl;
import studygroup.StudyGroup;


/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 */
public class AddCommand extends AbstractCommand implements StudyGroupCommand {
    private final CollectionControl StudyGroupManager;

    public AddCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(StudyGroup studyGroup) {
        StudyGroupManager.addStudyGroupById(studyGroup);
    }
}
