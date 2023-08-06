package com.rsm.service.impl;

import com.rsm.dao.AppDocumentDAO;
import com.rsm.dao.AppPhotoDAO;
import com.rsm.entity.AppDocument;
import com.rsm.entity.AppPhoto;
import com.rsm.service.FileService;
import com.rsm.utils.CryptoTool;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class FileServiceImpl implements FileService {

    private final AppDocumentDAO appDocumentDAO;
    private final AppPhotoDAO appPhotoDAO;
    private final CryptoTool cryptoTool;

    public FileServiceImpl(AppDocumentDAO appDocumentDAO, AppPhotoDAO appPhotoDAO, CryptoTool cryptoTool) {
        this.appDocumentDAO = appDocumentDAO;
        this.appPhotoDAO = appPhotoDAO;
        this.cryptoTool = cryptoTool;
    }

    @Override
    public AppDocument getDocument(String hash) {
        var id = cryptoTool.idOf(hash);
        if (id == null) {
            return null;
        }
        return appDocumentDAO.findById(id).orElse(null);
    }

    @Override
    public AppPhoto getPhoto(String hash) {
        var id = cryptoTool.idOf(hash);
        if (id == null) {
            return null;
        }
        return appPhotoDAO.findById(id).orElse(null);
    }
}