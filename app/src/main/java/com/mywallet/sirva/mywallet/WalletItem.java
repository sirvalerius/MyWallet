package com.mywallet.sirva.mywallet;

import java.util.Date;

/**
 * Created by valerio on 07/07/2016.
 */
public class WalletItem {

    int imageId;
    int expenseAmount;
    String expenseDescription;
    Date expenseDate;

    public WalletItem(int imageId, int expenseAmount, String expenseDescription, Date expenseDate)
    {
        this.expenseAmount = expenseAmount;
        this.imageId = imageId;
        this.expenseDescription = expenseDescription;
        this.expenseDate = expenseDate;
    }

    public static WalletItem[] listBuilder(int[] imageList, int[]amountList, String[]descriptionList, Date[]dateList) /*throws InvalidAlgorithmParameterException */{

      /*  if((imageList.length != amountList.length)||(amountList.length != descriptionList.length)||(imageList.length != descriptionList.length)) {
            throw new InvalidAlgorithmParameterException();
        } */

        WalletItem[] list = new WalletItem[imageList.length];

        for(int i = 0; i < list.length; i++)
        {
            list[i] = new WalletItem(imageList[i], amountList[i], descriptionList[i],dateList[i]);
        }

        return list;
    }

    public int getImageResource() {
        return imageId;
    }

    public String getDescription() {
        return expenseDescription;
    }

    public int getAmount() {
        return expenseAmount;
    }

    public Date getDate() {
        return expenseDate;
    }
}
