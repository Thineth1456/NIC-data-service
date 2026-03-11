package org.mobios.service;

import org.mobios.model.NICDetail;
import org.mobios.repository.NICRepository;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NicService {
    public void getIdList(List<String> nicNumberList,String fileId,String userid) throws SQLException {

        NICRepository nicRepo = new NICRepository();

        for(String nicNumber:nicNumberList){
            char [] userIdChar = nicNumber.toCharArray();
            if(userIdChar[userIdChar.length-1]=='V'||userIdChar[userIdChar.length-1]=='X'){
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

                    gender = "FeMale";
                    LocalDate birthday = retrieveBirthday(birthYear,150);
                    NICDetail oneDetail = new NICDetail(nicNumber,userid,fileId,gender,birthday);
                    System.out.println(oneDetail);
                    nicRepo.saveNicDetail(oneDetail);

                }



            }

        }
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
}
