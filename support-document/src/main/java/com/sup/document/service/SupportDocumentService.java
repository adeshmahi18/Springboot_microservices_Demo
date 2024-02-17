package com.sup.document.service;

import com.sup.document.entity.SupportDocument;

import java.util.List;

public interface SupportDocumentService {
    String uploadSupDocument(SupportDocument supportDocument);

    List<SupportDocument> findAlltheDocument();
}
