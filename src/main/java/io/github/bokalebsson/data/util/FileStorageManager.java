package io.github.bokalebsson.data.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileStorageManager {

    public <T> void saveListToFile(List<T> list, File file, Class<T> type) throws IOException {
        if (list == null) throw new IllegalArgumentException("List is null");
        if (file == null) throw new IllegalArgumentException("File is null");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.writeValue(file, list);
    }

    public <T> List<T> loadListFromFile(File file, Class<T> type) throws IOException {
        if (file == null) throw new IllegalArgumentException("File is null");
        if (!file.exists()) throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);

        return mapper.readValue(file, listType);
    }

    public <K, V> void saveMapToFile(Map<K, V> map, File file) throws IOException {
        if (map == null) throw new IllegalArgumentException("Map is null");
        if (file == null) throw new IllegalArgumentException("File is null");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.writeValue(file, map);
    }

    public <K, V> Map<K, V> loadMapFromFile(File file, TypeReference<Map<K, V>> typeRef) throws IOException {
        if (file == null) throw new IllegalArgumentException("File is null");
        if (!file.exists()) throw new FileNotFoundException("File does not exist: " + file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.readValue(file, typeRef);
    }

}


