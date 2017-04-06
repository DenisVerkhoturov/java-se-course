package com.github.leo_scream.java_se_course.unit_04.task_02;

import com.github.leo_scream.java_se_course.unit_04.shared.ProgrammingLanguage;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution {

    private final static ProgrammingLanguage java = new ProgrammingLanguage(
        "Java",
        new HashSet<>(
            Arrays.asList(
                new String[]{
                    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                    "class", "const", "continue", "default", "do", "double", "else", "enum",
                    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
                    "import", "instanceof", "int", "interface", "long", "native", "new", "package",
                    "private", "protected", "public", "return", "short", "static", "strictfp",
                    "super", "switch", "synchronized", "this", "throw", "throws", "transient",
                    "try", "void", "volatile", "while"
                }
            )
        )
    );

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Pass filepath as argument");
        }
        File sourceFile = new File(args[0]);
        String sourceContent = readFileUsingByteStream(sourceFile);
        File temporaryFile = File.createTempFile("keyword-frequency", ".txt");
        StringBuilder frequencyContent = new StringBuilder();
        Arrays.stream(sourceContent.split(" "))
            .filter(java::isKeyword)
            .collect(groupingBy(identity(), summingInt(word -> 1)))
            .forEach(
                (keyword, frequency) -> {
                    String keywordFrequency = keyword + " : " + frequency + System.lineSeparator();
                    frequencyContent.append(keywordFrequency);
                }
            );
        writeFileUsingByteStream(temporaryFile, frequencyContent.toString());
        System.out.println("Results can be viewed in file: " + temporaryFile.getPath());
    }

    /**
     * Read {@code File} to {@code String} using {@code java.io.FileInputStream}.
     *
     * @param file File to read from
     * @return Content of file as {@code String}
     * @throws IOException If an I/O error occurs.
     */
    static String readFileUsingByteStream(File file) throws IOException {
        String content;
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
            content = new String(buffer);
        }
        return content;
    }

    /**
     * Write {@code String} to {@code File} using {@code java.io.FileInputStream}.
     */
    static void writeFileUsingByteStream(File file, String content) throws IOException {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            stream.write(content.getBytes());
        }
    }
}
