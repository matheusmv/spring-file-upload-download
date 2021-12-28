package com.github.matheusmv.fileuploaddownload.services;

import com.github.matheusmv.fileuploaddownload.domain.Document;
import com.github.matheusmv.fileuploaddownload.repositories.DocumentRepository;
import com.github.matheusmv.fileuploaddownload.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DBFileDownload {

    @Value("${app.config.file-base-path}")
    private String fileBasePath;

    @Autowired
    private DocumentRepository documentRepository;

    public Document findDocumentByName(String fileName) {
        return documentRepository.findDocumentByFileName(fileName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Document with name ::%s:: not found", fileName)));
    }
}
