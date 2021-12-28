package com.github.matheusmv.fileuploaddownload.resources;

import com.github.matheusmv.fileuploaddownload.services.DBFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class DatabaseUploadResourceHandler {

    @Autowired
    private DBFileUpload dbFileUpload;

    @PostMapping("/upload-db")
    public ResponseEntity<?> uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
        var uri = dbFileUpload.uploadToDatabase(file);

        return ResponseEntity.ok().body(uri);
    }
}
