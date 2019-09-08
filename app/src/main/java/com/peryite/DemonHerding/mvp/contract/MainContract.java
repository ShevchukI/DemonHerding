package com.peryite.DemonHerding.mvp.contract;

import com.peryite.DemonHerding.mvp.BaseView;

public interface MainContract {
    interface Presenter{
        void start();
        void onClickCompute(int pitLordQuantity, int sacrificeQuantity, int amount);
        void onClickClear();
    }

    interface View extends BaseView<Presenter>{
        void hideActionBar();
        void startAnimation();
        void showDemon(int demonAmount);
        void showNeed(int needAmount);
        void showSpare(int spareAmount);
        void clearFields();
    }
}
