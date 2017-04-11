package com.ajayvyas.dynamdb.model.table;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ajay on 30/3/17.
 */

public class TableAttributeDetails implements Parcelable {

    private String mField;
    private String mType;
    private String mCollection;
    private String mNull;
    private String mKey;
    private String mDefault;
    private String mExtra;
    private String mPrivalleges;
    private String mCommit;

    public TableAttributeDetails(String field,String type, String collection,String nullValue,String key,String defaultValue,String extra,String privallege,String commit)
    {
        setmField(field);
        setmType(type);
        setmCollection(collection);
        setmNull(nullValue);
        setmKey(key);
        setmDefault(defaultValue);
        setmExtra(extra);
        setmPrivalleges(privallege);
        setmCommit(commit);
    }

    protected TableAttributeDetails(Parcel in) {
        mField = in.readString();
        mType = in.readString();
        mCollection = in.readString();
        mNull = in.readString();
        mKey = in.readString();
        mDefault = in.readString();
        mExtra = in.readString();
        mPrivalleges = in.readString();
        mCommit = in.readString();
    }

    public static final Creator<TableAttributeDetails> CREATOR = new Creator<TableAttributeDetails>() {
        @Override
        public TableAttributeDetails createFromParcel(Parcel in) {
            return new TableAttributeDetails(in);
        }

        @Override
        public TableAttributeDetails[] newArray(int size) {
            return new TableAttributeDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mField);
        dest.writeString(mType);
        dest.writeString(mCollection);
        dest.writeString(mNull);
        dest.writeString(mKey);
        dest.writeString(mDefault);
        dest.writeString(mExtra);
        dest.writeString(mPrivalleges);
        dest.writeString(mCommit);
    }

    public String getmField() {
        return mField;
    }

    public void setmField(String mField) {
        this.mField = mField;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmCollection() {
        return mCollection;
    }

    public void setmCollection(String mCollection) {
        this.mCollection = mCollection;
    }

    public String getmNull() {
        return mNull;
    }

    public void setmNull(String mNull) {
        this.mNull = mNull;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    public String getmDefault() {
        return mDefault;
    }

    public void setmDefault(String mDefault) {
        this.mDefault = mDefault;
    }

    public String getmExtra() {
        return mExtra;
    }

    public void setmExtra(String mExtra) {
        this.mExtra = mExtra;
    }

    public String getmPrivalleges() {
        return mPrivalleges;
    }

    public void setmPrivalleges(String mPrivalleges) {
        this.mPrivalleges = mPrivalleges;
    }

    public String getmCommit() {
        return mCommit;
    }

    public void setmCommit(String mCommit) {
        this.mCommit = mCommit;
    }
}
