package com.example.philong.banhang.Activity;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.support.v7.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.banhang.Adapter.Adapter_Category_Listview;
import com.example.philong.banhang.Adapter.Adapter_Product_Main;
import com.example.philong.banhang.Adapter.Adapter_Table;
import com.example.philong.banhang.Adapter.Adapter_Product_Bill;
import com.example.philong.banhang.Objects.Bill;
import com.example.philong.banhang.Objects.Bill_Detail;
import com.example.philong.banhang.Objects.Category;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Product_Bill;
import com.example.philong.banhang.Objects.Table;
import com.example.philong.banhang.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {

        //khai báo chuỗi kết nối
        static String urlIPAddress = "http://192.168.199.2";
        static String urlGetDataTable = urlIPAddress + "/GraceCoffee/getdataTable.php";
        static String urlGetDataProduct = urlIPAddress + "/GraceCoffee/getdataProduct.php";
        static String urlGetDataProductCoffee = urlIPAddress + "/GraceCoffee/getdataProductCoffee.php";
        static String urlGetDataProductCannedWater = urlIPAddress + "/GraceCoffee/getdataProductCannedWater.php";
        static String urlGetDataProductBottledWater = urlIPAddress + "/GraceCoffee/getdataProductBottledWater.php";
        static String urlGetDataProductTea = urlIPAddress + "/GraceCoffee/getdataProductTea.php";
        static String urlGetDataProductFruit = urlIPAddress + "/GraceCoffee/getdataProductFruit.php";
        static String urlGetDataProductFastFood = urlIPAddress + "/GraceCoffee/getdataProductFastFood.php";
        static String urlGetDataProductOther = urlIPAddress + "/GraceCoffee/getdataProductOther.php";

        int percent = 0;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("grace");
        DatabaseReference myRefBill = myRef.child("bill");
        DatabaseReference myRefCategory = myRef.child("catalog");
        DatabaseReference myRefProduct = myRef.child("product");

        static int count_notification = 0;
        static int count_category_quantity = 0;
        static int count_product_quantity = 0;
        static int billNumberOfDay = 0;

        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dfH = new SimpleDateFormat("HH:mm");


        //khai báo thuộc tính con
        public TextView txt_get_time,textViewNumberTable, textViewTotal, textViewThanhTien, textViewStaff;
        public EditText editTextPercent;
        Button btn_update_menu, btn_exit;
        Button buttonMainPrint;
        NotificationBadge notiBadge;
        Button createBill;
        Handler mHandler;

        //khai báo recyclerview
        RecyclerView recyclerView_bill, recyclerView_product, recyclerView_table;
        ListView lvCategory;

        //khai báo arrayList

        ArrayList<Table> tableArrayList = new ArrayList<>();
        ArrayList<Product> ProductArrayList = new ArrayList<>();
        ArrayList<Product_Bill> arrayListBill = new ArrayList<>();
        ArrayList<Category> CategoryArrayList = new ArrayList<>();


        //khai báo adapter
        Adapter_Product_Bill menu_adapter_update_bill;
        Adapter_Product_Main adapter_product;
        Adapter_Table adapter_table;
        Adapter_Category_Listview adapter_category_listview;

        //khai báo 2 thuộc tính để setup grid layout
        RecyclerView.LayoutManager recyclerViewLayoutManager;
        Context context;

        static final int MESSAGE_THANH_TIEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        anhXaHandler();


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("intent_tenmon"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverPosition, new IntentFilter("intent_vitrixoabill"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiverCategory, new IntentFilter("intent_category"));


        GetDataTable(urlGetDataTable);
        GetCategory(myRefCategory);

        SetupRecycerView();

        XuLyEvent();

    }

    public void CreateProduct() {
//        Product product1 = new Product("Coffee đen", "Coffee rang xay", "Coffee", 15000);
//        Product product2 = new Product("Coffee sữa", "Coffee rang xay", "Coffee", 20000);
//        Product product3 = new Product("Coffee đen SG", "Coffee rang xay", "Coffee", 20000);
//        Product product4 = new Product("Coffee sữa SG", "Coffee rang xay", "Coffee", 25000);
//        Product product5 = new Product("Trà hoa hồng", "Trà ngon", "Trà đặc biệt", 30000);
//        myRefProduct.push().setValue(product1);
//        myRefProduct.push().setValue(product2);
//        myRefProduct.push().setValue(product3);
//        myRefProduct.push().setValue(product4);
//        myRefProduct.push().setValue(product5);

        Category category = new Category("Machiato", "aaaaaaa");
        myRefCategory.push().setValue(category);
    }

    public void tinhThanhTien() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = MESSAGE_THANH_TIEN; //tạo tên msg
                msg.arg1 = Integer.parseInt(textViewTotal.getText().toString().trim()); //gán giá trị cho msg
                msg.arg2 = Integer.parseInt(editTextPercent.getText().toString().trim());
                mHandler.sendMessage(msg); //gửi vào handler
            }
        });
        thread.start();
    }

    public void anhXaHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_THANH_TIEN:
                        textViewThanhTien.setText(String.valueOf(msg.arg1 - msg.arg1*msg.arg2/100));
                }
            }
        };
    }

    public void anhXa(){
        //ánh xạ recyclerview
        recyclerView_bill = findViewById(R.id.recycler_view_bill);
        recyclerView_product = findViewById(R.id.recycler_view_product);
        recyclerView_table = findViewById(R.id.recycler_view_table);
//        recyclerView_category = findViewById(R.id.recycler_view_category);
        lvCategory = findViewById(R.id.listView_Category);

        textViewNumberTable=findViewById(R.id.textview_number_table);
        txt_get_time=findViewById(R.id.text_view_getTime);
        textViewTotal = findViewById(R.id.text_view_toTal);
        textViewThanhTien = findViewById(R.id.text_view_thanhTien);
        textViewStaff = findViewById(R.id.textView_Staff);
        editTextPercent = findViewById(R.id.editText_perCent);

        notiBadge = findViewById(R.id.btn_alerm);
        createBill = findViewById(R.id.btn_createBill);

        //ánh xạ button trên tittle
        btn_exit=findViewById(R.id.button_exit);
        btn_update_menu=findViewById(R.id.btn_update_menu);
        buttonMainPrint=findViewById(R.id.button_main_print);

    }

    public void XuLyEvent(){
        //xử lý tổng tiền thay đổi
        textViewTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinhThanhTien();
            }
        });

        //xử lý giảm giá thay đổi
        editTextPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editTextPercent.getText().toString().trim().equals("")) {
                    editTextPercent.setText(String.valueOf(0));
                } else if (Integer.parseInt(editTextPercent.getText().toString().trim()) > 100) {
                    editTextPercent.setText(String.valueOf(100));
                }
                tinhThanhTien();
            }
        });


        //xử lý event thời gian
        String formattedDate = df.format(calendar.getTime());
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

//      khi có thay đổi trên firebase sẽ gởi thông báo

        //xử lý hiện số thông báo
        createBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmCreateBill();
            }
        });
        notiBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_notification = 0;
                notiBadge.setNumber(count_notification);
                showPopupMenu(notiBadge);
            }
        });

        editTextPercent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
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


        //Setup gán adapter cho recycler product
        adapter_product=new Adapter_Product_Main(ProductArrayList,getApplicationContext());
        recyclerView_product.setAdapter(adapter_product);

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String category_name = intent.getStringExtra("category");
            String product_name = intent.getStringExtra("name");
            String product_description = intent.getStringExtra("description");
            String product_price = intent.getStringExtra("price");

            int viTri = 0, size = 0;
            boolean check = false;
            for (int i = 0; i<arrayListBill.size(); i++) {
                if (product_name.equalsIgnoreCase(arrayListBill.get(i).getProduct_name())) {
                    viTri = i;
                    check = true;
                    size = arrayListBill.get(i).getSize();
                    break;
                }
            }
            if (check) {
                arrayListBill.get(viTri).setSize(++size);
            } else {
                arrayListBill.add(new Product_Bill(product_name, product_description, category_name, product_price, 1));
            }

            //settext cho tổng tiền
            textViewTotal.setText(String.valueOf(tongTien()));

            menu_adapter_update_bill = new Adapter_Product_Bill(arrayListBill,getApplicationContext(),textViewTotal);
            recyclerView_bill.setAdapter(menu_adapter_update_bill);

        }
    };

    public int tongTien() {
        int tong=0;
        for (int i=0; i<arrayListBill.size(); i++) {
            Product_Bill product_bill = arrayListBill.get(i);
            tong += Integer.parseInt(product_bill.getProduct_price())*product_bill.getSize();
        }
        return tong;
    }

    public BroadcastReceiver mMessageReceiverPosition = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int position = intent.getIntExtra("position", 123);
            arrayListBill.remove(position);

            //settext lại cho tổng tiền
            textViewTotal.setText(String.valueOf(tongTien()));

            menu_adapter_update_bill = new Adapter_Product_Bill(arrayListBill, getApplicationContext(), textViewTotal);
            recyclerView_bill.setAdapter(menu_adapter_update_bill);
        }
    };

    public BroadcastReceiver mMessageReceiverCategory = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String category_name = intent.getStringExtra("category_name");
            GetDataProduct(myRefProduct, category_name);
        }
    };

    //lấy dữ liệu product theo mục category
    public void GetDataProduct(DatabaseReference myRef, final String category) {
        myRef.orderByChild("category_name").equalTo(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //lấy từng dữ liệu theo kiểu Product
                    Product product = snapshot.getValue(Product.class);

                    //lấy các thành phần trong product
                    String product_name = product.getProduct_name();
                    String product_description = product.getProduct_description();
                    String category_name = product.getCategory_name();
                    String product_price = product.getProduct_price();

                    //gán vào arraylist
                    ProductArrayList.add(new Product(product_name, product_description, category_name, product_price));

                }
                adapter_product.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //lấy dữ liệu category
    public void GetCategory(DatabaseReference myRef) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CategoryArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    CategoryArrayList.add(new Category(category.getCatalog_name(), category.getCatalogs_des()));
                }
                //setup gans adapter cho recycler category
                adapter_category_listview = new Adapter_Category_Listview(getApplicationContext(), CategoryArrayList, R.layout.item_view_row_category);
                lvCategory.setAdapter(adapter_category_listview);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void GetLastItem(DatabaseReference myRef) {
        myRef.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    Log.d("checkData1", snapshot.toString());
                    Log.d("checkData2", snapshot.getValue().toString());
                    Log.d("checkData3", category.getCatalog_name() + " - " + category.getCatalogs_des());
                }


//                Toast.makeText(MainActivity.this, "name " + category.getCatalog_name() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //Insert bill lên firebase
    public void CreateBill(DatabaseReference myRef) {
        String key = myRef.push().getKey();

        String formattedDate = df.format(calendar.getTime());

//        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" +calendar.get(Calendar.YEAR);
        String bill_number = calendar.get(Calendar.DAY_OF_MONTH) + "" + (calendar.get(Calendar.MONTH) + 1) + "" +calendar.get(Calendar.YEAR) + String.valueOf(++billNumberOfDay);
        String amount = textViewThanhTien.getText().toString();
        String discount = editTextPercent.getText().toString();
        String total = textViewTotal.getText().toString();
        Bill bill = new Bill(amount, bill_number, "Khoa", formattedDate, discount, textViewStaff.getText().toString(), "1", textViewNumberTable.getText().toString(), total);

        myRef.child(key).setValue(bill);

        Map<String, Object> myMap = new HashMap<String, Object>();
        for (int i=0; i<arrayListBill.size(); i++) {
            Product_Bill product_bill = arrayListBill.get(i);

            Bill_Detail bill_detail = new Bill_Detail(product_bill.getProduct_price(), String.valueOf(product_bill.getSize()));

            myMap.put(product_bill.getProduct_name(), bill_detail);
        }

        myRef.child(key).child("bill_detail").setValue(myMap);
    }

    //Xác nhận tạo bill
    public void ConfirmCreateBill() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirm_createbill);
        dialog.setCanceledOnTouchOutside(false);

        final TextView textview_ThanhTien = dialog.findViewById(R.id.textViewThanhTien);
        final EditText editext_TienKhachDua = dialog.findViewById(R.id.editTextTienKhachDua);
        final TextView textview_TienThua = dialog.findViewById(R.id.textViewTienThua);
        Button buttonXacNhan = dialog.findViewById(R.id.buttonXacNhanDialog);
        Button buttonHuy = dialog.findViewById(R.id.buttonHuyDialog);

        textview_ThanhTien.setText(textViewThanhTien.getText().toString());

        editext_TienKhachDua.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textview_TienThua.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editext_TienKhachDua.getText().toString().equals("")) {
                    editext_TienKhachDua.setText("0");
                }
                int TKD = Integer.parseInt(editext_TienKhachDua.getText().toString());
                int TT = Integer.parseInt(textview_ThanhTien.getText().toString());
                if (TKD > TT) {
                    textview_TienThua.setText(String.valueOf(TKD - TT));
                }
            }
        });

        editext_TienKhachDua.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    if (editext_TienKhachDua.getText().toString().equals("")) {
                        editext_TienKhachDua.setText("0");
                    }
                    int TKD = Integer.parseInt(editext_TienKhachDua.getText().toString());
                    int TT = Integer.parseInt(textview_ThanhTien.getText().toString());
                    if (TKD < TT) {
                        Toast.makeText(MainActivity.this, "Tiền khách đưa phải lớn hơn tổng tiền!", Toast.LENGTH_SHORT).show();
                    } else {
                        textview_TienThua.setText(String.valueOf(TKD - TT));

                        notiBadge.setNumber(++count_notification);
                        CreateBill(myRefBill);
                        dialog.dismiss();

                        arrayListBill.clear();
                        menu_adapter_update_bill.notifyDataSetChanged();
                        textViewNumberTable.setText("Chưa chọn bàn");
                        textViewTotal.setText("0");
                        textViewThanhTien.setText("0");
                    }


                    return true;
                }
                return false;
            }
        });

        buttonXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notiBadge.setNumber(++count_notification);
                CreateBill(myRefBill);
                dialog.dismiss();

                arrayListBill.clear();
                menu_adapter_update_bill.notifyDataSetChanged();
                textViewNumberTable.setText("Chưa chọn bàn");
                textViewTotal.setText("0");
                textViewThanhTien.setText("0");
            }
        });

        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
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

    public void showPopupMenu(View v) {
        final PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
        popupMenu.inflate(R.menu.popup_menu_list_bill);
        popupMenu.show();
        final ArrayList<String> arrayListKey = new ArrayList<>();
        myRefBill.orderByKey().limitToLast(5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())  {
                    arrayListKey.add(snapshot.getKey());
                    String bill_number = snapshot.child("bill_number").getValue().toString();
                    String[] time = snapshot.child("date_create").getValue().toString().split(" ");
                    popupMenu.getMenu().add(0, arrayListKey.size(), 0, "HĐ: " + bill_number + " - " + time[1]);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 1:
                        getItemPopupMenu(arrayListKey.get(0));
                        return true;
                    case 2:
                        getItemPopupMenu(arrayListKey.get(1));
                        return true;
                    case 3:
                        getItemPopupMenu(arrayListKey.get(2));
                        return true;
                    case 4:
                        getItemPopupMenu(arrayListKey.get(3));
                        return true;
                    case 5:
                        getItemPopupMenu(arrayListKey.get(4));
                        return true;
                }
                return false;
            }
        });
    }

    public void getItemPopupMenu(final String key) {
        buttonMainPrint.setText("Cập nhật");
        createBill.setText("Thoát");

        myRefBill.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txt_get_time.setText(dataSnapshot.child("date_create").getValue().toString());
                textViewNumberTable.setText(dataSnapshot.child("table").getValue().toString());
                textViewTotal.setText(dataSnapshot.child("total").getValue().toString());
                editTextPercent.setText(dataSnapshot.child("discount").getValue().toString());
                textViewThanhTien.setText(dataSnapshot.child("amount").getValue().toString());

                readBillDetail(key);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        createBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBill.setText("Lưu");
                buttonMainPrint.setText("In");
                txt_get_time.setText(df.format(calendar.getTime()));
                textViewNumberTable.setText("Chưa chọn bàn");
                textViewTotal.setText("0");
                editTextPercent.setText("0");
                textViewThanhTien.setText("0");
                arrayListBill.clear();
                menu_adapter_update_bill.notifyDataSetChanged();
            }
        });
    }

    public void readBillDetail(String key) {
        myRefBill.child(key).child("bill_detail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.getKey();
                    String price = snapshot.child("product_price").getValue().toString();
                    String size = snapshot.child("product_quantity").getValue().toString();
                    arrayListBill.add(new Product_Bill(name, "", "", price, Integer.parseInt(size)));
                }
                menu_adapter_update_bill = new Adapter_Product_Bill(arrayListBill, getApplicationContext(), textViewTotal);
                recyclerView_bill.setAdapter(menu_adapter_update_bill);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
