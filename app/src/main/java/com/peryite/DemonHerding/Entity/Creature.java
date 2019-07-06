package com.peryite.DemonHerding.Entity;

import java.util.Objects;

public class Creature {
    private String name;
    private int health;
    private int amount;

    public Creature() {
    }

    public Creature(String name, int health, int amount) {
        this.name = name;
        this.health = health;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
