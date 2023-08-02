package com.rsm.service;

import com.rsm.entity.AppDocument;
import com.rsm.entity.AppPhoto;
import com.rsm.service.enums.LinkType;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface FileService {

    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
    String generateLink(Long docId, LinkType linkType);
}