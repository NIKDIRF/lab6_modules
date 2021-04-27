package command;

import collection.CollectionControl;
import commands.AbstractCommand;
import commands.Command;
import studygroup.StudyGroup;

/**
 * Класс-команда, реализующая вывод в стандартный поток всей коллекциииии
 */
public class ShowCommand extends AbstractCommand implements Command {
    private final CollectionControl StudyGroupManager;

    public ShowCommand(CollectionControl StudyGroupManager, boolean req) {
        super(req);
        this.StudyGroupManager = StudyGroupManager;
    }

    public void execute() {
        StudyGroupManager.show();
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
