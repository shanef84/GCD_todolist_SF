package com.example.forbess.gcd_todolist_sf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private fields of the class
    private TextView tv_name;
    private ListView lv_mainlist;
    private EditText et_new_strings;
    private boolean checkbox;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter caa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar will confirm items added to list or error
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pull the list view & edit text from XML
        tv_name = (TextView) findViewById(R.id.tv_name);
        lv_mainlist = (ListView) findViewById(R.id.lv_mainlist);
        et_new_strings = (EditText) findViewById(R.id.et_new_strings);
        //generate an array list
        al_items = new ArrayList<CustomItem>();
        //create array adapter for al_items & set it on the listview
        caa = new CustomArrayAdapter(this, al_items);
        lv_mainlist.setAdapter(caa);

        // add in a listener for the edit text to create new items in our list view
        et_new_strings.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionid, KeyEvent event) {
                // if the user is done entering in a new string then add it to
                // the array list. this then notifies the adapter that the data has
                // changed and that the list view needs to be updated
                if (actionid == EditorInfo.IME_ACTION_DONE) {
                    al_items.add(new CustomItem(et_new_strings.getText().toString(), false));
                    caa.notifyDataSetChanged();
                    Snackbar.make(view, "New todo Item added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    et_new_strings.setText("");
                    return true;
                }
                // if we get to this point then the event has not been handled thus
                // return false
                Snackbar.make(view, "Error", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return false;
            }
        });

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al_items.add(new CustomItem(et_new_strings.getText().toString(), false));
                caa.notifyDataSetChanged();
                Snackbar.make(view, "New todo Item added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                et_new_strings.setText("");
            }
        });

        // add in a listener that listens for long clicks on our list items
        lv_mainlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            // overridden method that we must implement to get access to long clicks
            public boolean onItemLongClick(AdapterView<?> adapterview, View view, int pos, long id) {
                // update the display with what we have just long clicked
                al_items.remove(pos);
                caa.notifyDataSetChanged();
                Snackbar.make(view, "Item Deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                // as we are going to change the textview anyway we can automatically
                // return true;
                return true;
            }
        });
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
