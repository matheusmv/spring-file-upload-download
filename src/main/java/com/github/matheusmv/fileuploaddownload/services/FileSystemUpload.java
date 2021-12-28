package com.github.matheusmv.fileuploaddownload.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemUpload {

    @Value("${app.config.file-base-path}")
    private String fileBasePath;

    @Value("${app.config.file-download-path}")
    private String fileDownloadPath;

    public URI uploadToLocalFileSystem(MultipartFile file) {
        var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        var path = Paths.get(fileBasePath + "/" + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return URI.create(String.join("/", fileDownloadPath, fileName));
    }
}
