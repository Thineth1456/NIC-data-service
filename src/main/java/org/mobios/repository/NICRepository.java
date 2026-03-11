package org.mobios.repository;

import org.mobios.db.DbConnnection;
import org.mobios.model.NICDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
