/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tridang
 */

import java.util.Scanner;


public class Application {
    private final int LOG_MIN_LENGTH = 3;
    private final int LOG_MAX_LENGTH = 15;
    private final int LOG_MAX_ATTEMPT = 5;
    private final int LOCKOUT_TIME = 5;
    
    
    private String login_id, login_pass;
    private boolean logged_in;
    private int login_attempts;
    private User new_user;
    Scanner in;
    
    
    
    Application(){
        login_attempts = LOG_MAX_ATTEMPT;
        in = new Scanner(System.in);
        logged_in = false;
    }
    
    public void displayInterface(){
        String command;
        boolean done = false;
        while(!done){
            if (isLoggedIn()){
                System.out.println("////////MAIN MENU////////");
                System.out.println("You are currently logged in.\nPlease select from options below:");
                System.out.println("CONTACT MANAGEMENT | MESSAGES | SHOW STATUS | SET STATUS | USER SUPPORT | SETTINGS | LOGOUT | EXIT");
                command = in.nextLine();
                if (command.equals("CONTACT MANAGEMENT")) {
                    showContacts();
                }
                else if (command.equals("MESSAGES")){
                    System.out.println("Message function not implemented yet!");
                }
                else if (command.equals("SHOW STATUS")){
                    showStatus();
                }
                else if (command.equals("SET STATUS")){
                    setStatus();
                }
                else if (command.equals("USER SUPPORT")){
                    System.out.println("User support function not implemented yet!");
                }
                else if (command.equals("SETTINGS")){
                    System.out.println("Settings function not implemented yet!");
                }
                else if (command.equals("LOGOUT")){
                    logout();
                }
                else if (command.equals("EXIT")){
                    System.exit(0);
                }
            }
            else {
                System.out.println("////////FCHAT////////");
                System.out.println("You are not currently logged in.");
                System.out.println("Options: LOGIN | EXIT");
                command = in.nextLine();
                if (command.equals("LOGIN")) {
                    login();
                }
                else if (command.equals("EXIT")){
                    System.exit(0);
                }
                else {
                    System.out.println("Please choose one of the options.");
                }
            }
        }
    }
    
    public String getLogin(){
        return login_id;
    }
    public void setLogin(String id){
        login_id = id;
    }
    
    public boolean isLoggedIn(){
        return logged_in;
    }
    
    //new account creation process
    public void newAccount(){
        if (isLoggedIn()){
            System.out.println("Currently logged in. Please logout first!");
        }
        else {
            String temp;
            boolean done = false;

            new_user = new User();
            System.out.println("Welcome to fChat!");
            System.out.println("Creating a new account. Limit " +LOG_MIN_LENGTH+ " to " +LOG_MAX_LENGTH+ " characters.");
            while(!done){
                System.out.print("Please enter a new username: ");
                temp = in.nextLine();
                if (temp.length() >= LOG_MIN_LENGTH && temp.length() <= LOG_MAX_LENGTH){
                    new_user.setUserID(temp);
                    System.out.print("Please enter a new password: ");
                    temp = in.nextLine();
                    if (temp.length() > LOG_MIN_LENGTH && temp.length() < LOG_MAX_LENGTH){
                        new_user.setPassword(temp);
                        done = true;
                    }
                }
                else {
                    System.out.println("Your username and password must be " +LOG_MIN_LENGTH+ " to " +LOG_MAX_LENGTH+ " characters.");
                }
            }
        }
    }
    
    public void login(){
        if (isLoggedIn()){
            System.out.println("Currently logged in. Please logout first!");
        }
        else {
            while(!isLoggedIn()){
                if (login_attempts > 0){                
                    String id, pass;
                    System.out.println("////////Login Menu////////");
                    System.out.print("Please enter your username: ");
                    id = in.nextLine();
                    System.out.print("Please enter your password: ");
                    pass = in.nextLine();
                    if (id.equals(new_user.getUserID()) && pass.equals(new_user.getPassword())) {
                        System.out.println("Login Successful!");
                        logged_in = true;
                    }
                    else {
                        System.out.println("Incorrect id or password!");
                        login_attempts--;
                        System.out.println("Login attemps left: " +login_attempts);                                                        
                    }
                }
                else {
                    System.out.println("You are locked out! No more attempts. Please wait " +LOCKOUT_TIME+ " seconds.");
                    lockout();
                }
            }
            
        }
    }
    
    public void logout(){
        System.out.println("Logging out.");
        logged_in = false;
        login_attempts = LOG_MAX_ATTEMPT;
    }
    
    //wait for 5 second before giving user more login attempts
    public void lockout(){
        try {
            Thread.sleep(LOCKOUT_TIME*1000);
            login_attempts = LOG_MAX_ATTEMPT;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void showContacts(){
        boolean done = false;
        String command;
        Contact[] list = new_user.getContactList();        
        while (!done){    
            System.out.println("////////Contact Management////////");
            for (Contact contact : list){
                System.out.println("Contact ID: " +contact.getContactID()+ " (" +contact.getContactStatus()+ ")");
            }
            System.out.println("Options: ADD | VIEW | EDIT | DELETE | GO BACK");
            command = in.nextLine();
            if (command.equals("ADD")){
                new_user.addContact();
            }
            else if (command.equals("VIEW")){
                new_user.viewContact();
            }
            else if (command.equals("EDIT")){
                new_user.editContact();
            }
            else if (command.equals("DELETE")){
                new_user.deleteContact();
            }
            else if (command.equals("GO BACK")){
                done = true;
            }
            else {
                System.out.println("Please select from one of the options");
            }
        }
    }
    
    public void showStatus(){
        String temp;
        temp = new_user.getUserStatus();
        System.out.println("Status: " +temp);
    }
    
    public void setStatus(){
        String temp;
        System.out.print("Set status: ");
        temp = in.nextLine();
        new_user.setUserStatus(temp);
    }
}
