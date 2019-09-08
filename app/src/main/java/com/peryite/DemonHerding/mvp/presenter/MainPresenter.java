package com.peryite.DemonHerding.mvp.presenter;

import com.peryite.DemonHerding.entity.Creature;
import com.peryite.DemonHerding.entityServices.CreatureServices;
import com.peryite.DemonHerding.entityServices.CreatureServicesImpl;
import com.peryite.DemonHerding.mvp.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private CreatureServices creatureServices;
    private Creature pitLord;
    private Creature demon;
    private Creature sacrifice;

    public MainPresenter(MainContract.View view) {
        this.view = view;

        creatureServices = new CreatureServicesImpl();
        pitLord = new Creature();
        demon = new Creature();
        sacrifice = new Creature();
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.hideActionBar();
        view.startAnimation();
    }

    @Override
    public void onClickCompute(int pitLordQuantity, int sacrificeQuantity, int amount) {
        pitLord.setAmount(pitLordQuantity);
        sacrifice.setHealth(sacrificeQuantity);
        sacrifice.setAmount(amount);
        computeAmount();
    }

    @Override
    public void onClickClear() {
        view.clearFields();
    }

    private void computeAmount() {
        int demonQuantity = creatureServices.calcDemonAmount(pitLord, sacrifice);
        demon.setAmount(demonQuantity);

        int spare = creatureServices.calcSpareAmount(sacrifice, demon);
        int needs = creatureServices.calcNeedAmount(sacrifice.getAmount(), spare);

        view.showDemon(demon.getAmount());
        view.showSpare(spare);
        view.showNeed(needs);
    }
}
