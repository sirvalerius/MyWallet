package com.mywallet.sirva.mywallet;

/**
 * Created by valerio on 07/07/2016.
 */
public class WalletItem {

    int imageId;
    int expenseAmount;
    String expenseDescription;

    public WalletItem(int imageId, int expenseAmount, String expenseDescription)
    {
        this.expenseAmount = expenseAmount;
        this.imageId = imageId;
        this.expenseDescription = expenseDescription;
    }

    public static WalletItem[] listBuilder(int[] imageList, int[]amountList, String[]descriptionList) /*throws InvalidAlgorithmParameterException */{

      /*  if((imageList.length != amountList.length)||(amountList.length != descriptionList.length)||(imageList.length != descriptionList.length)) {
            throw new InvalidAlgorithmParameterException();
        } */

        WalletItem[] list = new WalletItem[imageList.length];

        for(int i = 0; i < list.length; i++)
        {
            list[i] = new WalletItem(imageList[i], amountList[i], descriptionList[i]);
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
}
