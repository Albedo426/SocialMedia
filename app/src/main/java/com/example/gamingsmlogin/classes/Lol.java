package com.example.gamingsmlogin.classes;

import android.content.Context;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class Lol extends GameOptionController {

    private AutoCompleteTextView textBox1,textBox2;
    private TextInputLayout til1,til2;

    public Lol(String required){
        super(required);
    }

    public Lol(AutoCompleteTextView textBox1, AutoCompleteTextView textBox2, Context mContext, TextInputLayout til1,TextInputLayout til2) {
        super("Şampiyon","Rol",mContext);
        this.textBox1 = textBox1;
        this.textBox2 = textBox2;
        this.til1 = til1;
        this.til2 = til2;
    }

    public void getOptions(){
        super.getOptions("lolOptions",textBox1,textBox2,til1,til2);
    }

    public void getStats(){
        super.getStats("summonerByName");
    }

    
}
