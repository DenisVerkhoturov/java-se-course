package com.github.leo_scream.java_se_course.unit_04.shared;

import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class ProgrammingLanguage
{
    private final String name;
    private final Set<String> keywords;

    public ProgrammingLanguage(String name, Set<String> keywords)
    {
        this.name = name;
        this.keywords = keywords;
    }

    public boolean isKeyword(String word)
    {
        return keywords.contains(word);
    }

    public String content()
    {
        return this.name;
    }

    public Stream<String> keywords()
    {
        return null;
    }
}
