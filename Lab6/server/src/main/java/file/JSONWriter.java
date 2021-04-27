package file;

import collection.CollectionControl;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JSONWriter implements StudyGroupWriter {
    private final Gson serializer = new Gson();
    private final CollectionControl collectionControl;
    private final String filename;

    public JSONWriter(CollectionControl collectionControl, String fileName) {
        this.collectionControl = collectionControl;
        this.filename = fileName;
    }

    public void write() {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename))) {
            byte[] buffer = serializer.toJson(collectionControl.getStudyGroupHashSet()).getBytes();
            out.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            System.out.println("Вы указали нерверный путь до файла сохранения. Попробуйте снова, предварительно введя команду save ещё раз.");
        }
    }
}
