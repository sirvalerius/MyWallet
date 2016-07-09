package com.mywallet.sirva.mywallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mywallet.sirva.mywallet.Activity.MainActivity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by valerio on 07/07/2016.
 */
public class WalletItem {

    int imageId;
    double expenseAmount;
    String expenseDescription;
    Date expenseDate;

    public WalletItem(int imageId, double expenseAmount, String expenseDescription, Date expenseDate)
    {
        this.expenseAmount = expenseAmount;
        this.imageId = imageId;
        this.expenseDescription = expenseDescription;
        this.expenseDate = expenseDate;
    }

    public static ArrayList<WalletItem> listBuilder(int[] imageList, int[]amountList, String[]descriptionList, Date[]dateList) /*throws InvalidAlgorithmParameterException */{

      /*  if((imageList.length != amountList.length)||(amountList.length != descriptionList.length)||(imageList.length != descriptionList.length)) {
            throw new InvalidAlgorithmParameterException();
        } */

        ArrayList<WalletItem> list = new ArrayList<WalletItem>(imageList.length);

        for(int i = 0; i < imageList.length; i++)
        {
            list.add(new WalletItem(imageList[i], amountList[i], descriptionList[i],dateList[i]));
        }

        return list;
    }

    public int getImageResource() {
        return imageId;
    }

    public String getDescription() {
        return expenseDescription;
    }

    public double getAmount() {
        return expenseAmount;
    }

    public Date getDate() {
        return expenseDate;
    }

    public static void onClickAddButton(final View view, MainActivity context, final ArrayList<WalletItem> wlltList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View editAlertView = inflater.inflate(R.layout.edit_walletitem,null);
        builder.setView(editAlertView);
        builder.setTitle("New Expense");

        // Set up the input
        final EditText input = (EditText) editAlertView.findViewById(R.id.editDesctiprion);
        final EditText number = (EditText) editAlertView.findViewById(R.id.editAmount);


        // Set up the buttons
        builder.setPositiveButton("Gain", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((input.getText().toString().matches(""))||(number.getText().toString().matches(""))) {
                    input.setError("Please insert a description");
                    number.setError("Please insert an amount");
                }
                else {
                    wlltList.add(new WalletItem(
                            R.drawable.testicongreen,
                            Double.parseDouble(number.getText().toString()),
                            input.getText().toString(),
                            new Date()
                    ));
                }
            }
        });
        builder.setNeutralButton("Expense", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_DialogText = input.getText().toString();

                if((input.getText().toString().matches(""))||(number.getText().toString().matches(""))) {
                    input.setError("Please insert a description");
                    number.setError("Please insert an amount");
                }
                else {
                    wlltList.add(new WalletItem(
                            R.drawable.testiconred,
                            -1*Double.parseDouble(number.getText().toString()),
                            input.getText().toString(),
                            new Date()
                    ));
                }
            }
        });

        builder.show();
    }

    public static void onClickEditButton(final View view,final MainActivity context,final ArrayList<WalletItem> wlltList,final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View editAlertView = inflater.inflate(R.layout.edit_walletitem,null);
        builder.setView(editAlertView);
        builder.setTitle("Edit Expense");

        // Set up the input
        final EditText input = (EditText) editAlertView.findViewById(R.id.editDesctiprion);
        final EditText number = (EditText) editAlertView.findViewById(R.id.editAmount);
        input.setText(wlltList.get(position).getDescription(),null);
        number.setText(Double.toString(wlltList.get(position).getAmount()),null);

        // Set up the buttons
        builder.setPositiveButton("Gain", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if((input.getText().toString().matches(""))||(number.getText().toString().matches(""))) {
                    input.setError("Please insert a description");
                    number.setError("Please insert an amount");
                }
                else {
                    wlltList.add(position,new WalletItem(
                            R.drawable.testicongreen,
                            Double.parseDouble(number.getText().toString()),
                            input.getText().toString(),
                            new Date()
                    ));
                    wlltList.remove(position+1);
                    context.recreate();
                }
            }
        });
        builder.setNeutralButton("Expense", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_DialogText = input.getText().toString();

                if((input.getText().toString().matches(""))||(number.getText().toString().matches(""))) {
                    input.setError("Please insert a description");
                    number.setError("Please insert an amount");
                }
                else {
                    wlltList.add(position,new WalletItem(
                            R.drawable.testiconred,
                            -1*Double.parseDouble(number.getText().toString()),
                            input.getText().toString(),
                            new Date()
                    ));
                    wlltList.remove(position+1);
                    context.recreate();
                }
            }
        });

        builder.show();
    }
}
