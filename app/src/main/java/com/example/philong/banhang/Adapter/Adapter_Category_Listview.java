package com.example.philong.banhang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.philong.banhang.Objects.Category;
import com.example.philong.banhang.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter_Category_Listview extends BaseAdapter {

    Context context;
    List<Category> categoryList;
    int layout;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("product");

    public Adapter_Category_Listview(Context context, List<Category> categoryList, int layout) {
        this.context = context;
        this.categoryList = categoryList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        Button btnCategory = view.findViewById(R.id.btn_category);

        btnCategory.setText(categoryList.get(i).getCatalog_name());

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = categoryList.get(i);
                String category_name = category.getCatalog_name();

                Intent intent = new Intent("intent_category");
                intent.putExtra("category_name", category_name);

                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
        return view;
    }
}
