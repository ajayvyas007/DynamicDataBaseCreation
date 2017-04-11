package com.ajayvyas.dynamdb.model.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ajay on 2/4/17.
 */

public class DataOfPhpMySql implements Parcelable{
    private String mId;
    private String mEmployeeId;
    private String mName;
    private String mSex;

    public DataOfPhpMySql(String id,String empid,String name,String sex)
    {
        setmId(id);
        setmEmployeeId(empid);
        setmName(name);
        setmSex(sex);
    }

    protected DataOfPhpMySql(Parcel in) {
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

    public static final Creator<DataOfPhpMySql> CREATOR = new Creator<DataOfPhpMySql>() {
        @Override
        public DataOfPhpMySql createFromParcel(Parcel in) {
            return new DataOfPhpMySql(in);
        }

        @Override
        public DataOfPhpMySql[] newArray(int size) {
            return new DataOfPhpMySql[size];
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
}
