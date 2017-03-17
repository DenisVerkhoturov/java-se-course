package com.github.leo_scream.java_se_course.unit_05.task_02;

import com.github.leo_scream.java_se_course.unit_05.task_02.exceptions.NoSuchPropertiesFileException;
import com.github.leo_scream.java_se_course.unit_05.task_02.exceptions.NoSuchPropertyException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Properties {

    private static Map<File, Map<String, String>> filePropertiesMap;
    private final File file;

    public Properties() {
        this.file = null;
    }

    private Properties(final File file, final Map<String, String> properties) {
        this.file = file;
        filePropertiesMap.put(file, null);
    }

    public Properties fromFile(final String fileName) throws NoSuchPropertiesFileException {
        Objects.requireNonNull(fileName);

        final File file = new File(fileName);

        Map<String, String> properties = load();

        return new Properties(file, properties);
    }

    public String get(String key) throws NoSuchPropertyException {
        return filePropertiesMap.get(file).get(key);
    }

    public Stream<Entry<String, String>> entries() {
        return filePropertiesMap.get(this.file).entrySet().stream();
    }

    private Map<String, String> load() throws NoSuchPropertiesFileException {
        try {
            return Files.lines(this.file.toPath())
                .collect(
                    Collectors.toMap(
                        line -> line.substring(0, line.indexOf('=')).trim(),
                        line -> line.substring(line.indexOf('=')).trim()
                    )
                );
        } catch (IOException e) {
            throw new NoSuchPropertiesFileException(e);
        }
    }
}
