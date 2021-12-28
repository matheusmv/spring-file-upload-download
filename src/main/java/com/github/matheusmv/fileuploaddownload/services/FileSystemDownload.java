package com.github.matheusmv.fileuploaddownload.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Paths;

@Service
public class FileSystemDownload {

    @Value("${app.config.file-base-path}")
    private String fileBasePath;

    public Resource downloadFileFromLocalFileSystem(String fileName) {
        var path = Paths.get(fileBasePath + "/" + fileName);
        Resource resource = null;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }

        return resource;
    }
}
