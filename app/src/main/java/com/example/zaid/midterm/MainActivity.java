package com.example.zaid.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Screen screen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ButtonHandler bh = new ButtonHandler();
        screen = new Screen(this, bh);
        setContentView(screen);
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            if(screen.isButton((Button)v))
            {

            }
        }
    }
}
