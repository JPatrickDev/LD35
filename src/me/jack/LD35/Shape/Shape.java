package me.jack.LD35.Shape;

import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Shape {
    private int moveSpeed;
    private float health;

    private float chargeNeeded;

    public Shape(int moveSpeed, float health, float chargeNeeded) {
        this.moveSpeed = moveSpeed;
        this.health = health;
        this.chargeNeeded = chargeNeeded;
    }

    public abstract void attack(int x, int y, Level level);

    public abstract void onSwitch(Level level);

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getHealth() {
        return health;
    }

    public float getChargeNeeded() {
        return chargeNeeded;
    }
}
