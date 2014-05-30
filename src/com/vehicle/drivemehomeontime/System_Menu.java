package com.vehicle.drivemehomeontime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        }
    }

}
