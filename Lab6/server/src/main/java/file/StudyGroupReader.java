package file;

import exception.InvalidArgumentException;
import exception.StudyGroupBuildException;
import exception.StudyGroupReadException;
import studygroup.StudyGroup;

import java.util.Collection;

public interface StudyGroupReader {
    Collection<StudyGroup> read() throws InvalidArgumentException, StudyGroupReadException, StudyGroupBuildException;
}
