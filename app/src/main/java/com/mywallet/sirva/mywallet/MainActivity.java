package com.mywallet.sirva.mywallet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Context context;

    private String m_DialogText = "";
    private int m_DialogAmount = 0;
    private WalletItem m_DialogResult = null;

    public static int[] prgmImages = {R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred, R.drawable.testiconred};
    public static String[] prgmNameList = {"Canned Beans", "Dog Toilette", "JAVA Course", "Magic booster pack", "Microsoft .Net", "Android", "PHP", "Jquery", "JavaScript"};
    public static int[] amountDummy = {1, 2, -3, 4, 5, 6, 7, 8, 9};
    public static Date[] dates = {new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(),};

    private boolean listViewDrawn = false;

    public static ArrayList<WalletItem> wlltList = new ArrayList<WalletItem>() ;// WalletItem.listBuilder(prgmImages, amountDummy, prgmNameList, dates);

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        lv = (ListView) findViewById(R.id.listView);
        if(wlltList != null) {
            lv.setAdapter(new CustomAdapter(this, wlltList));
            listViewDrawn = true;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickAddButton(view);
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onClickAddButton(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Expense");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Gain", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_DialogText = input.getText().toString();

                onClickAmountInsert(view,1,m_DialogText);
            }
        });
        builder.setNeutralButton("Expense", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_DialogText = input.getText().toString();

                onClickAmountInsert(view,-1,m_DialogText);
            }
        });
        builder.setIcon(R.drawable.testiconred);

        builder.show();
    }

    private void onClickAmountInsert(final View view, final int selector, final String description) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Expense");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        final int icon;

        // Set up the buttons
        if(selector < 0)
        {
            icon = R.drawable.testiconred;
        }else
        {
            icon = R.drawable.testicongreen;
        }

        builder.setIcon(icon);

        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_DialogAmount = selector*Integer.parseInt(input.getText().toString());

                wlltList.add(new WalletItem(icon,m_DialogAmount,description,new Date()));
            }
        });



        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mywallet.sirva.mywallet/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.mywallet.sirva.mywallet/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
