package com.vehicle.drivemehomeontime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class System_Menu extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system__menu);
    }
    
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.register_System_Menu:
                Intent i = new Intent(getApplicationContext(), 
                        RegisterCustomerEmployee.class);
                startActivity(i);
                break;
            
            case R.id.submit_System_Menu:
                new LoginAccount().execute();
                break;
                
        }
    }
    
    public class LoginAccount extends AsyncTask<Void, Void, Void>
    {
        @Override
        public Void doInBackground(Void ...voids)
        {
            EditText username = (EditText) findViewById(
                    R.id.username_System_Menu);
            EditText password = (EditText) findViewById(
                    R.id.password_System_Menu);
            
            String username_string = username.getText().toString();
            String password_string = password.getText().toString();
            
            Log.d("myTag", "username_string = " + username_string);
            Log.d("myTag", "password_string = " + password_string);
            
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://rszeto.no-ip.biz/login.php");
            
            try
            {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", username_string));
                nameValuePairs.add(new BasicNameValuePair("password", password_string));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                
                Log.d("myTag", "it works up to here");
                
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity);
                
                if(res.contains("success"))
                {
                    Log.d("myTag", "account successfully logged in");
                }
                else
                {
                    Log.d("myTag", "account was not logged in");
                }
            }
            catch(ClientProtocolException e)
            {
                Log.e("myTag", "something went wrong " + e.toString());
            }
            catch(IOException e)
            {
                Log.e("myTag", "something went wrong IO " + e.toString());
            }
            return null;
        }
    }

}
