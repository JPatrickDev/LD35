package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Level.Level;

import java.util.ArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Shape {
    private int moveSpeed;
    private float health;

    private int aoeRadius;
    private float chargeNeeded;

    private String description;
    public Shape(int moveSpeed, float health, float chargeNeeded, int aoeRadius,String description) {
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.chargeNeeded = chargeNeeded;
        this.aoeRadius = aoeRadius;
        this.description = description;
    }

    public abstract void attack(int x, int y, Level level);

    public abstract void onSwitch(Level level);

    public abstract void dealAOEDamage(ArrayList<Entity> hit,Level level);

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getHealth() {
        return health;
    }

    public float getChargeNeeded() {
        return chargeNeeded;
    }

    public int getAoeRadius() {
        return aoeRadius;
    }

    public String getDescription() {
        return description;
    }
}
