package command;

import file.StudyGroupWriter;
import studygroup.StudyGroup;

/**
 * Класс-команда, реализующая сохранение коллекции в файл
 */
public class SaveCommand implements ServerCommand{
    private final StudyGroupWriter studyGroupWriter;

    public SaveCommand(StudyGroupWriter studyGroupWriter) {
        this.studyGroupWriter = studyGroupWriter;
    }
    public void execute() {
        studyGroupWriter.write();
    }

    public void execute(StudyGroup studyGroup) {
        throw new UnsupportedOperationException();
    }
}
