package com.peryite.DemonHerding;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peryite.DemonHerding.EnityServices.CreatureServices;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable daemonAnimationDrawable;
    private CreatureServices creatureServices;

   /* TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView text_Daemon;
    TextView text_Needs;
    TextView text_Spare;*/

   private EditText PitAmount;
   private EditText HPAmount;
   private EditText Amount;
   private TextView DaemonAmount;
   private TextView NeedsAmount;
   private TextView SpareAmount;
   private Button compute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        hideActionBar();
        initView();

        creatureServices = new CreatureServices();
    }

    private void initView() {
        final Typeface liberationSerif_Typeface = Typeface.createFromAsset(getAssets(), "LiberationSerif-Regular.ttf");
        TextView tv_title = findViewById(R.id.tv_Title);
        tv_title.setTypeface(liberationSerif_Typeface);
        TextView tv_PitLord = findViewById(R.id.tv_PitLord);
        tv_PitLord.setTypeface(liberationSerif_Typeface);
        TextView tv_HoS = findViewById(R.id.tv_HoS);
        tv_HoS.setTypeface(liberationSerif_Typeface);
        TextView tv_Amount = findViewById(R.id.tv_Amount);
        tv_Amount.setTypeface(liberationSerif_Typeface);
        TextView tv_Daemon = findViewById(R.id.tv_Daemon);
        tv_Daemon.setTypeface(liberationSerif_Typeface);
        TextView tv_Needs = findViewById(R.id.tv_Needs);
        tv_Needs.setTypeface(liberationSerif_Typeface);
        TextView tv_Spare = findViewById(R.id.tv_Spare);
        tv_Spare.setTypeface(liberationSerif_Typeface);

        ImageView imgv_Daemon =  findViewById(R.id.imgv_Daemon);
        imgv_Daemon.setBackgroundResource(R.drawable.demanimation);

        daemonAnimationDrawable = (AnimationDrawable) imgv_Daemon.getBackground();
        daemonAnimationDrawable.start();

        PitAmount = findViewById(R.id.et_PitAmount);
        PitAmount.setTypeface(liberationSerif_Typeface);
        HPAmount = findViewById(R.id.et_HPAmount);
        HPAmount.setTypeface(liberationSerif_Typeface);
        Amount =  findViewById(R.id.et_Amount);
        Amount.setTypeface(liberationSerif_Typeface);

        DaemonAmount = findViewById(R.id.tv_DaemonAmount);
        DaemonAmount.setTypeface(liberationSerif_Typeface);
        NeedsAmount = findViewById(R.id.tv_NeedsAmount);
        NeedsAmount.setTypeface(liberationSerif_Typeface);
        SpareAmount = findViewById(R.id.tv_SpareAmount);
        SpareAmount.setTypeface(liberationSerif_Typeface);

        compute = findViewById(R.id.btn_Compute);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
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
