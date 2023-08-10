package service;

import model.User;
import DAO.*;

import java.sql.SQLException;

public class Userservice {
    public static Integer saveUser(User user){
        try{
            if(userDAO.isExist(user.getEmail())){
                return 0;
            }else{
                return userDAO.saveUser(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
//suforhuasvndlnib