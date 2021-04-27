package collection;

import studygroup.StudyGroup;

import java.util.Collection;
import java.util.HashSet;

public interface CollectionControl {
    HashSet<StudyGroup> getStudyGroupHashSet();
    void addStudyGroup(StudyGroup studyGroup);
    void addStudyGroupByIdIfMax(StudyGroup studyGroup);
    void addStudyGroupById(StudyGroup studyGroup);
    void addStudyGroups(Collection<StudyGroup> collection);
    boolean addUniqueID(int id);
    void updateId(int id, StudyGroup studyGroup) throws NumberFormatException;
    void info();
    void clear();
    void show();
    void printAscending();
    void printUniqueExpelledStudents();
    void printFieldDescendingFormOfEducation();
    void removeById(int id);
    void removeGreater(StudyGroup studyGroup);
    void removeLower(StudyGroup studyGroup);
}
