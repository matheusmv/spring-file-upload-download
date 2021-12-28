package com.github.matheusmv.fileuploaddownload.services;

import com.github.matheusmv.fileuploaddownload.domain.Document;
import com.github.matheusmv.fileuploaddownload.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@Service
public class DBFileUpload {

    @Value("${app.config.file-base-path}")
    private String fileBasePath;

    @Value("${app.config.file-download-path}")
    private String fileDownloadPath;

    @Autowired
    private DocumentRepository documentRepository;

    public URI uploadToDatabase(MultipartFile file) {
        var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        var newDocument = new Document();

        try {
            newDocument.setFileName(fileName);
            newDocument.setFile(file.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        documentRepository.save(newDocument);

        return URI.create(String.join("/", fileDownloadPath, fileName));
    }
}
