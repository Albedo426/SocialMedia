package com.example.gamingsmlogin.classes;

import android.content.Context;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class CsGO extends GameOptionController {

    private AutoCompleteTextView textBox1, textBox2;
    private TextInputLayout til1,til2;

    public CsGO(String required){
        super(required);
    }


    public CsGO(AutoCompleteTextView textBox1, AutoCompleteTextView textBox2,Context mContext, TextInputLayout til1,TextInputLayout til2) {
        super("Silahlar","Roller", mContext);
        this.textBox1 = textBox1;
        this.textBox2 = textBox2;
        this.til1 = til1;
        this.til2 = til2;
    }

    public void getOptions(){
        super.getOptions("csGOOptions",textBox1,textBox2,til1,til2);
    }

    public void getStats(){
        super.getStats("getCsGOPlayerStats");
    }

}
