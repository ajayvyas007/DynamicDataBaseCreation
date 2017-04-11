package com.ajayvyas.dynamdb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ajayvyas.dynamdb.model.data.DataOfSqliteDB;
import com.ajayvyas.dynamdb.model.table.TableAttribute;
import com.ajayvyas.dynamdb.model.table.TableAttributeDetails;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajay on 1/4/17.
 */

public class DataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private ArrayList<String> allColumns = new ArrayList<String>();


    private void setAllColumns()
    {
        for(int i = 0;MySQLiteHelper.TABLE_ATTRIBUTE.getTableAttributeDetailsList().size()>i;i++)
        {
            allColumns.add(MySQLiteHelper.TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmField().toString());
        }
    }

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        Log.d("DataBaseCreate","DataSourceClass Called");
        setAllColumns();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createComment(DataOfSqliteDB commentobj) {
        ContentValues values = new ContentValues();
        ArrayList<String> comment = new ArrayList<>(commentobj.getCompleteDataIntoString());
        for(int i=0;MySQLiteHelper.TABLE_ATTRIBUTE.getTableAttributeDetailsList().size()>i;i++)
        {
            values.put(MySQLiteHelper.TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(1).getmField(),comment.get(i));
        }
        try {
            database.insert(MySQLiteHelper.TABLE_ATTRIBUTE.getmName(), null, values);
            Log.d("DataBaseCreate","Sucessfully Insert");
        }
        catch (Exception e)
        {
            Log.d("DataBaseCreate",e.getMessage().toString());
        }
    }

}
