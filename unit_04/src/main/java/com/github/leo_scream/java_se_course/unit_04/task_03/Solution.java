package com.github.leo_scream.java_se_course.unit_04.task_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Pass filepath as argument");
        }
        File sourceFile = new File(args[0]);
        File destinationFile = File.createTempFile("converted", ".txt");
        try(
            InputStreamReader reader = new InputStreamReader(
                new FileInputStream(sourceFile), StandardCharsets.UTF_8
            );
            OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(destinationFile), StandardCharsets.UTF_16
            )
        ) {
            while (reader.ready()) {
                writer.write(reader.read());
            }
        }
        System.out.println("Results can be viewed: " + destinationFile.getPath());
    }
}
