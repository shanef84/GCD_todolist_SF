package com.example.forbess.gcd_todolist_sf;

import android.widget.CheckBox;

/**
 * Created by forbess on 05/03/2016.
 */
public class CustomItem {

    CustomItem(String name, boolean status){
        this.name = name;
        this.checkbox = status;

    }

    // getter methods for both fields
    public String getName() { return name; }
    public boolean isCheckbox() { return checkbox; }
    // private fields of the class
    private String name;
    private boolean checkbox;
}
