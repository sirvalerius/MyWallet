package com.mywallet.sirva.mywallet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WalletListAdapter extends BaseAdapter {

    ArrayList<WalletItem> list;
    MainActivity context;
    private static LayoutInflater inflater=null;
    public WalletListAdapter(MainActivity mainActivity, ArrayList<WalletItem> walletItemList) {
        // TODO Auto-generated constructor stub

        context = mainActivity;
        list = walletItemList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tvDate;
        TextView tvDescription;
        TextView tvAmount;
        ImageView img;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();
        View rowView;

        final DialogInterface.OnClickListener walletItemOnEditClickHandler = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                WalletItem.onClickEditButton(convertView,context,list,position);
                            }

        };

        final DialogInterface.OnClickListener walletItemOnDeleteClickHandler = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                ListView lv = (ListView) context.findViewById(R.id.listView);
                context.recreate();
            }

        };

        View.OnClickListener walletItemOnClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setNeutralButton("Edit",walletItemOnEditClickHandler);
                builder.setPositiveButton("Delete",walletItemOnDeleteClickHandler);

                builder.show();
            }
        };

        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy hh:mm");

        rowView = inflater.inflate(R.layout.wallet_item, null);
        holder.tvDescription=(TextView) rowView.findViewById(R.id.textDescription);
        holder.tvAmount=(TextView) rowView.findViewById(R.id.textAmount);
        holder.tvDate=(TextView) rowView.findViewById(R.id.textDate);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tvDate.setText(dateFormat.format(list.get(position).getDate()));
        holder.tvDescription.setText(list.get(position).getDescription());
        holder.tvAmount.setText(""+list.get(position).getAmount());
        holder.img.setImageResource(list.get(position).getImageResource());
        rowView.setOnClickListener(walletItemOnClickHandler);
        return rowView;
    }
}