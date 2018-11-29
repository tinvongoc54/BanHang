package com.example.philong.banhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philong.banhang.Activity.MainActivity;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Product_Bill;
import com.example.philong.banhang.R;

import java.util.ArrayList;

public class Adapter_Product_Bill extends RecyclerView.Adapter<Adapter_Product_Bill.ViewHolder>{
    ArrayList<Product_Bill> menuUpdatesBillArrayList;
    Context context;
    TextView total;

    public Adapter_Product_Bill(ArrayList<Product_Bill> menuUpdatesArrayList, Context context, TextView total) {
        this.menuUpdatesBillArrayList = menuUpdatesArrayList;
        this.context = context;
        this.total = total;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_view_row_main_bill, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtName.setText(menuUpdatesBillArrayList.get(position).getProduct_name());
        holder.txtPrice.setText(String.valueOf(menuUpdatesBillArrayList.get(position).getProduct_price()));
        holder.txtSoLuong.setText(String.valueOf(menuUpdatesBillArrayList.get(position).getSize()));



        holder.txtSoLuong.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    int soLuong = 1;
                    if (!holder.txtSoLuong.getText().toString().equals("")) {
                        soLuong = Integer.parseInt(holder.txtSoLuong.getText().toString().trim());
                        if (soLuong > 100) {
                            soLuong = 100;
                        } else if (soLuong < 1) {
                            soLuong = 1;
                        }
                    }
                    menuUpdatesBillArrayList.get(position).setSize(soLuong);
                    holder.txtSoLuong.setText(String.valueOf(soLuong));
                    total.setText(String.valueOf(TongTien()));
                    return true;
                }
                return false;
            }
        });

        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(holder.txtSoLuong.getText().toString().trim());
                soLuong++;
                menuUpdatesBillArrayList.get(position).setSize(soLuong);
                holder.txtSoLuong.setText(String.valueOf(menuUpdatesBillArrayList.get(position).getSize()));
                total.setText(String.valueOf(TongTien()));
            }
        });

        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(holder.txtSoLuong.getText().toString().trim());
                if (soLuong > 1) {
                    soLuong--;
                    menuUpdatesBillArrayList.get(position).setSize(soLuong);
                    holder.txtSoLuong.setText(String.valueOf(menuUpdatesBillArrayList.get(position).getSize()));
                    total.setText(String.valueOf(TongTien()));
                } else {
                    Toast.makeText(context, "Số lượng không thể nhỏ hơn hoặc bằng 0!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.imageButtonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("intent_vitrixoabill");
                intent.putExtra("position", position);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

//        MainActivity mainActivity = new MainActivity();
////        mainActivity.getResources();
//////        mainActivity.textViewTotal.setText(String.valueOf(mainActivity.totalMoney(menuUpdatesBillArrayList)));

    }

    public int TongTien() {
        int tong=0;
        for (int i=0; i<menuUpdatesBillArrayList.size(); i++) {
            Product_Bill product_bill = menuUpdatesBillArrayList.get(i);
            tong += Integer.parseInt(product_bill.getProduct_price())*product_bill.getSize();
        }
        return tong;
    }



    @Override
    public int getItemCount() {
        return menuUpdatesBillArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice, txtSoLuong;
        ImageButton btnCong, btnTru, imageButtonXoa;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.text_view_item_name_bill);
            txtPrice=itemView.findViewById(R.id.text_view_item_price_bill);
            txtSoLuong = itemView.findViewById(R.id.textview_soluong_order1);
            btnCong = itemView.findViewById(R.id.button_cong);
            btnTru = itemView.findViewById(R.id.button_tru);
            imageButtonXoa = itemView.findViewById(R.id.imageButtonDelete);
        }

    }


}
