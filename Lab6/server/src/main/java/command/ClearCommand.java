package command;

import commands.AbstractCommand;
import collection.CollectionControl;
import studygroup.StudyGroup;

/**
 * Класс-команда, реализующая удаление всех элементов коллекции
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionControl StudyGroupManager;

    public ClearCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        StudyGroupManager.clear();
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
