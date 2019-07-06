package com.peryite.DemonHerding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.peryite.DemonHerding.EnityServices.CreatureServices;
import com.peryite.DemonHerding.Entity.Creature;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable daemonAnimationDrawable;
    private CreatureServices creatureServices;
    private Creature pitLord;
    private Creature demon;
    private Creature sacrifice;

   /* TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView text_Daemon;
    TextView text_Needs;
    TextView text_Spare;*/

    private EditText et_PitAmount;
    private EditText et_HPAmount;
    private EditText et_Amount;
    private TextView tv_DaemonAmount;
    private TextView tv_NeedsAmount;
    private TextView tv_SpareAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        hideActionBar();
        initView();
        pitLord = new Creature();
        demon = new Creature();
        sacrifice = new Creature();
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
        ImageView imgv_Daemon = findViewById(R.id.imgv_Daemon);
        imgv_Daemon.setBackgroundResource(R.drawable.demanimation);

        daemonAnimationDrawable = (AnimationDrawable) imgv_Daemon.getBackground();
        daemonAnimationDrawable.start();

        et_PitAmount = findViewById(R.id.et_PitAmount);
        et_PitAmount.setTypeface(liberationSerif_Typeface);
        et_PitAmount.setTextColor(getResources().getColor(R.color.colorAccent));
        et_HPAmount = findViewById(R.id.et_HPAmount);
        et_HPAmount.setTypeface(liberationSerif_Typeface);
        et_Amount = findViewById(R.id.et_Amount);
        et_Amount.setTypeface(liberationSerif_Typeface);

        tv_DaemonAmount = findViewById(R.id.tv_DaemonAmount);
        tv_DaemonAmount.setTypeface(liberationSerif_Typeface);
        tv_NeedsAmount = findViewById(R.id.tv_NeedsAmount);
        tv_NeedsAmount.setTypeface(liberationSerif_Typeface);
        tv_SpareAmount = findViewById(R.id.tv_SpareAmount);
        tv_SpareAmount.setTypeface(liberationSerif_Typeface);

        Button compute = findViewById(R.id.btn_Compute);
        Button clear = findViewById(R.id.btn_Clear);
        compute.setOnClickListener(click -> computeAmount());
        clear.setOnClickListener(click -> clearFields());

    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void computeAmount() {
//        int pit = 0;
//        int hp = 0;
//        int amount = 0;
//        int daemon = 0;
        int needs = 0;
        int spare = 0;
        int xPit;
        int hpXamount;
        // Check empty fields
       if(!checkFields()){
           return;
       }

        pitLord.setAmount(Integer.parseInt(et_PitAmount.getText().toString()));
        sacrifice.setHealth(Integer.parseInt(et_HPAmount.getText().toString()));
        sacrifice.setAmount(Integer.parseInt(et_Amount.getText().toString()));
        demon.setAmount(creatureServices.calcDemonAmount(pitLord, sacrifice));
        tv_DaemonAmount.setText(String.valueOf(demon.getAmount()));
        spare = creatureServices.calcSpareAmount(sacrifice, demon);
        tv_SpareAmount.setText(String.valueOf(spare));
        needs = creatureServices.calcNeedAmount(sacrifice.getAmount(), spare);
        tv_NeedsAmount.setText(String.valueOf(needs));
        //Insert EditText into variables
//        pitLord.setAmount(Integer.parseInt(et_PitAmount.getText().toString()));
//        sacrifice.setHealth(Integer.parseInt(et_HPAmount.getText().toString()));
//        sacrifice.setAmount(Integer.parseInt(et_Amount.getText().toString()));
//        xPit = 50 * pit;
//        hpXamount = hp * amount;
//        if (hpXamount > xPit) {
//            daemon = xPit / 35;
//            et_PitAmount.setTextColor(getResources().getColor(R.color.coloryellow));
//        } else if (hpXamount <= xPit)
//            daemon = hpXamount / 35;
//        spare = (hpXamount - (daemon * 35)) / hp;
//        needs = amount - spare;
//        String Daemon = Integer.toString(daemon);
//        tv_DaemonAmount.setText(Daemon);
//        String Spare = Integer.toString(spare);
//        tv_SpareAmount.setText(Spare);
//        String Needs = Integer.toString(needs);
//        tv_NeedsAmount.setText(Needs);
    }

    public void clearFields() {
        et_PitAmount.setTextColor(getResources().getColor(R.color.colorAccent));
        et_HPAmount.setText("");
        et_Amount.setText("");
        tv_DaemonAmount.setText("");
        tv_NeedsAmount.setText("");
        tv_SpareAmount.setText("");
    }


    @SuppressLint("ShowToast")
    private boolean checkFields(){
        if(TextUtils.isEmpty(et_PitAmount.getText().toString())){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.pit_lord_empty), Toast.LENGTH_SHORT);
            return false;
        }
        if(TextUtils.isEmpty(et_HPAmount.getText().toString())){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.HoS_empty), Toast.LENGTH_SHORT);
            return false;
        }
        if(TextUtils.isEmpty(et_Amount.getText().toString())){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Amount_empty), Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }
}
