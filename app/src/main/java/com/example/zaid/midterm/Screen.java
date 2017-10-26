package com.example.zaid.midterm;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Zaid on 10/17/2017.
 */

public class Screen extends LinearLayout
{
    private EditText editText;
    private Button button;
    private TextView textView;

    public Screen(Context context, View.OnClickListener listener)
    {
        super(context);
        button = new Button (context);

        //Creating Layout
        LinearLayout linear = new LinearLayout(context);

        //Setting up the orientation
        linear.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        addView(linear, linearParams);

        //setting up edittext
        editText = new EditText(context);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        editTextParams.gravity = Gravity.CENTER_HORIZONTAL;
        editText.setHint("Enter zipcode");
        editText.setTextSize(30);
        linear.addView(editText, editTextParams);

        //setting up button
        button = new Button(context);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
        button.setText("Search");
        button.setTextSize(15);
        button.setOnClickListener(listener);
        buttonParams.setMargins(0,100,0,0);
        linear.addView(button, buttonParams);

        //setting up textView
        textView = new TextView(context);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textViewParams.gravity = Gravity.CENTER_HORIZONTAL;
        textView.setText("Result");
        textViewParams.setMargins(0,100,0,0);
        linear.addView(textView, textViewParams);
    }

    public boolean isButton(Button b)
    {
        return (b == button);
    }

    public String UserInput(String input)
    {
        input = editText.getText().toString();
        return input;
    }

    public void setTextView(String textview)
    {
        textView.setText(textview.toString());
    }
}
