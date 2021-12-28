package com.github.matheusmv.fileuploaddownload.repositories;

import com.github.matheusmv.fileuploaddownload.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findDocumentByFileName(String fileName);
}
