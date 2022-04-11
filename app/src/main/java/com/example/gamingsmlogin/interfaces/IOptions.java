package com.example.gamingsmlogin.interfaces;

import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public interface IOptions {

     void getOptions(String method, AutoCompleteTextView textBox1, AutoCompleteTextView textBox2, TextInputLayout til1, TextInputLayout til2);

     void getStats(String method);

}
