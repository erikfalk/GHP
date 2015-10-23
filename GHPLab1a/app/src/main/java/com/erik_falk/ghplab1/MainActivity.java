/**************************************
 * Meine erste Android App
 * Autor: Erik Falk
 * für: GHP Labor
 * erstellt am: 07.04.2015
 *************************************/

package com.erik_falk.ghplab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //A4 Button über IDE eingefügt
        Button button = (Button) findViewById(R.id.mybutton);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Knopf gedrückt", Toast.LENGTH_SHORT).show();
                //A6 logcat
                Log.i(getResources().getString(R.string.app_name), "Knopf gedrückt");

            }
        });

        //A5 Button dynmaisch erzeugt
        Button button1 = new Button(this);
        button1.setText(R.string.button_dyn);

        LinearLayout ll = (LinearLayout)findViewById(R.id.mylayout);
        ll.addView(button1);

        //A5 Listener über anonyme Klasse
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "dyn. Knopf gedrückt", Toast.LENGTH_SHORT).show();
                //A 6 logcat
                Log.i(getResources().getString(R.string.app_name), "dyn. Knopf gedrückt");
            }
        });

        //A9 aus den Shared Preferences laden
        EditText textField = (EditText) findViewById(R.id.editText);
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        textField.setText(prefs.getString("1",""));

    }

    public static final String PREFS = "MyPrefs";
    public final static String EXTRA_MESSAGE = "com.erik_falk.ghplab1.MESSAGE";

    //A7 eigenes onStop
    @Override
    public void onStop() {
        super.onStop();
        Log.i(getResources().getString(R.string.app_name), "gestoppt");

        //A9 in die Shared Preferences schreiben
        EditText textField = (EditText) findViewById(R.id.editText);
        SharedPreferences prefs = getSharedPreferences(PREFS,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("1",textField.getText().toString() );
        editor.apply();

    }

    //A10 Intent
    public void createIntent(View view){
        Intent intent = new Intent(this, DisplayMessage.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
}
