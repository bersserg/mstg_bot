package com.rsm.service;

import com.rsm.entity.AppDocument;
import com.rsm.entity.AppPhoto;
import com.rsm.entity.BinaryContent;
import org.springframework.core.io.FileSystemResource;

public interface FileService {

    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
    FileSystemResource getFileSystemResource(BinaryContent binaryContent);
}