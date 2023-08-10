package DAO;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    public static boolean isExist(String email) throws SQLException {
        Connection connection= MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from Users ");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            String e = rs.getString(1);
            if(e.equals(email)){
                return true;
            }
        }
        return  false;
    }

    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();
        String query = "insert into Users values(default, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
       return ps.executeUpdate();


    }
}
