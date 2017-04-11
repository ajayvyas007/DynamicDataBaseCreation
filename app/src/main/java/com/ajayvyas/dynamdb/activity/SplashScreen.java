package com.ajayvyas.dynamdb.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ajayvyas.dynamdb.R;
import com.ajayvyas.dynamdb.createdbfirst.CheckDB;
import com.ajayvyas.dynamdb.database.MySQLiteHelper;
import com.ajayvyas.dynamdb.library.Config;
import com.ajayvyas.dynamdb.library.VolleySingleton;
import com.ajayvyas.dynamdb.model.table.TableAttribute;
import com.ajayvyas.dynamdb.model.table.TableAttributeDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private RequestQueue volley;
    private TableAttribute tableAttribute;
    private CheckDB session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        volley = VolleySingleton.getInstance(SplashScreen.this).getRequestQueue();
        session=new CheckDB(getApplicationContext());
        if(session.checkSubmit())
        {
            getData();
            Log.d("DataBaseCreate","SplashScreen getData Called");
        }
        else {
            Intent intent=new Intent(SplashScreen.this,HomeScreen.class);
            //intent.putExtra("table",tableAttribute);
            startActivity(intent);
            Log.d("DataBaseCreate","Schema Aavailable");
        }

    }

    private void getData()
    {
        volley.add(new JsonArrayRequest(Config.APP_SECEMA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Thread",Thread.currentThread().getName().toString());
                Log.d("VAlue",response.toString());

                try {

                    TableAttributeDetails tableAttributeDetails;
                    List<TableAttributeDetails> tableAttributeDetailsList=new ArrayList<TableAttributeDetails>();
                    for(int i=0;i<response.length();i++){

                        JSONObject jresponse =response.getJSONObject(i);
                        String Field =jresponse.getString("Field");
                        String Type = jresponse.getString("Type");
                        String Collation = jresponse.getString("Collation");
                        String Null = jresponse.getString("Null");
                        String Key = jresponse.getString("Key");
                        String Default = jresponse.getString("Default");
                        String Extra = jresponse.getString("Extra");
                        String Privileges = jresponse.getString("Privileges");
                        String Comment = jresponse.getString("Comment");
                        Log.d("nickname",Field);
                        tableAttributeDetails = new TableAttributeDetails(Field,Type,Collation,Null,Key,Default,Extra,Privileges,Comment);
                        tableAttributeDetailsList.add(i,tableAttributeDetails);
                    }

                    tableAttribute = new TableAttribute(1,"survey",tableAttributeDetailsList);
                    if(tableAttribute!=null)
                    {
                        MySQLiteHelper.TABLE_ATTRIBUTE = tableAttribute;
                        Intent intent=new Intent(SplashScreen.this,HomeScreen.class);
                        //intent.putExtra("table",tableAttribute);
                        startActivity(intent);
                        session.submitSuccess();
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VAlue",error.toString());
                        Log.d("DataBaseCreate","VolleyErreo"+error.toString());
                    }
                }));

    }


}
