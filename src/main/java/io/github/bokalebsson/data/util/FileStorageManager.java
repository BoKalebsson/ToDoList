package io.github.bokalebsson.data.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileStorageManager {

    public <T> void saveListToFile(List<T> list, File file, Class<T> type) throws IOException {
        if (list == null) throw new IllegalArgumentException("List is null");
        if (file == null) throw new IllegalArgumentException("File is null");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);
    }

    public <T> List<T> loadListFromFile(File file, Class<T> type) throws IOException {
        if (file == null) throw new IllegalArgumentException("File is null");
        if (!file.exists()) throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();

        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);

        return mapper.readValue(file, listType);
    }

}


