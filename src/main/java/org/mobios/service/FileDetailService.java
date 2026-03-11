package org.mobios.service;

import org.mobios.model.FIleDetail;
import org.mobios.repository.FileDetailRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.OptionalInt;

public class FileDetailService {
    public String generateNextId() throws SQLException {
        FileDetailRepository fileDetailRepository = new FileDetailRepository();
        List<String>list =fileDetailRepository.getLastId();
        if (list == null || list.isEmpty()) {
            return "F001";
        }


        OptionalInt max = list.stream()
                .map(id -> id.substring(1))
                .mapToInt(Integer::parseInt)
                .max();

        int nextValue = max.orElse(0) + 1;
        return String.format("F%03d", nextValue);
    }

    public void addFile(FIleDetail fileInfo) throws SQLException {
       FileDetailRepository fileRepo = new FileDetailRepository();
       fileRepo.saveFileDetail(fileInfo);
    }
}
