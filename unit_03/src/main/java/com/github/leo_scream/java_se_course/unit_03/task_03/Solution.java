package com.github.leo_scream.java_se_course.unit_03.task_03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Solution {

    private String text;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Solution app = new Solution();
        app.text = new String(
            Files.readAllBytes(Paths.get(Solution.class.getResource("index.html").toURI())),
            Charset.forName("windows-1251")
        );

        Matcher pictureReferenceMatcher = Pattern.compile(
            "\\([Рр]ис\\.\\s(\\d+)\\)"
        ).matcher(app.text);

        boolean isOrdered = true;
        int lastPictureIndex = 0;

        while (pictureReferenceMatcher.find()) {
            int pictureIndex = Integer.parseInt(pictureReferenceMatcher.group(1));

            if (lastPictureIndex > pictureIndex) {
                isOrdered = false;
                break;
            }

            lastPictureIndex = pictureIndex;
        }

        Matcher sentencesWithPictureMatcher = Pattern.compile(
            "(?:[^.?!<>]*\\([Рр]ис\\.\\s\\d+\\)[^.?!<>]*)+[.?!]+"
        ).matcher(app.text);

        List<String> sentences = new ArrayList<>();

        while (sentencesWithPictureMatcher.find()) {
            sentences.add(sentencesWithPictureMatcher.group());
        }

        System.out.println("The author refers to images consistently: " + isOrdered);
        System.out.println("======================================================");
        System.out.println("Sentences with reference to image:");
        sentences.forEach(sentence -> {
            System.out.println("------------------------------");
            System.out.println(sentence.trim());
        });
    }
}
