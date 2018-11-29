package com.example.philong.banhang.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.philong.banhang.R;

public class Update_All extends AppCompatActivity {
    //khai bao cac nut
    Button buttonUpdateMenu,buttonUpdateTable,butonUpdateEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        AnhXa();
        XuLyEvent();
    }
    void AnhXa(){

        buttonUpdateMenu=findViewById(R.id.button_update_menu);
        buttonUpdateTable=findViewById(R.id.button_update_table);
        butonUpdateEmployee=findViewById(R.id.button_update_employee);
    }
    void XuLyEvent(){
//        buttonUpdateMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Update_All.this,Update_All_Product.class));
//            }
//        });

        buttonUpdateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Update_All.this,Update_All_Table.class));
            }
        });

        butonUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Update_All.this,Update_All_Employee.class));
            }
        });

    }

}
