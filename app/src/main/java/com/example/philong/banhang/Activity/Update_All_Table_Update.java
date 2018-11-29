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
import com.example.philong.banhang.Objects.Table;
import com.example.philong.banhang.R;

import java.util.HashMap;
import java.util.Map;

public class Update_All_Table_Update extends AppCompatActivity {
    //khai bao cac nut
    EditText editTextUpdateTableNumber;
    Button buttonUpdateTableConfirm,buttonUpdateTableCancel;
    //khai bao id cho method update
    int id=0;
    //url
    MainActivity mainActivity = new MainActivity();
    String urlUpdateTable = mainActivity.urlIPAddress + "/GraceCoffee/updateTable.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_table__update);
        AnhXa();
        XuLySuKien();
    }

    void AnhXa(){
        editTextUpdateTableNumber=findViewById(R.id.edittext_update_number_table);
        buttonUpdateTableConfirm=findViewById(R.id.button_table_update_confirm);
        buttonUpdateTableCancel=findViewById(R.id.button_table_update_cancel);
    }
    void XuLySuKien(){
        //nhận intent từ .. gán vào edit
        Intent intent=getIntent();
        Table table=(Table) intent.getSerializableExtra("soban") ;

        id=table.getId();
        editTextUpdateTableNumber.setText(table.getName());

    buttonUpdateTableConfirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String numberTable=editTextUpdateTableNumber.getText().toString().trim();
            if(numberTable.isEmpty()){
                Toast.makeText(Update_All_Table_Update.this, "Sao rỗng", Toast.LENGTH_SHORT).show();
            }
            else CapNhapTable(urlUpdateTable);
        }
    });
    }
    public void CapNhapTable(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){//success là báo thành công trên php lấy xuống để dùng
                    Toast.makeText(Update_All_Table_Update.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update_All_Table_Update.this,Update_All_Table.class));
                }
                else {

                    Toast.makeText(Update_All_Table_Update.this, "có lỗi gì rồi", Toast.LENGTH_SHORT).show();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update_All_Table_Update.this, "Loi ", Toast.LENGTH_SHORT).show();
                Log.d("AAA","Loi!\n"+error.toString());//chi tiết lỗi
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //tạo map để đẩy lên
                Map<String,String> params=new HashMap<>();
                params.put("idTable", String.valueOf(id));
                params.put("SoBan",editTextUpdateTableNumber.getText().toString().trim());//đẩy lên Json .trim để xóa khoảng trắng đầu,cuối


                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
