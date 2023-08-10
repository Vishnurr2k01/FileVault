package views;
import DAO.*;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.Userservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void  WelcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("welcome");
        System.out.println("1 for login");
        System.out.println("2 for register");
        System.out.println("0 for exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch ( IOException e) {
            e.printStackTrace();
        }

        switch (choice){
            case 1 : login(); break;
            case 2: signup();break;
            case 0 :System.exit(0);
        }

    }
    private void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email");
        String email = sc.nextLine();
        try{
            if (userDAO.isExist(email)){
                String generatedOTp = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, generatedOTp);
                System.out.println("Enter OTP");
                String otp = sc.nextLine();
                if(otp.equals(generatedOTp)){
                    new UserView(email).home();
                    System.out.println("Welcome");
                }else{
                    System.out.println("Wrong OTP");
                }
            }else{
                System.out.println("User not found");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void signup(){
    Scanner sc = new Scanner(System.in);
        System.out.println("ENter name");
        String name = sc.nextLine();
        System.out.println("Enter email");
        String email = sc.nextLine();
        String generatedOTp = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, generatedOTp);
        System.out.println("Enter OTP");
        String otp = sc.nextLine();
        if(otp.equals(generatedOTp)){
            User user = new User(name,email);
            int res = Userservice.saveUser(user);
            switch (res){
                case 0:
                    System.out.println("User created");
                    break;
                case 1:
                    System.out.println("User already exist");
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
            System.out.println("Welcome");
        }else{
            System.out.println("Wrong OTP");
        }

    }


}
