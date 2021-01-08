package com.project.uniqo.models;

import javax.validation.constraints.NotBlank;

public class ContactForm {

    private static String to = "ivarswing@gmail.com";
    private String subject;
    private String text;

    public static String getTo() {
        return to;
    }

    @NotBlank(message = "Subject is mandatory")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
