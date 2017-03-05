package com.github.leo_scream.java_se_course.unit_03.task_01;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class CrazyLogger
{
    private final StringBuilder log;
    private final String messagesDelimiter;
    private final String dateMessageDelimiter;
    private final DateTimeFormatter format;

    public CrazyLogger()
    {
        this.log = new StringBuilder();
        this.messagesDelimiter = "\n";
        this.dateMessageDelimiter = "—";
        this.format = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy : HH-mm")
            .toFormatter();
    }

    /**
     * Get all the logged messages as steam.
     *
     * @return Stream of {@link Message messages} containing all the logged messages.
     */
    public Stream<Message> messages()
    {
        if (log.length() > 0) {
            return Arrays.stream(log.toString().split(messagesDelimiter)).map(
                entry -> {
                    String[] parts = entry.split(dateMessageDelimiter);
                    String date = parts[0].trim();
                    String text = parts[1].trim();
                    ZonedDateTime dateTime = LocalDateTime.parse(date, format).atZone(ZoneId.systemDefault());
                    return new Message(dateTime, text);
                }
            );
        } else {
            return Stream.empty();
        }
    }

    /**
     * Logs message.
     *
     * @param message
     * 		Message to log
     */
    public CrazyLogger log(String message)
    {
        log.append(format.format(ZonedDateTime.now()))
            .append(" ")
            .append(dateMessageDelimiter)
            .append(" ")
            .append(clean(message))
            .append(messagesDelimiter);

        return this;
    }

    /**
     * Make text safe to keep logged.
     *
     * @param text
     * 		To clean from delimiters which {@link CrazyLogger}
     * 		uses to separate date and text of message
     *
     * @return Safe to keep logged string
     */
    private String clean(String text)
    {
        return text.trim()
            .replaceAll(messagesDelimiter, " ")
            .replaceAll(dateMessageDelimiter, "-");
    }

    /**
     * Represents log entry
     */
    public class Message
    {
        private final ZonedDateTime dateTime;
        private final String text;

        private Message(ZonedDateTime dateTime, String text)
        {
            this.dateTime = dateTime;
            this.text = text;
        }

        public ZonedDateTime getDateTime()
        {
            return this.dateTime;
        }

        public String getText()
        {
            return this.text;
        }
    }
}
