package com.sup.document.serviceImpl;

import com.sup.document.entity.SupportDocument;
import com.sup.document.repo.SupportDocumentRepo;
import com.sup.document.service.SupportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportDocumentServiceImpl implements SupportDocumentService {

    private static final String UPLOAD_DIR = "C:/springboot-microservice-banking-app/support-document/target/uploadFiles/";

    @Autowired
    private SupportDocumentRepo supportDocumentRepo;

    @Override
    public String uploadSupDocument(SupportDocument supportDocument) {
        supportDocumentRepo.save(supportDocument);
        return "Document upload successfully";
     }

    @Override
    public List<SupportDocument> findAlltheDocument() {

        List<SupportDocument> allDocuments=  supportDocumentRepo.findAll();
        return allDocuments;
    }
}
