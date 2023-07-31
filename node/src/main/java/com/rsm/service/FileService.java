package com.rsm.service;

import com.rsm.entity.AppDocument;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface FileService {

    AppDocument processDoc(Message externalMessage);
}