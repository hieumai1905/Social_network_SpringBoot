package com.example.socialnetwork.exceptions;

public class MailException extends Exception{
    public MailException(String message, Exception e) {
        super(message);
    }
}
