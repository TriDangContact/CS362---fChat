/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tridang
 */
public class Contact {
    private final int PHONE_MIN_LENGTH = 7;
    private final int PHONE_MAX_LENGTH = 10;
    private final int ID_MIN_LENGTH = 3;
    private final int ID_MAX_LENGTH = 15;
    
    private int contact_key;
    private String contact_id, contact_email, contact_phone, contact_status;
    
    Contact(){};
    
    Contact(String id){
        contact_id = id;
        contact_status = "Not Available";
    }
    
    public int getKey(){
        return contact_key;
    }
    public void setKey(int key){
        contact_key = key;
    }
    public String getContactPhone(){
        return contact_phone;
    }
    public void setContactPhone(String phone){
        if (phone.length() < PHONE_MIN_LENGTH || phone.length() > PHONE_MAX_LENGTH) {
            System.out.println("Phone must be between " +PHONE_MIN_LENGTH+ " to " +PHONE_MAX_LENGTH+ " digits.");
        }
        else {
            contact_phone = phone;
        }
    }
    public String getContactID(){
        return contact_id;
    }
    public void setContactID(String id){
        if (id.length() < ID_MIN_LENGTH || id.length() > ID_MAX_LENGTH){
            System.out.println("Contact ID must be between " +ID_MIN_LENGTH+ " to " +ID_MAX_LENGTH+ " digits.");
        }
        else {
            contact_id = id;
        }
        
    }
    public String getContactEmail(){
        return contact_email;
    }
    public void setContactEmail(String email){
        contact_email = email;
    }
    public String getContactStatus(){
        return contact_status;
    }
    public void setContactStatus(String status){            //should ideally be set by the contact using setStatus, but included here for simulation purposes
        contact_status = status;
    }
    
    
}
