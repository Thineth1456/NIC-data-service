package org.mobios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Report {
    private String nic;
    private String gender;
    private LocalDate birthday;
    private String filename;
}
