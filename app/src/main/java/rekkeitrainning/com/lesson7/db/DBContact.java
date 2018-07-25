package rekkeitrainning.com.lesson7.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import rekkeitrainning.com.lesson7.model.Contact;

/**
 * Created by hoang on 7/25/2018.
 */

public class DBContact extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_db";
    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_PHONE + " TEXT)";
    public DBContact(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public long insertContact(Contact mContact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mContact.getNameContact());
        values.put(COLUMN_PHONE, mContact.getPhoneNumber());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact mContact = new Contact();
                mContact.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                mContact.setNameContact(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                mContact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                contacts.add(mContact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contacts;
    }


    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateContact(Contact mContact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mContact.getNameContact());
        values.put(COLUMN_PHONE, mContact.getPhoneNumber());

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mContact.getId())});
    }

    public void deleteContact(Contact mContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mContact.getId())});
        db.close();
    }
}
