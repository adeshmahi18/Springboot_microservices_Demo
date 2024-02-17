package com.sup.document.repo;

import com.sup.document.entity.SupportDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportDocumentRepo extends JpaRepository<SupportDocument, Long> {
}
