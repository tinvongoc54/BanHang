package com.example.philong.banhang.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philong.banhang.Activity.Update_All_Table;
import com.example.philong.banhang.Activity.Update_All_Table_Update;
import com.example.philong.banhang.Activity.Update_All;
import com.example.philong.banhang.Objects.Product;
import com.example.philong.banhang.Objects.Table;
import com.example.philong.banhang.R;

import java.util.ArrayList;

public class Adapter_Update_Table extends RecyclerView.Adapter<Adapter_Update_Table.ViewHolder>{
    ArrayList<Table> UpdatesTableArrayList;
    Context context;
    Update_All_Table updateTableClass;

    public Adapter_Update_Table(ArrayList<Table> updatesTableArrayList, Context context, Update_All_Table updateTableClass) {
        UpdatesTableArrayList = updatesTableArrayList;
        this.context = context;
        this.updateTableClass = updateTableClass;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_view_row_update_table, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtNameTable.setText(UpdatesTableArrayList.get(position).getName());
        holder.imageViewEditUpdateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table table=UpdatesTableArrayList.get(position);
                Intent intent=new Intent(updateTableClass,Update_All_Table_Update.class);
                intent.putExtra("soban",table);
                updateTableClass.startActivity(intent);
            }
        });
        holder.imageViewEditDeleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(UpdatesTableArrayList.get(position).getName(),UpdatesTableArrayList.get(position).getId());
            }
        });
    }

    private void XacNhanXoa(String soBan, final int id){

        AlertDialog.Builder dialogxoa=new AlertDialog.Builder(updateTableClass);
        dialogxoa.setMessage("Ban co muon xoa ban : "+soBan+" khong");
        dialogxoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateTableClass.DeleteTable(id);
            }
        });
        dialogxoa.setNegativeButton("khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogxoa.show();
    }

    @Override
    public int getItemCount() {
        return UpdatesTableArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameTable;
        ImageView imageViewEditUpdateTable,imageViewEditDeleteTable;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNameTable=itemView.findViewById(R.id.text_view_item_name_table_update);
            imageViewEditUpdateTable=itemView.findViewById(R.id.imageview_update_edit);
            imageViewEditDeleteTable=itemView.findViewById(R.id.imageview_update_delete);
        }

    }


}
