package com.github.leo_scream.java_se_course.unit_03.task_02.business;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class FAQEntry {

    private final String question;
    private final String answer;

    public FAQEntry(final String question, final String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
