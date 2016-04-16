package me.jack.LD35.Shape;

import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Shape{
    private int moveSpeed;
    private float health;

    public Shape(int moveSpeed, float health) {
        this.moveSpeed = moveSpeed;
        this.health = health;
    }

    public abstract void attack(int x, int y, Level level);
    public abstract void onSwitch(Level level);

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getHealth() {
        return health;
    }
}
