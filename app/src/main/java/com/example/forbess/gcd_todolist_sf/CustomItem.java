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
    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
    // private fields of the class
    private String name;
    private boolean checkbox;
}
