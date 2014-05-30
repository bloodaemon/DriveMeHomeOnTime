package com.vehicle.drivemehomeontime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class RegisterCustomerEmployee extends Activity
{
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
        driver_spinner = (Spinner) findViewById(R.id.driver_spinner_Register_Customer_Employee);
        driver_spinner
                .setOnItemSelectedListener(new CustomOnItemSelectedListener());
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

}
