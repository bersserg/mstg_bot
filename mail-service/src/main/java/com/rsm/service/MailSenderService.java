package com.rsm.service;

import com.rsm.dto.MailParams;

public interface MailSenderService {

    void send(MailParams mailParams);
}