package org.mobios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InValied {
    private String nic;
    private String fileName;
    private String fileId;
}
