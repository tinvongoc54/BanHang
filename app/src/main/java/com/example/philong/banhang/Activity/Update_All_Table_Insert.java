package com.example.philong.banhang.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.banhang.R;

import java.util.HashMap;
import java.util.Map;

public class Update_All_Table_Insert extends AppCompatActivity {
    EditText editTextInsertTable;
    Button buttonInsertTableConfirm,buttonInsertTableCancel;

    MainActivity mainActivity = new MainActivity();
    String urlInsertTable= mainActivity.urlIPAddress + "/GraceCoffee/insertTable.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_table);
        AnhXa();
        XuLySuKien();
    }

    void AnhXa(){
        editTextInsertTable=findViewById(R.id.edittext_number_table);
        buttonInsertTableConfirm=findViewById(R.id.button_table_update_insert_confirm);
        buttonInsertTableCancel=findViewById(R.id.button_table_update_insert_cancel);
    }

    void XuLySuKien(){
    buttonInsertTableConfirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String numberTable=editTextInsertTable.getText().toString().trim();
            if(numberTable.isEmpty()){
                Toast.makeText(Update_All_Table_Insert.this, "Không có gì cả", Toast.LENGTH_SHORT).show();
            }
            else InsertTable(urlInsertTable);
        }
    });
    }
    private void InsertTable(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //POST để đấy lên
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//khi insert thành công
                if(response.trim().equals("success")){//success là báo thành công trên php lấy xuống để dùng
                    Toast.makeText(Update_All_Table_Insert.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update_All_Table_Insert.this,Update_All_Table.class));
                }
                else {

                    Toast.makeText(Update_All_Table_Insert.this, "có lỗi gì rồi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {//khi insert thất bại
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_All_Table_Insert.this, "Loi ", Toast.LENGTH_SHORT).show();
                Log.d("AAA","Loi!\n"+error.toString());//chi tiết lỗi
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //tạo map để đẩy lên
                Map<String,String> params=new HashMap<>();
                params.put("numberTable",editTextInsertTable.getText().toString().trim());//đẩy lên Json
                return params;
            }
        };

        requestQueue.add(stringRequest);//add vao

    }
}
