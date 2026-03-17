package org.mobios.service;

import org.mobios.model.FIleDetail;
import org.mobios.model.NICDetail;
import org.mobios.model.Report;
import org.mobios.repository.NICRepository;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

public class NicService {
    NICRepository nicRepo = new NICRepository();
    public List<String> getIdList(List<String> nicNumberList, String fileId, String userid) throws SQLException {

        //NICRepository nicRepo = new NICRepository();
        List<String> invaliedIds = new ArrayList<>();
        for(String nicNumber:nicNumberList){

            char [] userIdChar = nicNumber.toCharArray();
            if(userIdChar[userIdChar.length-1]=='V'||userIdChar[userIdChar.length-1]=='X'){
                if(userIdChar.length==10){
                    String birthYearString = "19"+userIdChar[0]+userIdChar[1];
                    int birthYear = Integer.parseInt(birthYearString);
                    //System.out.println(birthYear);
                    String genCalString = new String(Arrays.copyOfRange(userIdChar,2,5));
                    int genCalValue = Integer.parseInt(genCalString);
                    String gender = "";
                    if(genCalValue>0&&genCalValue<367){
                        gender = "Male";

                        // Create a birthday for Jan 5, 1990
                        LocalDate birthday = retrieveBirthday(birthYear,genCalValue);
                        NICDetail oneDetail = new NICDetail(nicNumber,userid,fileId,gender,birthday);
                        System.out.println(oneDetail);

                        nicRepo.saveNicDetail(oneDetail);

                    }
                    else if(genCalValue>500&&genCalValue<867 ){
                        gender = "Female";
                        LocalDate birthday = retrieveBirthday(birthYear,genCalValue-500);
                        NICDetail oneDetail = new NICDetail(nicNumber,userid,fileId,gender,birthday);
                        System.out.println(oneDetail);
                        nicRepo.saveNicDetail(oneDetail);

                    }
                }
                else{
                    invaliedIds.add(nicNumber);
                }



            }
            else{
                if(userIdChar.length==12){
                    String birthYearString = new String(Arrays.copyOfRange(userIdChar,0,4));

                    int birthYear = Integer.parseInt(birthYearString);
                    String genCalString = new String(Arrays.copyOfRange(userIdChar,4,7));
                    int genCalValue = Integer.parseInt(genCalString);
                    String gender="";
                    if(genCalValue>0&&genCalValue<367){
                        gender = "Male";
                        LocalDate birthday = retrieveBirthday(birthYear,genCalValue);
                        NICDetail oneDetail = new NICDetail(nicNumber,userid,fileId,gender,birthday);
                        System.out.println(oneDetail);
                        nicRepo.saveNicDetail(oneDetail);

                    }
                    else if(genCalValue>500&&genCalValue<867){

                        gender = "Female";
                        LocalDate birthday = retrieveBirthday(birthYear,150);
                        NICDetail oneDetail = new NICDetail(nicNumber,userid,fileId,gender,birthday);
                        System.out.println(oneDetail);
                        nicRepo.saveNicDetail(oneDetail);

                    }
                }
                else {
                    invaliedIds.add(nicNumber);
                }



            }

        }
        return invaliedIds;
    }

    private LocalDate retrieveBirthday(int birthYear, int genCalValue) {
        if (genCalValue-31<0){
            return LocalDate.of(birthYear,Month.JANUARY,genCalValue);
        }
        else if(genCalValue-60<0){
            return LocalDate.of(birthYear,Month.FEBRUARY,genCalValue-31);
        }
        else if(genCalValue-91<0){
            return LocalDate.of(birthYear,Month.MARCH,genCalValue-60);
        }
        else if(genCalValue-121<0){
            return LocalDate.of(birthYear,Month.APRIL,genCalValue-91);
        }
        else if(genCalValue-152<0){
            return LocalDate.of(birthYear,Month.MAY,genCalValue-121);
        }
        else if(genCalValue-182<0){
            return LocalDate.of(birthYear,Month.JUNE,genCalValue-152);
        }
        else if(genCalValue-213<0){
            return LocalDate.of(birthYear,Month.JULY,genCalValue-182);
        }
        else if(genCalValue-244<0){
            return LocalDate.of(birthYear,Month.AUGUST,genCalValue-213);
        }
        else if(genCalValue-274<0){
            return LocalDate.of(birthYear,Month.SEPTEMBER,genCalValue-244);
        }
        else if(genCalValue-305<0){
            return LocalDate.of(birthYear,Month.OCTOBER,genCalValue-274);
        }
        else if(genCalValue-335<0){
            return LocalDate.of(birthYear,Month.NOVEMBER,genCalValue-305);
        }
        else{
            return LocalDate.of(birthYear,Month.DECEMBER,genCalValue-335);
        }
    }

    public List<NICDetail> getAll() throws SQLException {
        return nicRepo.getAll();
    }

    public List<NICDetail> getAllbyId(String userId) throws SQLException {
        return nicRepo.getAllById(userId);
    }

    public List<FIleDetail> getAllFiles() throws SQLException {
        return nicRepo.getAllfiles();
    }

    public List<NICDetail> getDetailByfile(String fileName) throws SQLException {

        for(FIleDetail file:getAllFiles()){
            if (file.getFileName().equals(fileName)){
                return getAllbyId(file.getFileId());
            }
        }
        return null;
    }

    public boolean checkFIleName(String fileName) throws SQLException {
        for(FIleDetail file:getAllFiles()){
            if (file.getFileName().equals(fileName)){
                return true;
            }
        }
        return false;
    }

    public void getUserId(String loggedEmail) throws SQLException {
        for(NICDetail detail:getAll()){

        }
    }

    public List<Report> getReportRecods() throws SQLException {
        List<Report> nicReport = new ArrayList<>();
        for(NICDetail detail:getAll()){
            nicReport.add(new Report(detail.getNicNumber(),detail.getGender(),detail.getBirthDay(),detail.getFileId()));
        }
        return nicReport;
    }

    public Map<String, Integer> getMaleRecords() throws SQLException {
        Map<String,Integer> genMap = new HashMap<>();
        for(NICDetail detail:getAll()){
            String gender = detail.getGender();
            if(!genMap.containsKey(gender)){
                genMap.put(detail.getGender(),1);
            }
            else{
                genMap.put(gender,genMap.get(gender)+1);
            }

        }
        return genMap;
    }

    public Map<String, Integer> getAgeRecords() throws SQLException {
        Map<String,Integer> genMap = new HashMap<>();
        for(NICDetail detail:getAll()){
            LocalDate today = LocalDate.now();
            int age = Period.between(detail.getBirthDay(), today).getYears();
            if(age>60){
                if(!genMap.containsKey("60+")){
                    genMap.put("60+",1);
                }
                else{
                    genMap.put("60+",genMap.get("60+")+1);
                }
            }
            else if(age>45){

                if(!genMap.containsKey("46-60")){
                    genMap.put("46-60",1);
                }
                else{
                    genMap.put("46-60",genMap.get("46-60")+1);
                }
            }
            else if(age>35){

                if(!genMap.containsKey("36-45")){
                    genMap.put("36-45",1);
                }
                else{
                    genMap.put("36-45",genMap.get("36-45")+1);
                }
            }
            else if(age>25){

                if(!genMap.containsKey("26-35")){
                    genMap.put("26-35",1);
                }
                else{
                    genMap.put("26-35",genMap.get("26-35")+1);
                }
            }
            else {
                if(!genMap.containsKey("18-25")){
                    genMap.put("18-25",1);
                }
                else{
                    genMap.put("18-25",genMap.get("18-25")+1);
                }
            }

        }
        return genMap;

    }

    public List<Report> getReportsByDate(LocalDate startDate, LocalDate endDate) throws SQLException {
        List<Report> reports = new ArrayList<>();
        List<String> fileIds=  nicRepo.getReportBydate(startDate,endDate);

        for(NICDetail nicDetail:getAll()){
            if(fileIds.contains(nicDetail.getFileId())){
                reports.add(new Report(nicDetail.getNicNumber(),nicDetail.getGender(),nicDetail.getBirthDay(),nicDetail.getFileId()));
            }
        }
        return reports;
    }

    public List<Report> getReportsByFile(String fileName) throws SQLException {
        List<Report> reports = new ArrayList<>();
        for(FIleDetail fIleDetail:getAllFiles()){
            if (fIleDetail.equals(fileName)){
                String fileId = fIleDetail.getFileId();
                for(NICDetail nicDetail:getAll()){
                    if(nicDetail.equals(fileId)){
                        reports.add(new Report(nicDetail.getNicNumber(),nicDetail.getGender(),nicDetail.getBirthDay(),nicDetail.getFileId()));
                    }
                }
            }
        }
        return reports;
    }

    public int getTodayData() throws SQLException {
        LocalDate today = LocalDate.now();
        int todayCount=0;
        List<String> fileList = new ArrayList<>();
        for(FIleDetail fIleDetail:getAllFiles()){
            System.out.println(fIleDetail);
            if(fIleDetail.getUploadDate().equals(today)){
                fileList.add(fIleDetail.getFileId());
                System.out.println(fileList);
            }
        }

        for(NICDetail detail:getAll()){
            if(fileList.contains(detail.getFileId())){
                todayCount++;
            }
        }
      return   todayCount;
    }
}
