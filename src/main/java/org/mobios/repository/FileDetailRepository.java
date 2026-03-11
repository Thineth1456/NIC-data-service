package org.mobios.repository;

import org.mobios.db.DbConnnection;
import org.mobios.model.FIleDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDetailRepository {
    Connection connnection = DbConnnection.getInstance().getConnection();

    public FileDetailRepository() throws SQLException {
    }

    public List<String> getLastId() throws SQLException {
       PreparedStatement pstm =  connnection.prepareStatement("Select fileId from fileDetail");
       ResultSet resultSet =pstm.executeQuery();
       resultSet.next();
       List<String> fileIds = new ArrayList<>();
       while(resultSet.next()){
           fileIds.add(resultSet.getString(1));
       }
       return fileIds;
    }

    public void saveFileDetail(FIleDetail fIleDetail) throws SQLException {
        PreparedStatement pstm =  connnection.prepareStatement("INSERT INTO fileDetail VALUES(?,?,?,?)");
        pstm.setObject(1,fIleDetail.getFileId());
        pstm.setObject(2,fIleDetail.getUserId());
        pstm.setObject(3,fIleDetail.getFileName());
        pstm.setObject(4,fIleDetail.getUploadDate());
    }
}
