package com.nk.realstateapi.services;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailWithTemplate(String to, String subject, String templateName, Object model);
}
