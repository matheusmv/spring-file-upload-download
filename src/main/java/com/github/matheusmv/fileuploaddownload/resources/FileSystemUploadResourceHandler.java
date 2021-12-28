package com.github.matheusmv.fileuploaddownload.resources;

import com.github.matheusmv.fileuploaddownload.services.FileSystemUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileSystemUploadResourceHandler {

    @Autowired
    private FileSystemUpload fileSystemUpload;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        var uri = fileSystemUpload.uploadToLocalFileSystem(file);

        return ResponseEntity.created(uri).build();
    }
}
