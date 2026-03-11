package org.mobios.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
public class NicController {
    @PostMapping
    public void getCsv(@RequestParam("file") List<MultipartFile> files) {
        if (files.isEmpty()) {
            System.out.println();
        }
        for(MultipartFile file :files) {
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {

                for (CSVRecord csvRecord : csvParser) {

                    String name = csvRecord.get("Old_NIC");


                }


            } catch (Exception e) {

            }
        }
    }
}