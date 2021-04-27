package file;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import studygroup.StudyGroup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class JSONReader implements StudyGroupReader {
    private HashSet<Integer> idSet = new HashSet<>();
    private final Gson serializer = new Gson();
    private final String fileName;
    private Scanner scanner;
    private String inputLine;

    public JSONReader(String fileName) {
        this.fileName = fileName;
    }

    public Collection<StudyGroup> read() {
        try {
            scanner = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("файл не существует.");
            System.exit(1);
        }
        Collection<StudyGroup> loadStudyStream;
        if (scanner.hasNext()) {
            this.inputLine = scanner.nextLine();
            try {
                loadStudyStream = serializer.fromJson(inputLine, new TypeToken<HashSet<StudyGroup>>() {
                }.getType());
                Iterator<StudyGroup> i = loadStudyStream.iterator();
                StudyGroup checker;
                try {
                    while (i.hasNext()) {
                        checker = i.next();
                        if (!idSet.add(checker.getId())) {
                            throw new NumberFormatException();
                        } else {
                            try {
                                if (checker.getId() <= 0)
                                    throw new NumberFormatException();
                            } catch (NumberFormatException ex) {
                                System.out.println("В исходном файле найден id меньший 1, исправьте и попробуйте снова.");
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("В исходном файле найдены повторяющиеся id, исправьте и попробуйте снова.");
                    System.exit(1);
                }
                return loadStudyStream;
            } catch (JsonSyntaxException ex) {
                System.out.println("Нарушен синтаксис Json.");
                System.exit(1);
            }
        } else {
            System.out.println("исходный файл пуст.");
            System.exit(1);
        }
        return null;
    }

}
