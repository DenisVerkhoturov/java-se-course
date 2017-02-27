package com.github.leo_scream.java_se_course.unit_03.task_01;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class CrazyLogger
{
    private final StringBuilder log;
    private final MessageFormat template;
    private final String delimiter;

    public CrazyLogger()
    {
        this.log = new StringBuilder();
        this.delimiter = "\n";
        this.template = new MessageFormat(
            "{0,date,dd-mm-YYYY} : {0,time,hh-mm} — {1};" + delimiter
        );
    }

    public void log(String message)
    {
        final Object[] parameters = {
            Date.from(Instant.now()),
            message.trim().replaceAll(delimiter, " ")
        };
        log.append(template.format(parameters));
    }

    public String firstMessage()
    {
        return log.toString().equals("")
            ? ""
            : log.substring(0, log.indexOf(delimiter));
    }

    public String lastMessage()
    {
        String[] messages = log.toString().split(delimiter);
        return messages.length > 0 ? messages[messages.length - 1] : "";
    }

    public List<String> head()
    {
        return this.head(10);
    }

    public List<String> head(int count)
    {
        String loggedString = log.toString();

        if (loggedString.equals("")) {
            return Arrays.asList(new String[0]);
        } else {
            List<String> messages = Arrays.asList(loggedString.split(delimiter));

            int end = messages.size() > count
                ? count
                : messages.size();

            return messages.subList(0, end);
        }
    }

    public List<String> tail()
    {
        return this.tail(10);
    }

    public List<String> tail(int count)
    {
        String loggedString = log.toString();

        if (loggedString.equals("")) {
            return Arrays.asList(new String[0]);
        } else {
            List<String> messages = Arrays.asList(loggedString.split(delimiter));

            int start = messages.size() > count
                ? messages.size() - count
                : 0;

            return messages.subList(start, messages.size());
        }
    }
}
