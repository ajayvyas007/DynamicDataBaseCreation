package com.ajayvyas.dynamdb.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ajayvyas.dynamdb.R;
import com.ajayvyas.dynamdb.adapter.SwipeListAdapter;
import com.ajayvyas.dynamdb.library.Config;
import com.ajayvyas.dynamdb.library.VolleySingleton;
import com.ajayvyas.dynamdb.model.data.DataOfPhpMySql;
import com.ajayvyas.dynamdb.model.table.TableAttribute;
import com.ajayvyas.dynamdb.model.table.TableAttributeDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RequestQueue volley;
    private  static TableAttribute tableAttribute=null;
    private List<DataOfPhpMySql> dataList = new ArrayList<DataOfPhpMySql>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListAdapter adapter;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThreadAjay",Thread.currentThread().getName().toString());
        setContentView(R.layout.activity_home_screen);
        volley = VolleySingleton.getInstance(HomeScreen.this).getRequestQueue();
        /*getData();*/
        next  = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,SecoundActivity.class));
            }
        });

        listView = (ListView)findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);



    }


    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
        adapter = new SwipeListAdapter(getBaseContext(), dataList);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        getUserData();
                                    }
                                }
        );
    }

    private void getUserData()
    {
        swipeRefreshLayout.setRefreshing(true);
        volley.add(new JsonArrayRequest(Config.GET_DATA,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ThreadAjay",Thread.currentThread().getName().toString());
                Log.d("VAlue",response.toString());
                //Toast.makeText(HomeScreen.this,response.toString(),Toast.LENGTH_LONG).show();

                try {
                        /*JSONArray jsonArray = new JSONArray(response);
                        Log.d("VAlueVAlue",response.toString());
*/


                    for(int i=0;i<response.length();i++){

                        JSONObject jresponse =response.getJSONObject(i);
                        String id =jresponse.getString("id");
                        String emp_id = jresponse.getString("emp_id");
                        String name = jresponse.getString("name");
                        String sex  = jresponse.getString("sex");
                        DataOfPhpMySql obj = new DataOfPhpMySql(id,emp_id,name,sex);
                        dataList.add(i,obj);

                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VAlue",error.toString());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }));
    }

    

    @Override
    public void onRefresh() {
        getUserData();
    }


}
