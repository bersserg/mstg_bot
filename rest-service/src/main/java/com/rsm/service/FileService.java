package com.rsm.service;

import com.rsm.entity.AppDocument;
import com.rsm.entity.AppPhoto;

public interface FileService {

    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
}