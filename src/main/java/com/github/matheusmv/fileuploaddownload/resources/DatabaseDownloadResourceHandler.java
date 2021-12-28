package com.github.matheusmv.fileuploaddownload.resources;

import com.github.matheusmv.fileuploaddownload.services.DBFileDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class DatabaseDownloadResourceHandler {

    @Autowired
    private DBFileDownload dbFileDownload;

    @GetMapping("/download/{fileName:.+}/db")
    public ResponseEntity<?> downloadFileFromDB(@PathVariable String fileName) {
        var document = dbFileDownload.findDocumentByName(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + document.getFileName() + "\""
                ).body(document.getFile());
    }
}
