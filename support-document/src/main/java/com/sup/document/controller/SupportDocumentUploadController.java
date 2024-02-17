package com.sup.document.controller;

import com.netflix.discovery.converters.Auto;
import com.sup.document.entity.SupportDocument;
import com.sup.document.service.SupportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SupportDocumentUploadController {

    private static final String UPLOAD_DIR = "C:/springboot-microservice-banking-app/support-document/target/uploadFiles/";

    String message="";

    @Autowired
    private SupportDocumentService supportDocumentService;

    @PostMapping("/text")
    public void text(){
        System.out.println("textpppsad;asdj");
    }


    @GetMapping("/api/findAllDocuments")
    public ResponseEntity<?> findAlltheDocuments(){
        List<SupportDocument> allDocuments=supportDocumentService.findAlltheDocument();
        allDocuments.stream()
                .peek(file-> file.setFileName(UPLOAD_DIR + file.getFileName()))
                .map(SupportDocument::getFileName).collect(Collectors.toList());
        ///System.out.println(allFilesWithPathName);
         return new ResponseEntity<>(allDocuments, HttpStatus.OK);
    }

    //Testing the merge



    @PostMapping("/api/uploadSupDocument")
    public ResponseEntity<?> uploadSupDocument(@RequestParam("file") MultipartFile[] files, @RequestParam("id") long id) throws IOException {
        System.out.println("files=="+files);
        SupportDocument supportDocument =new SupportDocument();
            for(MultipartFile file:files){
                File destFile= new File(UPLOAD_DIR+file.getOriginalFilename());
                System.out.println("files=="+destFile);
                file.transferTo(destFile);
                supportDocument.setFileName(file.getOriginalFilename());
                supportDocument.setBlackLineId(id);
                 message=supportDocumentService.uploadSupDocument(supportDocument);
            }

       return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
