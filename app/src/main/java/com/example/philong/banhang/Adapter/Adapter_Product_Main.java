package com.example.philong.banhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philong.banhang.Activity.MainActivity;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Product_Bill;
import com.example.philong.banhang.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Adapter_Product_Main extends RecyclerView.Adapter<Adapter_Product_Main.ViewHolder>{
    ArrayList<Product> ProductArrayList;
    ArrayList<Product> ProductBillArrayList=new ArrayList<>();
    Context context;
    int id;
    String name;
    int price;

    public Adapter_Product_Main(ArrayList<Product> productArrayList, Context context) {
        ProductArrayList = productArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_view_row_main_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(ProductArrayList.get(position).getName());
        holder.txtPrice.setText(String.valueOf(ProductArrayList.get(position).getPrice()));

        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = ProductArrayList.get(position).getId();
                name=ProductArrayList.get(position).getName();
                price=ProductArrayList.get(position).getPrice();

                Product product=ProductArrayList.get(position);
//                Toast.makeText(context,name+","+price , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("intent_tenmon");
                intent.putExtra("id", id); //bug
                intent.putExtra("name", name);
                intent.putExtra("price", price);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.text_view_item_name_product);
            txtPrice=itemView.findViewById(R.id.text_view_item_price_product);
        }

    }


}
