package io.github.bokalebsson.data.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileStorageManager {

    public <T> void saveListToFile(List<T> list, File file, Class<T> type) throws IOException {
        if (list == null) throw new IllegalArgumentException("List is null");
        if (file == null) throw new IllegalArgumentException("File is null");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);
    }

}


