package com.ajayvyas.dynamdb.model.table;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajay on 30/3/17.
 */

public class TableAttribute implements Parcelable{
    private int mId;
    private String mName;
    private List<TableAttributeDetails> tableAttributeDetailsList=new ArrayList<TableAttributeDetails>();

    public TableAttribute(int id ,String name,List<TableAttributeDetails> list)
    {
        setmId(id);
        setmName(name);
        setTableAttributeDetailsList(list);
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public List<TableAttributeDetails> getTableAttributeDetailsList() {
        return tableAttributeDetailsList;
    }

    public void setTableAttributeDetailsList(List<TableAttributeDetails> tableAttributeDetailsList) {
        this.tableAttributeDetailsList = tableAttributeDetailsList;
    }

    protected TableAttribute(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        tableAttributeDetailsList = in.createTypedArrayList(TableAttributeDetails.CREATOR);
    }

    public static final Creator<TableAttribute> CREATOR = new Creator<TableAttribute>() {
        @Override
        public TableAttribute createFromParcel(Parcel in) {
            return new TableAttribute(in);
        }

        @Override
        public TableAttribute[] newArray(int size) {
            return new TableAttribute[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeTypedList(tableAttributeDetailsList);
    }
}
