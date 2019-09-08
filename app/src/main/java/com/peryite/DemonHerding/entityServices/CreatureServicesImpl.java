package com.peryite.DemonHerding.entityServices;

import com.peryite.DemonHerding.entity.Creature;

public class CreatureServicesImpl implements CreatureServices{

    private Creature pitLord;
    private Creature demon;
    private Creature sacrifice;

    public CreatureServicesImpl() {
    }

    public CreatureServicesImpl(Creature pitLord, Creature demon, Creature sacrifice) {
        this.pitLord = pitLord;
        this.demon = demon;
        this.sacrifice = sacrifice;
    }

    public int calcDemonAmount(Creature pitLord, Creature sacrifice) {
        return (int) Math.min(Math.floor(Math.min(sacrifice.getAmount() * sacrifice.getHealth(),
                pitLord.getAmount() * 50) / 35), sacrifice.getAmount());
    }

    public int calcSpareAmount(Creature sacrifice, Creature demon) {
        return (sacrifice.getHealth() * sacrifice.getAmount() - demon.getAmount() * 35) / sacrifice.getHealth();
    }

    public int calcNeedAmount(int amount, int spare) {
        return amount - spare;
    }

    public Creature getPitLord() {
        return pitLord;
    }

    public void setPitLord(Creature pitLord) {
        this.pitLord = pitLord;
    }

    public Creature getDemon() {
        return demon;
    }

    public void setDemon(Creature demon) {
        this.demon = demon;
    }

    public Creature getSacrifice() {
        return sacrifice;
    }

    public void setSacrifice(Creature sacrifice) {
        this.sacrifice = sacrifice;
    }
}
