package com.github.matheusmv.fileuploaddownload.repositories;

import com.github.matheusmv.fileuploaddownload.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
