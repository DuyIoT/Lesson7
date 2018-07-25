package rekkeitrainning.com.lesson7.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import rekkeitrainning.com.lesson7.R;
import rekkeitrainning.com.lesson7.model.Contact;


/**
 * Created by hoang on 7/16/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
    Context mContext;
    ArrayList<Contact> mListContact;
    ItemClickListener mClickListener;

    public ArrayList<Contact> getmListContact() {
        return mListContact;
    }

    public ContactsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmListContact(ArrayList<Contact> mListContact) {
        this.mListContact = mListContact;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact_list, parent, false);
        return new ContactViewHolder(view);
    }

    void loadItems(ArrayList<Contact> mListContact) {
        this.mListContact = mListContact;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(position);
        Contact mContact = mListContact.get(position);
        holder.tv_name.setText(mContact.getNameContact());
        holder.tv_phone.setText(mContact.getPhoneNumber());
        holder.chk_delete.setOnCheckedChangeListener(null);
        holder.chk_delete.setChecked(mContact.isCheck());
        holder.chk_delete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int index = 0;
                mListContact.get(position).setCheck(isChecked);
                for (index = 0; index < mListContact.size(); index++) {
                    if (mListContact.get(index).isCheck()) {
                        break;
                    }
                }
                if (index == mListContact.size()) {
                    mClickListener.nullItemDelete();
                    loadItems(mListContact);
                } else {
                    mClickListener.itemDelete();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListContact != null ? mListContact.size() : 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tv_name;
        TextView tv_phone;
        CheckBox chk_delete;

        public ContactViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tvNameContact);
            tv_phone = itemView.findViewById(R.id.tvPhone);
            chk_delete = itemView.findViewById(R.id.chkDelete);
            this.setIsRecyclable(false);
            itemView.setOnClickListener(this);
            chk_delete.setVisibility(View.GONE);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            chk_delete.setVisibility(View.VISIBLE);
            chk_delete.setChecked(true);
            mListContact.get(getAdapterPosition()).setCheck(true);
            if (mClickListener != null) {
                mClickListener.onLongClick(v, getAdapterPosition());
            }
            return true;
        }

        public void bind(int position) {
            if (mListContact.get(position).isCheck()){
                chk_delete.setChecked(true);
            }
        }
    }

    public void onItemClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public interface ItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongClick(View view, int position);

        public void nullItemDelete();

        public void itemDelete();
    }
}
