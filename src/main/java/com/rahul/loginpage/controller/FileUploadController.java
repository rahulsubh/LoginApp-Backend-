package com.rahul.loginpage.controller;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.loginpage.model.Associate;
import com.rahul.loginpage.repository.AssociateRepository;

@CrossOrigin("*")
@RestController
public class FileUploadController {

    @Autowired
    private AssociateRepository associateRepository;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file.";
        }

        try {
            // Load the uploaded Excel file into a Workbook
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            // Get the first sheet from the workbook
            Sheet sheet = (Sheet) workbook.getSheetAt(0);

            // Iterate through each row of the sheet
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                // Assuming the first column contains associate IDs and the second column contains names
                String associateId = currentRow.getCell(0).getStringCellValue();
                String name = currentRow.getCell(1).getStringCellValue();

                // Save the associate to the database and mark as having permissions
                Associate associate = new Associate();
                associate.setAssociateIds(associateId);
                associate.setHasPermission(true); // Assuming everyone in the Excel file has permission
                associateRepository.save(associate);
            }

            workbook.close();
            return "File uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file.";
        }
    }
}