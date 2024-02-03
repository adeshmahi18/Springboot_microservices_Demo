package com.serviceApp.service.impl;

import com.serviceApp.entity.BlackLine;
import com.serviceApp.repo.BlackLineRepo;
import com.serviceApp.service.BlackLineService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BlackLineServiceImpl implements BlackLineService {

    @Autowired
    private BlackLineRepo blackLineRepo;


    @Override
    @Cacheable("excelCache")
    public void uploadData(MultipartFile file) throws IOException {

        Workbook workbook= WorkbookFactory.create(file.getInputStream());
        Sheet sheet=workbook.getSheet("Reconcilation");
        Iterator<Row> rowIterator = sheet.iterator();
        Row headRow = rowIterator.next();
        while(rowIterator.hasNext()){
            BlackLine blackLine= new BlackLine();
            Row row=rowIterator.next();
            blackLine.setStatus(getStringCellValue(row.getCell(0)));
            blackLine.setTempKey(getStringCellValue(row.getCell(1)));
            blackLine.setTempRisk(getStringCellValue(row.getCell(2)));
            blackLine.setPreparer(getStringCellValue(row.getCell(3)));
            blackLine.setTempTeam(getStringCellValue(row.getCell(4)));
            blackLine.setCompanyCode(getStringCellValue(row.getCell(5)));
            blackLine.setCodeDesc(getStringCellValue(row.getCell(6)));
            blackLine.setTempAccount(getStringCellValue(row.getCell(7)));
            blackLine.setTempDesc(getStringCellValue(row.getCell(8)));
            blackLine.setGlBalance(getStringCellValue(row.getCell(9)));
            blackLine.setDueData(getStringCellValue(row.getCell(10)));
            blackLineRepo.save(blackLine);
        }
    }

    @Cacheable("readExcelData")
    public List<BlackLine> readAllData(){
            List<BlackLine> allBlackLineData= blackLineRepo.findAll();
        return allBlackLineData;
    }

    @Override
    public Optional<BlackLine> getDataById(Long id) {
        Optional<BlackLine> getByID= blackLineRepo.findById(id);
        return getByID;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                return "";
            default:
                return null; // Handle other cell types as needed
        }
    }
}
