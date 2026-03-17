package org.mobios.repository;

import org.mobios.db.DbConnnection;
import org.mobios.model.FIleDetail;
import org.mobios.model.NICDetail;
import org.mobios.model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NICRepository {



    public void saveNicDetail(NICDetail idDetail){
        Connection connection = null;
        try {
            connection = DbConnnection.getInstance().getConnection();
            System.out.println("COnnection OK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(idDetail);

        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement("INSERT INTO nicDetail VALUES(?,?,?,?,?)");
            pstm.setObject(1,idDetail.getNicNumber());
            pstm.setObject(2,idDetail.getUserId());
            pstm.setObject(3,idDetail.getFileId());
            pstm.setObject(4,idDetail.getGender());
            pstm.setObject(5,idDetail.getBirthDay());

            System.out.println(pstm.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public List<NICDetail> getAll() throws SQLException {
       Connection connection =  DbConnnection.getInstance().getConnection();
       PreparedStatement pstm = connection.prepareStatement("SELECT * FROM nicDetail");
       ResultSet resultSet =  pstm.executeQuery();
       resultSet.next();
       List<NICDetail> detailNIC = new ArrayList<>();
       while (resultSet.next()){
            detailNIC.add(new NICDetail(resultSet.getNString(1),resultSet.getNString(2),resultSet.getNString(3),resultSet.getNString(4),resultSet.getDate(5).toLocalDate()));
       }

        return detailNIC;
    }

    public List<NICDetail> getAllById(String userId) throws SQLException {
        Connection connection =  DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM nicDetail WHERE ID = ?");
        pstm.setObject(1,userId);
        ResultSet resultSet =  pstm.executeQuery();
        resultSet.next();
        List<NICDetail> detailNIC = new ArrayList<>();
        while (resultSet.next()){
            detailNIC.add(new NICDetail(resultSet.getNString(1),resultSet.getNString(2),resultSet.getNString(3),resultSet.getNString(4),resultSet.getDate(5).toLocalDate()));
        }

        return detailNIC;
    }

    public List<FIleDetail> getAllfiles() throws SQLException {
        Connection connection =  DbConnnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM fileDetail");
        ResultSet resultSet =  pstm.executeQuery();
        resultSet.next();
        List<FIleDetail> fIleDetailList = new ArrayList<>();
        while (resultSet.next()){
            fIleDetailList.add(new FIleDetail(resultSet.getNString(1),resultSet.getString(2),resultSet.getNString(3),resultSet.getDate(4).toLocalDate()));
        }

        return fIleDetailList;
    }

    public List<String> getReportBydate(LocalDate startDate, LocalDate endDate) throws SQLException {
        Connection connection = DbConnnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("Select fileId from fileDetail where uploadDate between ? AND ?");
        pstm.setObject(1,startDate);
        pstm.setObject(2,endDate);
        List<String> fileIds = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            fileIds.add(resultSet.getString(1));
        }
        return fileIds;


    }
}
