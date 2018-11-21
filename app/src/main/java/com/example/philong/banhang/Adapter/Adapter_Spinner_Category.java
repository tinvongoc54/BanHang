package com.example.philong.banhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.philong.banhang.Objects.Category;
import com.example.philong.banhang.Objects.Category;
import com.example.philong.banhang.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Spinner_Category extends BaseAdapter {
    TextView txtId,txtName;


    Context context;
    int myLayout;
    ArrayList<Category> arrayList;



    public Adapter_Spinner_Category(Context context, int myLayout, ArrayList<Category> arrayList) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(myLayout,null);

        txtId=convertView.findViewById(R.id.textview_item_spinner_id);
        txtName=convertView.findViewById(R.id.textview_item_spinner_ten);

        txtId.setText(String.valueOf(arrayList.get(position).id));
        txtName.setText(arrayList.get(position).ten);


        return convertView;
    }
}
