package com.github.leo_scream.java_se_course.unit_04.shared;

import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class ProgrammingLanguage {

    private final String name;
    private final Set<String> keywords;

    public ProgrammingLanguage(String name, Set<String> keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public boolean isKeyword(String word) {
        return keywords.contains(word);
    }
}
