package com.github.matheusmv.fileuploaddownload.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public void addFilesToZipOutputStream(ZipOutputStream zipOutputStream, List<String> fileNames) {
        fileNames.forEach(fileName -> {
            var resource = new FileSystemResource(fileBasePath + "/" + fileName);
            var zipEntry = new ZipEntry(Objects.requireNonNull(resource.getFilename()));

            try {
                zipEntry.setSize(resource.contentLength());
                zipOutputStream.putNextEntry(zipEntry);
                StreamUtils.copy(resource.getInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
