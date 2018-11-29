//package com.example.philong.banhang.Activity;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.philong.banhang.Adapter.Adapter_Product_Update;
//import com.example.philong.banhang.Adapter.Adapter_Product_Update;
//import com.example.philong.banhang.Objects.Product;
//import com.example.philong.banhang.R;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Update_All_Product extends AppCompatActivity {
//    //khai báo ArrayList
//    ArrayList<Product> menu_updatesArrayList=new ArrayList<>();
//
//    //khai bao url
//    MainActivity mainActivity = new MainActivity();
//    String urlDeleteDataMenu = mainActivity.urlIPAddress + "/GraceCoffee/deleteProduct.php";
//
//    //Khai báo Adapter
//    Adapter_Product_Update menu_adapter_update;
//
//    //Khai báo recyclerView
//    RecyclerView recyclerViewUpdateMenu;
//
//    //khai báo các nút
//    Button btn_UpdateMenu_Add;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_menu);
//        anhXa();
//        SetupForRecyclerView();
//        xuLyEvent();
//        GetDataMenu(MainActivity.urlGetDataProduct);
//    }
//
//
////    public void GetDataMenu (String url){
////        RequestQueue requestQueue= Volley.newRequestQueue(this);
////        //GET để lấy xuống
////        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
////                new Response.Listener<JSONArray>() {
////                    @Override
////                    //khi doc duoc json
////                    public void onResponse(JSONArray response) {
////                        menu_updatesArrayList.clear();
////                        for (int i=0;i<response.length();i++){
////                            try {
////                                JSONObject object = response.getJSONObject(i);
////                                menu_updatesArrayList.add(new Product(
////                                        object.getInt("ID"),
////                                        object.getString("Ten"),
////                                        object.getInt("Gia")//trùng với định nghĩa contructor của php $this->ID
////                                ));
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////                        }
////                        menu_adapter_update.notifyDataSetChanged();
////                    }
////                },
////                //khi doc json bi loi
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        Toast.makeText(Update_All_Product.this, error.toString(), Toast.LENGTH_SHORT).show();
////                    }
////                });
////        requestQueue.add(jsonArrayRequest);
////    }
//
//    public void DeleteStudent(final int idMon){
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlDeleteDataMenu, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.trim().equals("success")){
//                    Toast.makeText(Update_All_Product.this, "XoaThanhCong", Toast.LENGTH_SHORT).show();
//                    GetDataMenu(MainActivity.urlGetDataProduct);
//                }
//                else Toast.makeText(Update_All_Product.this, "loi xoa", Toast.LENGTH_SHORT).show();
//            }
//        }
//                , new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Update_All_Product.this, "Loi xoa", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<>();
//                params.put("idMon", String.valueOf(idMon));
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
//
//    public void anhXa(){
//        recyclerViewUpdateMenu = findViewById(R.id.recycler_view_menu);
//        btn_UpdateMenu_Add=findViewById(R.id.button_UpdateMenu_add);
//
//    }
//
//    void xuLyEvent(){
//        btn_UpdateMenu_Add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Update_All_Product.this,Update_All_Product_Insert.class));
//
//            }
//        });
//
//    }
//
//    void SetupForRecyclerView(){
//        //setup cho recyclerviewMain
//        recyclerViewUpdateMenu.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
//        recyclerViewUpdateMenu.setLayoutManager(layoutManager);
//
//        //setup gan adapter for recyclerviewMain
//        menu_adapter_update=new Adapter_Product_Update(menu_updatesArrayList,getApplicationContext(),this);
//        recyclerViewUpdateMenu.setAdapter(menu_adapter_update);
//    }
//
//    //setup menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_update,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    //setup menu
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==R.id.menu_Coffee)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductCoffee);
//        }
//        if(item.getItemId()==R.id.menu_cannedWater)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductCannedWater);
//        }
//        if(item.getItemId()==R.id.menu_bottledWater)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductBottledWater);
//        }
//        if(item.getItemId()==R.id.menu_tea)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductTea);
//        }
//        if(item.getItemId()==R.id.menu_fruit)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductFruit);
//        }
//        if(item.getItemId()==R.id.menu_fastFood)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductFastFood);
//        }
//        if(item.getItemId()==R.id.menu_other)
//        {
//            GetDataMenu(MainActivity.urlGetDataProductOther);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}
