package collection;

import locale.ServerBundle;
import response.Creator;
import studygroup.StudyGroup;
import studygroup.StudygroupFormatter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class HashSetControl implements CollectionControl {

    private HashSet<StudyGroup> studyGroupHashSet;

    private final Creator creator;

    private final SortedSet<Integer> setId = new TreeSet<>();

    private final LocalDate dateOfInit = LocalDate.now();

    public HashSetControl(Creator creator) {
        studyGroupHashSet = new HashSet<>();
        this.creator = creator;
    }

    public void addStudyGroupById(StudyGroup studyGroup) {
        int id = 1;
        for (int idSearch : setId) {
            if (idSearch != id) {
                id = idSearch - 1;
                break;
            }
            id++;
        }

        studyGroup.setId(id);
        studyGroupHashSet.add(studyGroup);
        addUniqueID(id);
    }

    public void addStudyGroupByIdIfMax(StudyGroup studyGroup) {
        int id = 1;
        for (int idSearch : setId) {
            if (idSearch != id) {
                id = idSearch - 1;
                break;
            }
            id++;
        }

        if (studyGroup.compareTo(studyGroupHashSet.stream().max(Comparator.comparingLong(StudyGroup::getStudentsCount)).get()) > 0) {
            studyGroup.setId(id);
            studyGroupHashSet.add(studyGroup);
            addUniqueID(id);
        }
    }

    public void addStudyGroup(StudyGroup studyGroup) {
        studyGroupHashSet.add(studyGroup);
        addUniqueID(studyGroup.getId());
    }

    public void addStudyGroups(Collection<StudyGroup> collection) {
        collection.forEach(x -> addUniqueID(x.getId()));
        studyGroupHashSet.addAll(collection);
    }

    public boolean addUniqueID(int id) {
        return setId.add(id);
    }

    public void removeUniqueID(int id) {
        setId.remove(id);
    }

    public int findStudyGroupById(int id) {
        int index = 0;
        for (StudyGroup studyGroup : studyGroupHashSet) {
            if (studyGroup.getId() == id) {
                return index;
            }
            index++;
        }
        return index;
    }

    public HashSet<StudyGroup> getStudyGroupHashSet() {
        return studyGroupHashSet;
    }

    public void info() {
        creator.addToMsg(String.format(ServerBundle.getString("collection.info"), studyGroupHashSet.getClass().getName(),
                dateOfInit.toString(), studyGroupHashSet.size()));
    }

    public void clear() {
        studyGroupHashSet.clear();
        setId.clear();
    }

    public void show() {
        StudygroupFormatter formatter = new StudygroupFormatter();
        studyGroupHashSet.stream()
                .sorted()
                .forEach(x -> creator.addToMsg(formatter.formatStudyGroup(x)));
    }

    public void printAscending() {
        StudygroupFormatter formatter = new StudygroupFormatter();
        studyGroupHashSet.stream().sorted().forEach(x -> creator.addToMsg(formatter.formatStudyGroup(x)));
    }

    public void printUniqueExpelledStudents() {
        studyGroupHashSet.stream()
                .flatMapToLong(x -> LongStream.of(x.getExpelledStudents()))
                .distinct()
                .forEach(x -> creator.addToMsg(String.valueOf(x)));
    }

    public void printFieldDescendingFormOfEducation() {
        studyGroupHashSet.stream()
                .sorted(Comparator.comparingInt(o -> ((StudyGroup) o).getFormOfEducation().ordinal()).reversed())
                .forEach(x -> creator.addToMsg(String.valueOf(x.getFormOfEducation().ordinal())));
    }

    public void removeById(int id) {
        studyGroupHashSet.remove(studyGroupHashSet.stream().filter(x -> x.getId() == id).findFirst().get());
    }


    public void removeGreater(StudyGroup studyGroup) {
        studyGroupHashSet = studyGroupHashSet.stream()
                .filter(x -> studyGroup.compareTo(x) > 0)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public void removeLower(StudyGroup studyGroup) {
        studyGroupHashSet = studyGroupHashSet.stream()
                .filter(x -> studyGroup.compareTo(x) < 0)
                .collect(Collectors.toCollection(HashSet::new));
    }



    public void updateId(int id, StudyGroup studyGroup)
            throws NumberFormatException{
        studyGroupHashSet.remove(studyGroupHashSet.stream().filter(x -> x.getId() == id).findFirst().get());
        studyGroup.setId(id);
        studyGroupHashSet.add(studyGroup);
    }
}
