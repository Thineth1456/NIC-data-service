package org.mobios.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.mobios.model.FIleDetail;
import org.mobios.service.FileDetailService;
import org.mobios.service.NicService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NicController {

    NicService nicService = new NicService();
    @PostMapping
    public void getCsv(@RequestParam("file") List<MultipartFile> files) throws SQLException {
        if (files.isEmpty()) {
            System.out.println();
        }
        for(MultipartFile file :files) {
            FileDetailService fileservice = new FileDetailService();
            String fileId = fileservice.generateNextId();
            fileservice.addFile(new FIleDetail(fileId,"U001",file.getOriginalFilename(), LocalDate.now()));
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {
                List<String> idsList = new ArrayList<>();
                for (CSVRecord csvRecord : csvParser) {
                    idsList.add(csvRecord.get("New_NIC"));
                    System.out.println(csvRecord.get("New_NIC"));



                }
                nicService.getIdList(idsList,fileId,"U001");

            } catch (Exception e) {

            }
        }
    }
}