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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterCustomerEmployee extends Activity
{
    private Spinner state_spinner;
    private Spinner driver_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer_employee);

        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection()
    {
        state_spinner = (Spinner) findViewById(R.id.state_spinner_Register_Customer_Employee);
        driver_spinner = (Spinner) findViewById(R.id.driver_spinner_Register_Customer_Employee);
        
        state_spinner
                .setOnItemSelectedListener(new CustomOnItemSelectedListener());
        
        driver_spinner
                .setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    
    public void submitRegistration(View v)
    {
        switch(v.getId())
        {
            case R.id.submit_button_Register_Customer_Employee:
                new RegisterAccount().execute();
                finish();
                break;
        }
    }

    public class CustomOnItemSelectedListener implements
            OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                View view, int pos, long id)
        {
            // TODO Auto-generated method stub
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0)
        {
            // TODO Auto-generated method stub
        }
    }
    
    public class RegisterAccount extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void ...voids)
        {
            register();
            
            return null;
        }
        
        private void register()
        {
            EditText username = (EditText) findViewById(
                    R.id.username_Register_Customer_Employee);
            EditText password = (EditText) findViewById(
                    R.id.password_Register_Customer_Employee);
            EditText first_name = (EditText) findViewById(
                    R.id.first_name_Register_Customer_Employee);
            EditText last_name = (EditText) findViewById(
                    R.id.last_name_Register_Customer_Employee);
            EditText address = (EditText) findViewById(
                    R.id.address_Register_Customer_Employee);
            EditText city = (EditText) findViewById(
                    R.id.city_Register_Customer_Employee);
            EditText zip_code = (EditText) findViewById(
                    R.id.zip_code_Register_Customer_Employee);
            EditText phone = (EditText) findViewById(
                    R.id.phone_Register_Customer_Employee);
            EditText email = (EditText) findViewById(
                    R.id.email_Register_Customer_Employee);
            EditText drivers_license = (EditText) findViewById(
                    R.id.drivers_license_Register_Customer_Employee);
            
            String username_string = username.getText().toString();
            String password_string = password.getText().toString();
            String first_name_string = first_name.getText().toString();
            String last_name_string = last_name.getText().toString();
            String address_string = address.getText().toString();
            String city_string = city.getText().toString();
            String state_string = state_spinner.getSelectedItem().toString();
            String zip_code_string = zip_code.getText().toString();
            String phone_string = phone.getText().toString();
            String email_string = email.getText().toString();
            String drivers_license_string = drivers_license.getText().toString();
            String driver_type_string = driver_spinner.getSelectedItem().toString();
            
            Log.d("myTag", "username_string = " + username_string);
            Log.d("myTag", "password_string = " + password_string);
            Log.d("myTag", "first_name_string = " + first_name_string);
            Log.d("myTag", "last_name_string = " + last_name_string);
            Log.d("myTag", "address_string = " + address_string);
            Log.d("myTag", "city_string = " + city_string);
            Log.d("myTag", "state_string = " + state_string);
            Log.d("myTag", "zip_code_string = " + zip_code_string);
            Log.d("myTag", "phone_string = " + phone_string);
            Log.d("myTag", "email_string = " + email_string);
            Log.d("myTag", "drivers_license_string = " + drivers_license_string);
            Log.d("myTag", "driver_type_string = " + driver_type_string);
            
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://rszeto.no-ip.biz/register.php");
            
            try
            {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(13);
                nameValuePairs.add(new BasicNameValuePair("username", username_string));
                nameValuePairs.add(new BasicNameValuePair("password", password_string));
                nameValuePairs.add(new BasicNameValuePair("first_name", first_name_string));
                nameValuePairs.add(new BasicNameValuePair("last_name", last_name_string));
                nameValuePairs.add(new BasicNameValuePair("address", address_string));
                nameValuePairs.add(new BasicNameValuePair("city", city_string));
                nameValuePairs.add(new BasicNameValuePair("state", state_string));
                nameValuePairs.add(new BasicNameValuePair("zip_code", zip_code_string));
                nameValuePairs.add(new BasicNameValuePair("phone_number", phone_string));
                nameValuePairs.add(new BasicNameValuePair("email", email_string));
                nameValuePairs.add(new BasicNameValuePair("drivers_license", drivers_license_string));
                nameValuePairs.add(new BasicNameValuePair("account_type", "customer"));
                nameValuePairs.add(new BasicNameValuePair("driver_type", driver_type_string));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                
                Log.d("myTag", "it works up to here");
                
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String res = EntityUtils.toString(entity);
                
                if(res.contains("account successfully created"))
                {
                    Log.d("myTag", "account successfully created");
                }
                else
                {
                    Log.d("myTag", "account was not created");
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
        }
    }

}
