package org.mobios.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.mobios.model.FIleDetail;
import org.mobios.model.InValied;
import org.mobios.model.NICDetail;
import org.mobios.model.Report;
import org.mobios.service.FileDetailService;
import org.mobios.service.NicService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(("/data-service"))
public class NicController {

    NicService nicService = new NicService();

    @PostMapping("/upload-files")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<InValied> getCsv(@RequestParam("file") List<MultipartFile> files, @RequestParam("userId") String loggedUserID) throws SQLException {
        if (files.isEmpty()) {
            System.out.println();
        }
        System.out.println(loggedUserID);
        List<InValied> wrongIdList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileDetailService fileservice = new FileDetailService();
            String fileId = fileservice.generateNextId();
            fileservice.addFile(new FIleDetail(fileId, loggedUserID, file.getOriginalFilename(), LocalDate.now()));
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                 CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {
                List<String> idsList = new ArrayList<>();
                for (CSVRecord csvRecord : csvParser) {
                    System.out.println("Prepare Id List");
                    idsList.add(csvRecord.get("New_NIC"));
                    System.out.println(csvRecord.get("New_NIC"));


                }
                List<String> ids = nicService.getIdList(idsList, fileId, loggedUserID);
                for (String id : ids) {
                    wrongIdList.add(new InValied(id, fileId, file.getOriginalFilename()));
                }

                System.out.println(wrongIdList);
            } catch (Exception e) {

            }
        }
        return wrongIdList;
    }

    @GetMapping("get-all")
    public List<NICDetail> getAllDetail() {
        System.out.println("ok");
        try {
            return nicService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("get-detail-by-user")
    public List<NICDetail> getAllByUser(String userId) throws SQLException {
        return nicService.getAllbyId(userId);
    }

    @GetMapping("/list-by-file")
    public List<NICDetail> getDailByFileName(@RequestParam String fileName) {
        try {
            return nicService.getDetailByfile(fileName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/check-file-name")
    public boolean checkFileName(@RequestParam String fileName) throws SQLException {
        return nicService.checkFIleName(fileName);
    }

    @GetMapping("/all-records")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public List<Report> getAllRecords() {
        try {
            return nicService.getReportRecods();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-gender-data")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public Map<String, Integer> getMaleRecords() {
        try {
            return nicService.getMaleRecords();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/age-data")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public Map<String, Integer> getAgedata() {
        try {
            return nicService.getAgeRecords();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-totalUser")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public int getNoOfUsers() {
        try {
            return nicService.getAll().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/report-by-date")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public List<Report> getReportsByDate(@RequestParam LocalDate start,@RequestParam LocalDate end){
        System.out.println("API is Ok");
        try {
            return nicService.getReportsByDate(start,end);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/report-by-file")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")

    public List<Report> getReportsByFilename(@RequestParam String filename){
        try {
            return nicService.getReportsByFile(filename);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-today-EnterData")
    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
    public int getTodayData(){
        System.out.println("NOw here");
        try {
           return nicService.getTodayData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}