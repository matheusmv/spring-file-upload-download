package com.github.matheusmv.fileuploaddownload.resources;

import com.github.matheusmv.fileuploaddownload.services.FileSystemDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/files")
public class FileSystemDownloadResourceHandler {

    @Autowired
    private FileSystemDownload fileSystemDownload;

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        var resource = fileSystemDownload.downloadFileFromLocalFileSystem(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                ).body(resource);
    }

    @GetMapping(value = "/zip-download", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> zipDownload(@RequestParam("name") List<String> fileNames) throws IOException {
        var zipFileName = "download-" + Instant.now().toString() + ".zip";

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + zipFileName + "\""
                ).body(outputStream -> {
                    var zipOutPutStream = new ZipOutputStream(outputStream);

                    fileSystemDownload.addFilesToZipOutputStream(zipOutPutStream, fileNames);

                    zipOutPutStream.close();
                });
    }
}
