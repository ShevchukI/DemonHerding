package com.peryite.DemonHerding.Entity;

import java.util.Objects;

public class Creature {
    private String name;
    private int health;

    public Creature() {
    }

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
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

}
