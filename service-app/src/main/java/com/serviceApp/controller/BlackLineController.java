package com.serviceApp.controller;

import com.serviceApp.entity.BlackLine;
import com.serviceApp.service.BlackLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class BlackLineController {

    @Autowired
    private BlackLineService blackLineService;

    @PostMapping("/uploadData")
    public ResponseEntity<?> uploadBlackLineData(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("controller page");
        blackLineService.uploadData(file);
       return new ResponseEntity<>("Data Saved cuccessfully", HttpStatus.CREATED);
    }

    @GetMapping("/readAllData")
    public ResponseEntity<?> readAllData(){
        blackLineService.readAllData();
      return new ResponseEntity<>(blackLineService.readAllData(), HttpStatus.OK);
    }

    @GetMapping("/getDataByID/{id}")
    public ResponseEntity<?> getDataByID(@PathVariable("id") Long id){
        Optional<BlackLine> blackLine = blackLineService.getDataById(id);
        return new ResponseEntity<>(blackLine, HttpStatus.OK);
    }
}
