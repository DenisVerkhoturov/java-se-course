package com.github.leo_scream.java_se_course.unit_04.shared;

import java.util.Arrays;
import java.util.Map;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class KeywordCounter
{
    private final ProgrammingLanguage language;

    public KeywordCounter(ProgrammingLanguage language)
    {
        this.language = language;
    }

    public Map<String, Integer> process(final String source)
    {
        return Arrays.stream(source.split(" "))
            .filter(language::isKeyword)
            .collect(groupingBy(identity(), summingInt(word -> 1)));
    }
}
