package com.peryite.DemonHerding;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private AnimationDrawable daemonAnimationDrawable;

   /* TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView text_Daemon;
    TextView text_Needs;
    TextView text_Spare;*/

    EditText PitAmount;
    EditText HPAmount;
    EditText Amount;

    TextView DaemonAmount;
    TextView NeedsAmount;
    TextView SpareAmount;

    Button compute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final Typeface liberationSerif_Typeface = Typeface.createFromAsset(getAssets(), "LiberationSerif-Regular.ttf");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(liberationSerif_Typeface);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(liberationSerif_Typeface);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setTypeface(liberationSerif_Typeface);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setTypeface(liberationSerif_Typeface);
        TextView text_Daemon = (TextView) findViewById(R.id.text_Daemon);
        text_Daemon.setTypeface(liberationSerif_Typeface);
        TextView text_Needs = (TextView) findViewById(R.id. text_Needs);
        text_Needs.setTypeface(liberationSerif_Typeface);
        TextView text_Spare = (TextView) findViewById(R.id.text_Spare);
        text_Spare.setTypeface(liberationSerif_Typeface);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.demanimation);

        daemonAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        daemonAnimationDrawable.start();

        PitAmount = (EditText) findViewById(R.id.PitAmount);
        PitAmount.setTypeface(liberationSerif_Typeface);
        HPAmount = (EditText) findViewById(R.id.HPAmount);
        HPAmount.setTypeface(liberationSerif_Typeface);
        Amount = (EditText) findViewById(R.id.Amount);
        Amount.setTypeface(liberationSerif_Typeface);

        DaemonAmount = (TextView) findViewById(R.id.DaemonAmount);
        DaemonAmount.setTypeface(liberationSerif_Typeface);
        NeedsAmount = (TextView) findViewById(R.id.NeedsAmount);
        NeedsAmount.setTypeface(liberationSerif_Typeface);
        SpareAmount = (TextView) findViewById(R.id.SpareAmount);
        SpareAmount.setTypeface(liberationSerif_Typeface);

        compute = (Button) findViewById(R.id.compute);
    }

    public void clickCompute (View view){
        int pit = 0;
        int hp = 0;
        int amount = 0;
        int daemon = 0;
        int needs = 0;
        int spare = 0;
        int xPit;
        int hpXamount;
        // Check empty fields
        if (TextUtils.isEmpty(PitAmount.getText().toString())
                || TextUtils.isEmpty(HPAmount.getText().toString())
                || TextUtils.isEmpty(Amount.getText().toString())) {
            return;
        }
        PitAmount.setTextColor(getResources().getColor(R.color.colorAccent));

        //Insert EditText into variables
        pit = Integer.parseInt(PitAmount.getText().toString());
        hp = Integer.parseInt(HPAmount.getText().toString());
        amount = Integer.parseInt(Amount.getText().toString());
        xPit = 50 * pit;
        hpXamount = hp * amount;
        if (hpXamount > xPit) {
            daemon = xPit / 35;
            PitAmount.setTextColor(getResources().getColor(R.color.coloryellow));
        }
        else if (hpXamount <= xPit)
            daemon = hpXamount / 35;
        spare = (hpXamount-(daemon*35))/hp;
        needs = amount-spare;
        String Daemon = Integer.toString(daemon);
        DaemonAmount.setText(Daemon);
        String Spare = Integer.toString(spare);
        SpareAmount.setText(Spare);
        String Needs = Integer.toString(needs);
        NeedsAmount.setText(Needs);
    }

    public void clickClear (View view){
        PitAmount.setText("");
        PitAmount.setTextColor(getResources().getColor(R.color.colorAccent));
        HPAmount.setText("");
        Amount.setText("");
        DaemonAmount.setText("");
        NeedsAmount.setText("");
        SpareAmount.setText("");
    }


}
