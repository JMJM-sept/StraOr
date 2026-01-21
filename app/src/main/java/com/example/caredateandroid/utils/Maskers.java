package com.example.caredateandroid.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Maskers {

    public static TextWatcher dateMask(final EditText editText) {
        return new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) return;
                String str = s.toString();
                if (str.equals(current)) return;
                String clean = str.replaceAll("[^\\d]", "");
                StringBuilder sb = new StringBuilder();
                int len = clean.length();
                if (len > 2) sb.append(clean.substring(0,2)).append('/');
                else if (len > 0) sb.append(clean);
                if (len > 4) sb.append(clean.substring(2,4)).append('/');
                else if (len > 2) sb.append(clean.substring(2));
                if (len > 4) sb.append(clean.substring(4));
                current = sb.toString();
                editText.removeTextChangedListener(this);
                editText.setText(current);
                editText.setSelection(current.length());
                editText.addTextChangedListener(this);
            }
        };
    }


    public static TextWatcher phoneOnly(final EditText editText, final int maxDigits) {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                if (s == null) return;
                String clean = s.toString().replaceAll("[^\\d]", "");
                if (!clean.equals(s.toString())) {
                    editText.removeTextChangedListener(this);
                    if (clean.length() > maxDigits) clean = clean.substring(0, maxDigits);
                    editText.setText(clean);
                    editText.setSelection(clean.length());
                    editText.addTextChangedListener(this);
                }
            }
        };
    }
}
