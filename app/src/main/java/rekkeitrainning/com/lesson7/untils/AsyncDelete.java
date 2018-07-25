package rekkeitrainning.com.lesson7.untils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import rekkeitrainning.com.lesson7.adapter.ContactsAdapter;
import rekkeitrainning.com.lesson7.db.DBContact;
import rekkeitrainning.com.lesson7.model.Contact;

/**
 * Created by hoang on 7/25/2018.
 */

public class AsyncDelete extends AsyncTask<MyTask, Void, Void> {
    DBContact mDbContact;
    ContactsAdapter mContactAdapter;
    ArrayList<Contact> mListContact;
    ProgressDialog mProgressDialog;
    Context mContext;

    public AsyncDelete(Context mContext) {
        this.mContext = mContext;
        mProgressDialog = new ProgressDialog(mContext);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPreExecute() {
        mProgressDialog.setMessage("Vui lòng chờ.....");
        mProgressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(MyTask... myTasks) {
        mDbContact = myTasks[0].mDbContact;
        mContactAdapter = myTasks[0].mContactAdapter;
        mListContact = myTasks[0].mListContact;
        for (int i = 0; i < mListContact.size(); i++) {
            if (mListContact.get(i).isCheck()) {
                mDbContact.deleteContact(mListContact.get(i));
            }
        }
        mListContact = (ArrayList<Contact>) mDbContact.getAllContact();
        mContactAdapter.setmListContact(mListContact);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mContactAdapter.notifyDataSetChanged();
    }
}
