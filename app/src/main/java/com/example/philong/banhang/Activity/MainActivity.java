package com.example.philong.banhang.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.banhang.Adapter.Adapter_Product_Main;
import com.example.philong.banhang.Adapter.Adapter_Table;
import com.example.philong.banhang.Adapter.Adapter_Product_Bill;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Product_Bill;
import com.example.philong.banhang.Objects.Table;
import com.example.philong.banhang.R;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {

        //khai báo chuỗi kết nối
        static String urlIPAddress = "http://10.173.37.231";
        static String urlGetDataTable = urlIPAddress + "/GraceCoffee/getdataTable.php";
        static String urlGetDataProduct = urlIPAddress + "/GraceCoffee/getdataProduct.php";
        static String urlGetDataProductCoffee = urlIPAddress + "/GraceCoffee/getdataProductCoffee.php";
        static String urlGetDataProductCannedWater = urlIPAddress + "/GraceCoffee/getdataProductCannedWater.php";
        static String urlGetDataProductBottledWater = urlIPAddress + "/GraceCoffee/getdataProductBottledWater.php";
        static String urlGetDataProductTea = urlIPAddress + "/GraceCoffee/getdataProductTea.php";
        static String urlGetDataProductFruit = urlIPAddress + "/GraceCoffee/getdataProductFruit.php";
        static String urlGetDataProductFastFood = urlIPAddress + "/GraceCoffee/getdataProductFastFood.php";
        static String urlGetDataProductOther = urlIPAddress + "/GraceCoffee/getdataProductOther.php";

        static int count = 0;

        //khai báo thuộc tính con
        public TextView txt_get_time,textViewNumberTable, textViewTotal;
        Button btn_update_menu, btn_exit;
        Button btn_getProduct_coffee, btn_getProduct_cannedWater, btn_getProduct_bottledWater, btn_getProduct_Tea;
        Button btn_getProduct_Fruit, btn_getProduct_fastFood, btn_getProduct_Other;
        Button buttonMainPrint;
        NotificationBadge notiBadge;
        Button createBill;
        Button btnIncreaseItemBill, btnDecreaseItemBill, btnDeleteItemBill;

        //khai báo recyclerview
        RecyclerView recyclerView_bill, recyclerView_product, recyclerView_table;

        //khai báo arrayList

        ArrayList<Table> tableArrayList = new ArrayList<>();
        ArrayList<Product> ProductArrayList = new ArrayList<>();
        ArrayList<Product_Bill> arrayListBill = new ArrayList<>();


        //khai báo adapter
        Adapter_Product_Bill menu_adapter_update_bill;
        Adapter_Product_Main adapter_product;
        Adapter_Table adapter_table;

        //khai báo 2 thuộc tính để setup grid layout
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("intent_tenmon"));
        SetupRecycerView();
        GetDataTable(urlGetDataTable);
        GetDataProduct(urlGetDataProduct);

        XuLyEvent();

    }


    public void anhXa(){
        //ánh xạ recyclerview
        recyclerView_bill = findViewById(R.id.recycler_view_bill);
        recyclerView_product = findViewById(R.id.recycler_view_product);
        recyclerView_table = findViewById(R.id.recycler_view_table);

        textViewNumberTable=findViewById(R.id.textview_number_table);
        txt_get_time=findViewById(R.id.text_view_getTime);
        textViewTotal = findViewById(R.id.text_view_toTal);


        //ánh xạ button in category
        btn_getProduct_coffee=findViewById(R.id.button_category_coffee);
        btn_getProduct_cannedWater=findViewById(R.id.button_category_cannedWater);
        btn_getProduct_bottledWater=findViewById(R.id.button_category_bottledWater);
        btn_getProduct_Tea=findViewById(R.id.button_category_tea);
//        btn_getProduct_Fruit=findViewById(R.id.button_category_fruit);
//        btn_getProduct_fastFood=findViewById(R.id.button_category_fastFood);
//        btn_getProduct_Other=findViewById(R.id.button_category_other);
        notiBadge = findViewById(R.id.btn_alerm);
        createBill = findViewById(R.id.btn_createBill);

        //ánh xạ button trên tittle
        btn_exit=findViewById(R.id.button_exit);
        btn_update_menu=findViewById(R.id.btn_update_menu);
        buttonMainPrint=findViewById(R.id.button_main_print);

    }

        void XuLyEvent(){
        //xử lý event thời gian
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = df.format(c.getTime());
        txt_get_time.setText(String.valueOf(formattedDate));

        //event timer
            Timer timer=new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    GetDataTable(urlGetDataTable);

                }
            },1000,10000);

        //xử lý button exit
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        //xử lý button update
        btn_update_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Update_All.class));
            }
        });

        //xử lý các button trong category
        btn_getProduct_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataProduct(urlGetDataProductCoffee);
            }
        });

        btn_getProduct_cannedWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataProduct(urlGetDataProductCannedWater);
            }
        });

        btn_getProduct_bottledWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataProduct(urlGetDataProductBottledWater);
            }
        });
        btn_getProduct_Tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataProduct(urlGetDataProductTea);
            }
        });

//        btn_getProduct_Fruit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetDataProduct(urlGetDataProductFruit);
//            }
//        });
//
//        btn_getProduct_fastFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetDataProduct(urlGetDataProductFastFood);
//            }
//        });
//
//        btn_getProduct_Other.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetDataProduct(urlGetDataProductOther);
//            }
//        });

        //xử lý hiện số thông báo
        createBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notiBadge.setNumber(++count);
            }
        });
        notiBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                notiBadge.setNumber(count);
            }
        });




    }

    void SetupRecycerView(){
        //Setup cấu hình cho recycler bill
        recyclerView_bill.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
        recyclerView_bill.setLayoutManager(layoutManager1);

        //Setup cấu hình cho recycler product
        recyclerViewLayoutManager = new GridLayoutManager(context, 1);
        recyclerView_product.setHasFixedSize(true);
        recyclerView_product.setLayoutManager(recyclerViewLayoutManager);

        //Setup cấu hình cho recycler table
        recyclerViewLayoutManager = new GridLayoutManager(context, 5);
        recyclerView_table.setHasFixedSize(true);
        recyclerView_table.setLayoutManager(recyclerViewLayoutManager);

        //Setup gán adapter cho recycler table
        adapter_table=new Adapter_Table(tableArrayList,getApplicationContext(),this);
        recyclerView_table.setAdapter(adapter_table);

        //Setup gán adapter cho recycler bill
//        menu_adapter_update_bill = new Adapter_Product_Bill(arrayListBill,getApplicationContext(), textViewTotal);
//        recyclerView_bill.setAdapter(menu_adapter_update_bill);
//        Intent intent=getIntent();
//        Product product=(Product) intent.getSerializableExtra("databill");

//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle bundle = intent.getBundleExtra("bundle");
//            if (bundle != null) {
//                arrayListBill.add(new Product(bundle.getInt("id"), bundle.getString("name"), bundle.getInt("price")));
//            } else {
//                Toast.makeText(this, "Bundle rỗng", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Intent rỗng", Toast.LENGTH_SHORT).show();
//        }

        //bug
        //arrayListBill.add(new Product(2,"CaPheDen",20000));
        //arrayListBill.add(new Product(2,product.getName(),product.getPrice()));

        //Setup gán adapter cho recycler product
        adapter_product=new Adapter_Product_Main(ProductArrayList,getApplicationContext());
        recyclerView_product.setAdapter(adapter_product);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            int id = intent.getIntExtra("id", 123);
            String name = intent.getStringExtra("name");
            int price = intent.getIntExtra("price",123);
            Toast.makeText(MainActivity.this,name +" "+price ,Toast.LENGTH_SHORT).show();

            int viTri = 0, size = 0;
            boolean check = false;
            for (int i = 0; i<arrayListBill.size(); i++) {
                if (id == arrayListBill.get(i).getId()) {
                    viTri = i;
                    check = true;
                    size = arrayListBill.get(i).getSize();
                    break;
                }
            }
            if (check) {
                arrayListBill.get(viTri).setSize(++size);
            } else {
                arrayListBill.add(new Product_Bill(id, name, price, 1));
            }

            int tong=0;
            for (int i=0; i<arrayListBill.size(); i++) {
                Product_Bill product_bill = arrayListBill.get(i);
                tong += product_bill.getPrice()*product_bill.getSize();
            }
            textViewTotal.setText(String.valueOf(tong));

            menu_adapter_update_bill = new Adapter_Product_Bill(arrayListBill,getApplicationContext(),textViewTotal);
            recyclerView_bill.setAdapter(menu_adapter_update_bill);

            //tính tổng tiền
//            textViewTotal.setText(String.valueOf(totalMoney(arrayListBill)));

        }
    };

    public int totalMoney(ArrayList<Product_Bill> a) {
        int tong=0;
        for (int i=0; i<a.size(); i++) {
            Product_Bill product_bill = a.get(i);
            tong += product_bill.getPrice()*product_bill.getSize();
        }
        return tong;
    }

    public void GetDataProduct (String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    //khi doc duoc json
                    public void onResponse(JSONArray response) {
                        ProductArrayList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                ProductArrayList.add(new Product(
                                        object.getInt("ID"),
                                        object.getString("Ten"),
                                        object.getInt("Gia")//trùng với định nghĩa contructor của php $this->ID

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter_product.notifyDataSetChanged();
                    }
                },
                //khi doc json bi loi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetDataTable (String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    //khi doc duoc json
                    public void onResponse(JSONArray response) {
                        tableArrayList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                tableArrayList.add(new Table(
                                        object.getInt("ID"),//trùng với định nghĩa contructor của php $this->ID
                                        object.getString("Ten")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter_table.notifyDataSetChanged();
                    }
                },
                //khi doc json bi loi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }




}
