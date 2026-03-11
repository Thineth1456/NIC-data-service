package org.mobios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NICDetail {
    private String NicNumber;
    private String UserId;
    private String fileId;
    private String gender;
    private LocalDate birthDay;


}
