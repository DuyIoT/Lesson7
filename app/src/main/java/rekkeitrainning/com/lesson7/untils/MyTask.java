package rekkeitrainning.com.lesson7.untils;

import java.util.ArrayList;

import rekkeitrainning.com.lesson7.adapter.ContactsAdapter;
import rekkeitrainning.com.lesson7.db.DBContact;
import rekkeitrainning.com.lesson7.model.Contact;

/**
 * Created by hoang on 7/25/2018.
 */

public class MyTask {
    ArrayList<Contact> mListContact;
    ContactsAdapter mContactAdapter;
    Contact mContact;
    DBContact mDbContact;

    public MyTask(ArrayList<Contact> mListContact, ContactsAdapter mContactAdapter, DBContact mDbContact) {
        this.mListContact = mListContact;
        this.mContactAdapter = mContactAdapter;
        this.mDbContact = mDbContact;
    }

    public MyTask(ArrayList<Contact> mListContact, ContactsAdapter mContactAdapter, Contact mContact, DBContact mDbContact) {
        this.mListContact = mListContact;
        this.mContactAdapter = mContactAdapter;
        this.mContact = mContact;
        this.mDbContact = mDbContact;
    }
}
