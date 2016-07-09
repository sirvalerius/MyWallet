package com.mywallet.sirva.mywallet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mywallet.sirva.mywallet.Activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WalletListRecyclerViewAdapter extends RecyclerView.Adapter<WalletListRecyclerViewAdapter.WalletItemHolder> {

    static ArrayList<WalletItem> walletItemList;
    static MainActivity context;
    private static LayoutInflater inflater=null;

    public WalletListRecyclerViewAdapter(MainActivity mainActivity, ArrayList<WalletItem> walletItemList) {
        // TODO Auto-generated constructor stub

        context = mainActivity;
        this.walletItemList = walletItemList;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public WalletItemHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {

        View rowView;

        rowView = inflater.inflate(R.layout.wallet_item, null);

        return new WalletItemHolder(rowView);
    }


    @Override
    public void onBindViewHolder(WalletItemHolder contactViewHolder, int i) {
        WalletItem wi = walletItemList.get(i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy hh:mm");
        contactViewHolder.tvDescription.setText(wi.getDescription());
        contactViewHolder.tvAmount.setText(""+walletItemList.get(i).getAmount());
        contactViewHolder.tvDate.setText(dateFormat.format(walletItemList.get(i).getDate()));
        contactViewHolder.ivIcon.setImageResource(walletItemList.get(i).getImageResource());
    }

    public static class WalletItemHolder extends RecyclerView.ViewHolder {
        public TextView tvDescription;
        public TextView tvAmount;
        public TextView tvDate;
        public ImageView ivIcon;
        final View v;

        /**
        *Constructor
         **/
        public WalletItemHolder(final View v) {
            super(v);
            this.v = v;
            tvDescription =  (TextView) v.findViewById(R.id.textDescription);
            tvAmount = (TextView)  v.findViewById(R.id.textAmount);
            tvDate = (TextView)  v.findViewById(R.id.textDate);
            ivIcon = (ImageView) v.findViewById(R.id.imageView1);

            final DialogInterface.OnClickListener walletItemOnEditClickHandler = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    WalletItem.onClickEditButton(v,context,walletItemList,getAdapterPosition());
                    v.invalidate();
                }

            };

            final DialogInterface.OnClickListener walletItemOnDeleteClickHandler = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(v.getContext().getApplicationContext(),""+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    walletItemList.remove(getAdapterPosition());
                    v.invalidate();
                }

            };

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setNeutralButton("Edit",walletItemOnEditClickHandler);
                    builder.setPositiveButton("Delete",walletItemOnDeleteClickHandler);
                    builder.show();
                }});

        }


    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return walletItemList.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}