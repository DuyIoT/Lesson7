package rekkeitrainning.com.lesson7.model;

/**
 * Created by hoang on 7/16/2018.
 */

public class Contact {
    int id;
    String nameContact;
    String phoneNumber;
    boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Contact(int id, String nameContact, String phoneNumber) {
        this.id = id;
        this.nameContact = nameContact;
        this.phoneNumber = phoneNumber;
        isCheck = false;

    }

    public Contact() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact(String nameContact, String phoneNumber) {
        this.nameContact = nameContact;
        this.phoneNumber = phoneNumber;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
