package com.peryite.DemonHerding.mvp.view;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.peryite.DemonHerding.R;
import com.peryite.DemonHerding.mvp.contract.MainContract;
import com.peryite.DemonHerding.mvp.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.et_pit_lord_amount)
    AppCompatEditText etPitLordAmount;
    @BindView(R.id.et_sacrifice)
    AppCompatEditText etSacrifice;
    @BindView(R.id.et_amount)
    AppCompatEditText etAmount;
    @BindView(R.id.tv_need_amount)
    AppCompatTextView tvNeedAmount;
    @BindView(R.id.tv_spare_amount)
    AppCompatTextView tvSpareAmount;
    @BindView(R.id.tv_demon_amount)
    AppCompatTextView tvDaemonAmount;
    @BindView(R.id.iv_demon)
    AppCompatImageView ivDemon;

    @OnClick(R.id.btn_compute)
    public void onClickCompute(View view) {
        if (checkFields()) {
            presenter.onClickCompute(Integer.parseInt(String.valueOf(etPitLordAmount.getText())),
                    Integer.parseInt(String.valueOf(etSacrifice.getText())),
                    Integer.parseInt(String.valueOf(etAmount.getText())));
        }
    }

    @OnClick(R.id.btn_clear)
    public void onClickClear(View view) {
        presenter.onClickClear();
    }

    @OnClick(R.id.linearLayout_pitLord)
    public void onClickPitLord(View view) {
        etPitLordAmount.requestFocus();
    }

    @OnClick(R.id.linearLayout_sacrifice)
    public void onClickSacrifice(View view) {
        etSacrifice.requestFocus();
    }

    @OnClick(R.id.linearLayout_amount)
    public void onClickAmount(View view) {
        etAmount.requestFocus();
    }

    private Unbinder unbinder;

    private MainContract.Presenter presenter;


    private AnimationDrawable daemonAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        presenter.start();

    }

    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


    public void clearFields() {
        etPitLordAmount.setText("");
        etPitLordAmount.setTextColor(getResources().getColor(R.color.colorAccent));
        etSacrifice.setText("");
        etAmount.setText("");
        tvDaemonAmount.setText("");
        tvNeedAmount.setText("");
        tvSpareAmount.setText("");
    }

    private boolean checkFields() {
        Toast toast;
        if (TextUtils.isEmpty(etPitLordAmount.getText().toString())) {
            toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.pit_lord_empty), Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (TextUtils.isEmpty(etSacrifice.getText().toString())) {
            toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.health_of_sacrifice_empty), Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (TextUtils.isEmpty(etAmount.getText().toString())) {
            toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.amount_empty), Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    @Override
    public void startAnimation() {
        daemonAnimationDrawable = (AnimationDrawable) ivDemon.getBackground();
        daemonAnimationDrawable.start();
    }

    @Override
    public void showDemon(int demonAmount) {
        tvDaemonAmount.setText(String.valueOf(demonAmount));
    }

    @Override
    public void showNeed(int needAmount) {
        tvNeedAmount.setText(String.valueOf(needAmount));
    }

    @Override
    public void showSpare(int spareAmount) {
        tvSpareAmount.setText(String.valueOf(spareAmount));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
