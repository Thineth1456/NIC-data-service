package org.mobios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class FIleDetail {
    private String fileId;
    private  String userId;
    private String fileName;
    private LocalDate uploadDate;
}
