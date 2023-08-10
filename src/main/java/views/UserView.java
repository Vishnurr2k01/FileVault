package views;

import DAO.DataDAO;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;

    public UserView(String email){
        this.email = email;
    }

    public void home(){
        do{
            System.out.println("Welcome " + this.email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide new file");
            System.out.println("Press 3 to show a hidden file");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch){
                case 1:
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("id-------file name");

                        for(Data file:files){
                            System.out.println(file.getId()+"------" + file.getName());
                        }
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                case 2:
                    System.out.println("Etner file path");
                    String path= sc.nextLine();
                    File f = new File(path);
                    Data file =new Data(0,f.getName(),path,this.email);

                    try {
                        DataDAO.hideFile(file);
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case 3:
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFiles(this.email);
                        System.out.println("Print id to show");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidid = false;
                        for(Data file2:files){
                            if(file2.getId() == id){
                                isValidid = true;
                                break;
                            }
                        }
                        if(isValidid){
                            DataDAO.showAgain(id);
                        }
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("id-------file name");
                    for(Data file2:files){
                        System.out.println(file2.getId()+"------" + file2.getName());
                    }                    ;
                    break;
                case 0 :System.exit(0);


            }
        }while (true);

    }
}
