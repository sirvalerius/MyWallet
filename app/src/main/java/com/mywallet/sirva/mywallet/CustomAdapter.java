package com.mywallet.sirva.mywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

    WalletItem [] list;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, WalletItem[] walletItemList) {
        // TODO Auto-generated constructor stub

        context=mainActivity;
        list = walletItemList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.length;
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
        TextView tvDescription;
        TextView tvAmount;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.wallet_item, null);
        holder.tvDescription=(TextView) rowView.findViewById(R.id.textDescription);
        holder.tvAmount=(TextView) rowView.findViewById(R.id.textAmount);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tvDescription.setText(list[position].getDescription());
        holder.tvAmount.setText(""+list[position].getAmount());
        holder.img.setImageResource(list[position].getImageResource());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+list[position].getDescription(), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}