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
import android.widget.LinearLayout;
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
    Context context;

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
        holder.txtName.setText(ProductArrayList.get(position).getProduct_name());
        holder.txtPrice.setText(String.valueOf(ProductArrayList.get(position).getProduct_price()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product=ProductArrayList.get(position);

                String product_name = product.getProduct_name();
                String product_description = product.getProduct_description();
                String category_name = product.getCategory_name();
                String product_price = product.getProduct_price();

                Intent intent = new Intent("intent_tenmon");
                intent.putExtra("category", category_name);
                intent.putExtra("name", product_name);
                intent.putExtra("description", product_description );
                intent.putExtra("price", product_price);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

//        holder.txtName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Product product=ProductArrayList.get(position);
//
//                String product_name = product.getProduct_name();
//                String product_description = product.getProduct_description();
//                String category_name = product.getCategory_name();
//                String product_price = product.getProduct_price();
//
//                Intent intent = new Intent("intent_tenmon");
//                intent.putExtra("category", category_name);
//                intent.putExtra("name", product_name);
//                intent.putExtra("description", product_description );
//                intent.putExtra("price", product_price);
//                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return ProductArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView txtName, txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearItem);
            txtName=itemView.findViewById(R.id.text_view_item_name_product);
            txtPrice=itemView.findViewById(R.id.text_view_item_price_product);
        }

    }


}
