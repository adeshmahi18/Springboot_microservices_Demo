package com.serviceApp.service;

import com.serviceApp.entity.BlackLine;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BlackLineService {

    public void uploadData(MultipartFile file) throws IOException;

    public List<BlackLine> readAllData();
    public Optional<BlackLine> getDataById(Long id);
}
