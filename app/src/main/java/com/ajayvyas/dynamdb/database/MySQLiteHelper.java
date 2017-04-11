package com.ajayvyas.dynamdb.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ajayvyas.dynamdb.model.table.TableAttribute;

/**
 * Created by ajay on 1/4/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static TableAttribute TABLE_ATTRIBUTE;


    private static final String DATABASE_NAME = "productrx.db";
    private static final int DATABASE_VERSION = 1;

    private static String QUERY = null;


    private static void createTableQuery() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE " + " productrx"+ "( ");
        for (int i=0;TABLE_ATTRIBUTE.getTableAttributeDetailsList().size()>i;i++)
        {
            if(i == TABLE_ATTRIBUTE.getTableAttributeDetailsList().size()-1)
            {
                buffer.append(" "+TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmField()+" "
                        +TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmType()+" "
                        +TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmExtra());
            }
            else
            {
                buffer.append(" "+TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmField()+" "
                        +TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmType()+" "
                        +TABLE_ATTRIBUTE.getTableAttributeDetailsList().get(i).getmExtra()+",");
            }

        }
        buffer.append(" );");
        QUERY = buffer.toString();
        Log.d("createTableQuerycall",QUERY);
    }

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        createTableQuery();
        Log.d("DataBaseCreate","MySQLiteHelper Called"+QUERY);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try
        {
            database.execSQL(QUERY);

            Log.d("DataBaseCreate","Sucessfully Created");
        }
        catch (Exception e)
        {
            Log.d("DataBaseCreate",e.getMessage().toString());
        }

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRIBUTE.getmName());
        onCreate(db);
    }
}
