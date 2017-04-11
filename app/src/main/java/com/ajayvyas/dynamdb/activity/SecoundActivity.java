package com.ajayvyas.dynamdb.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ajayvyas.dynamdb.R;
import com.ajayvyas.dynamdb.database.DataSource;
import com.ajayvyas.dynamdb.library.Config;
import com.ajayvyas.dynamdb.library.VolleySingleton;
import com.ajayvyas.dynamdb.model.data.DataOfSqliteDB;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class SecoundActivity extends AppCompatActivity {
    private EditText name,sex;
    private Button submit;
    private RequestQueue volley;
    private RadioGroup mAnswerGenderGroup;
    private RadioButton mAnswer;
    private DataSource datasource;
    private static int mID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);
        //datasource = new DataSource(this);
        volley = VolleySingleton.getInstance(SecoundActivity.this).getRequestQueue();
        name = (EditText)findViewById(R.id.name);
        /*sex = (EditText)findViewById(R.id.sex);*/
        submit = (Button)findViewById(R.id.submit);
        mAnswerGenderGroup=(RadioGroup)findViewById(R.id.radiogroup);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData() {
        int selectedId=mAnswerGenderGroup.getCheckedRadioButtonId();
        mAnswer=(RadioButton)findViewById(selectedId);
        String gender=null;
        DataOfSqliteDB comment = null;
        if(mAnswer.getText().toString().equals("Male"))
        {
            gender = "male";
        }
        else if(mAnswer.getText().toString().equals("Female"))
        {
            gender = "female";
        }

        Random rand = new Random();

        long  emp_id = rand.nextInt(100000) + 1;
        JSONObject send = new JSONObject();
        try {
            send.put("name", name.getText().toString());
            send.put("sex", gender);
            send.put("emp_id",String.valueOf(emp_id));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //comment =new DataOfSqliteDB(String.valueOf(++mID),String.valueOf(emp_id),name.getText().toString(),gender);
        //datasource.createComment(comment);
                /*datasource.createComment(comments[nextInt]);*/
        volley.add(new JsonObjectRequest(Request.Method.POST, Config.SUBMIT_DATA, send, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ThreadAjayPOST",Thread.currentThread().getName().toString());
                Toast.makeText(SecoundActivity.this,"Submit Successful",Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ThreadAjayPOST",error.toString());

                    }
                }));

    }
}
