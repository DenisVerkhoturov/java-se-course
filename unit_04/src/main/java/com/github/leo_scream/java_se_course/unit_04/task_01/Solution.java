package com.github.leo_scream.java_se_course.unit_04.task_01;

import com.github.leo_scream.java_se_course.unit_04.shared.KeywordCounter;
import com.github.leo_scream.java_se_course.unit_04.shared.ProgrammingLanguage;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution
{
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        byte[] sourceBytes = Files.readAllBytes(Paths.get(Solution.class.getResource("String.java").toURI()));
        ProgrammingLanguage java = new ProgrammingLanguage(
            "Java",
            new HashSet<>(
                Arrays.asList(
                    new String[]{
                        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
                        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
                        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
                        "native", "new", "package", "private", "protected", "public", "return", "short", "static",
                        "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try",
                        "void", "volatile", "while"
                    }
                )
            )
        );
        KeywordCounter javaKeywordCounter = new KeywordCounter(java);
        File temp = File.createTempFile("keyword-frequency", ".txt");
        javaKeywordCounter.process(new String(sourceBytes))
            .forEach((keyword, frequency) -> System.out.println(keyword + " : " + frequency));
    }
}
