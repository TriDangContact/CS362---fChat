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

public class User {
    private final int MAX_CL_SIZE = 10;
    private final int ID_MIN_LENGTH = 3;
    private final int ID_MAX_LENGTH = 15;
    private final int PHONE_MIN_LENGTH = 7;
    private final int PHONE_MAX_LENGTH = 10;
    
    private int user_key;
    private String user_id, user_password, user_phone, user_email, user_status;
    private Contact[] user_contacts_db;
    //private Message[] user_messages_db;
    private int current_cl_size;
    private Scanner in;
    
    User(){
        in = new Scanner(System.in);
        user_contacts_db = new Contact[MAX_CL_SIZE];
        for (int i = 0; i < MAX_CL_SIZE; i++){
            Contact newContact = new Contact("Empty");            
            newContact.setContactStatus("Not Available");
            newContact.setContactEmail("Empty");
            newContact.setContactPhone("0000000");
            user_contacts_db[i] = newContact;
        }
        //user_messages_db = new Message[100];
        current_cl_size = 0;
    }
    
    
    public int getKey(){
        return user_key;
    }
    public void setKey(int key){
        user_key = key;
    }
    public String getPhone(){
        return user_phone;
    }
    public void setPhone(String phone){
        if (phone.length() < PHONE_MIN_LENGTH || phone.length() > PHONE_MAX_LENGTH) {
            System.out.println("Phone must be between " +PHONE_MIN_LENGTH+ " to " +PHONE_MAX_LENGTH+ " digits.");
        }
        else {
            user_phone = phone;
        }
    }
    public String getUserID(){
        return user_id;
    }
    public void setUserID(String id){
        if (id.length() < ID_MIN_LENGTH || id.length() > ID_MAX_LENGTH){
            System.out.println("User ID must be between " +ID_MIN_LENGTH+ " to " +ID_MAX_LENGTH+ " digits.");
        }
        else {
            user_id = id;
        }
    }
    public String getPassword(){
        return user_password;
    }
    public void setPassword(String pass){
        if (pass.length() < ID_MIN_LENGTH || pass.length() > ID_MAX_LENGTH){
            System.out.println("User ID must be between " +ID_MIN_LENGTH+ " to " +ID_MAX_LENGTH+ " digits.");
        }
        else {
            user_password = pass;
        }
        
    }
    public String getEmail(){
        return user_email;
    }
    public void setEmail(String email){
        user_email = email;
    }
    public String getUserStatus(){
        return user_status;
    }
    public void setUserStatus(String status){
        user_status = status;
    }
    public Contact[] getContactList(){
        return user_contacts_db;
    }

    //public Message[] getMessagesDB(){
    //    return user_messages_db;
    //}
    
    //check if contact is in list and return position, otherwise return -1
    public int contactPosition(String contact){
        for (int i = 0; i < MAX_CL_SIZE; i++){
            if (user_contacts_db[i].getContactID().equals(contact)){
                //move rest of contacts up the list, essentially deleting the contact
                return i;
            }
        }
        return -1;
    }
    
    public void addContact(){
        boolean canAdd = true;
        
        if (current_cl_size == MAX_CL_SIZE) {
            System.out.println("Contact list full! Please delete a contact to add more.");
        }
        else {
            String command;
            System.out.println("Adding new contact.");
            System.out.print("New Contact ID: ");
            command = in.nextLine();
            //check if duplicate contact already exist
            for (int i = 0; i < MAX_CL_SIZE; i++){
                if (user_contacts_db[i].getContactID().equals(command)) {
                    System.out.println("Contact already exists in Contact List!");
                    canAdd = false;
                }
            }
            if (canAdd){
                Contact new_contact = new Contact(command);
                user_contacts_db[current_cl_size] = new_contact;
                current_cl_size++;
                System.out.println("Contact added!");
            }
        }
    }
    
    public void viewContact(){
        if (current_cl_size == 0){
            System.out.println("Contact list is empty!");
        }
        else {                         
            String command;
            int found_position;
            System.out.print("View Contact: ");
            command = in.nextLine();            
            //if found, then move every contact up one position and add an empty contact at last position
            if ((found_position = contactPosition(command)) >= 0){
                boolean done = false;
                while(!done){
                    System.out.println("////////View Contact////////");
                    System.out.println("Contact ID: " +user_contacts_db[found_position].getContactID());
                    System.out.println("Contact Email: " +user_contacts_db[found_position].getContactEmail());
                    System.out.println("Contact Phone: " +user_contacts_db[found_position].getContactPhone());
                    System.out.println("Options: GO BACK");
                    command = in.nextLine();
                    if (command.equals("GO BACK")) {
                        done = true;
                    }
                }
            }
            else {
                System.out.println("Cannot view! Contact doesn't exist.");
            }
        }
    }
    
    public void editContact(){
        if (current_cl_size == 0){
            System.out.println("Contact list is empty!");
        }
        else {                         
            String command;
            int found_position;
            System.out.print("Edit Contact: ");
            command = in.nextLine();            
            //if found, then move every contact up one position and add an empty contact at last position
            if ((found_position = contactPosition(command)) >= 0){
                boolean done = false;
                while(!done){
                    System.out.println("////////Edit Contact////////");
                    System.out.println("Contact ID: " +user_contacts_db[found_position].getContactID());
                    System.out.println("Contact Email: " +user_contacts_db[found_position].getContactEmail());
                    System.out.println("Contact Phone: " +user_contacts_db[found_position].getContactPhone());
                    System.out.println("Options: ID | EMAIL | PHONE | GO BACK");
                    command = in.nextLine();
                    if (command.equals("ID")) {
                        System.out.print("New Contact ID: ");
                        command = in.nextLine();
                        user_contacts_db[found_position].setContactID(command);
                    }
                    else if (command.equals("EMAIL")) {
                        System.out.print("New Contact Email: ");
                        command = in.nextLine();
                        user_contacts_db[found_position].setContactEmail(command);
                    }
                    else if (command.equals("PHONE")) {
                        System.out.print("New Contact Phone: ");
                        command = in.nextLine();
                        user_contacts_db[found_position].setContactPhone(command);
                    }
                    else if (command.equals("GO BACK")) {
                        done = true;
                    }
                    else {
                        System.out.println("Please select one of the options.");
                    }
                }
            }
            else {
                System.out.println("Cannot view! Contact doesn't exist.");
            }
        }
    }
    public void deleteContact(){
        
        if (current_cl_size == 0){
            System.out.println("Contact list already empty!");
        }
        else { 
            String command;
            System.out.print("Delete Contact: ");
            command = in.nextLine();
            Contact tempContact = new Contact("Empty");
            //if only 1 contact in list
            if (current_cl_size == 1) {            
                if (user_contacts_db[0].getContactID().equals(command)){              
                    user_contacts_db[0] = tempContact;
                    System.out.println("Contact deletion successful!");
                    current_cl_size = 0; 
                }
                else {
                    System.out.println("Cannot delete! Contact doesn't exist.");
                }
            }
            //if deleting last contact
            else if (user_contacts_db[MAX_CL_SIZE-1].getContactID().equals(command)) {                
                //have to add an Empty contact back into list
                user_contacts_db[MAX_CL_SIZE-1] = tempContact;
                current_cl_size--;
                System.out.println("Contact deletion successful!");
            }
            //if deleting any other contact in between first and last
            else {
                int found_position;
                //if found, then move every contact up one position and add an empty contact at last position
                if ((found_position = contactPosition(command)) >= 0){
                    for (int x = found_position; x < current_cl_size; x++){
                                user_contacts_db[x] = user_contacts_db[x+1];
                            }
                            //have to add an Empty contact back into list
                            user_contacts_db[MAX_CL_SIZE-1] = tempContact;
                            current_cl_size--;
                            System.out.println("Contact deletion successful!");
                }
                else {
                    System.out.println("Cannot delete! Contact doesn't exist.");
                }
            }
        }
    }
}
