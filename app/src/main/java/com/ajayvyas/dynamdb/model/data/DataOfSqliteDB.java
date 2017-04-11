package com.ajayvyas.dynamdb.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ajay on 2/4/17.
 */

public class DataOfSqliteDB implements Parcelable {
    private String mId;
    private String mEmployeeId;
    private String mName;
    private String mSex;
    private ArrayList<String> listOfData = new ArrayList<String>();

    public DataOfSqliteDB(String id,String empid,String name,String sex)
    {
        setmId(id);
        setmEmployeeId(empid);
        setmName(name);
        setmSex(sex);
    }

    protected DataOfSqliteDB(Parcel in) {
        mId = in.readString();
        mEmployeeId = in.readString();
        mName = in.readString();
        mSex = in.readString();
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmEmployeeId() {
        return mEmployeeId;
    }

    public void setmEmployeeId(String mEmployeeId) {
        this.mEmployeeId = mEmployeeId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public static final Creator<DataOfSqliteDB> CREATOR = new Creator<DataOfSqliteDB>() {
        @Override
        public DataOfSqliteDB createFromParcel(Parcel in) {
            return new DataOfSqliteDB(in);
        }

        @Override
        public DataOfSqliteDB[] newArray(int size) {
            return new DataOfSqliteDB[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mEmployeeId);
        dest.writeString(mName);
        dest.writeString(mSex);
    }

    public ArrayList getCompleteDataIntoString()
    {
        listOfData.add(getmId());
        listOfData.add(getmEmployeeId());
        listOfData.add(getmName());
        listOfData.add(getmSex());
        return listOfData;
    }
}
