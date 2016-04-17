package me.jack.LD35.Shape;

import jdk.nashorn.internal.ir.PropertyKey;
import me.jack.LD35.Entity.EntityProjectile;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Projectile.Projectile;

/**
 * Created by Jack on 16/04/2016.
 */
public class SquareShape extends Shape {

    public SquareShape() {
        super(8, 10f, 1);
    }

    long lastAttack = 0;

    @Override
    public void attack(int x, int y, Level level) {
        Projectile t = new LaserProjectile((int) (100 - Math.floor(level.round/5)), (float) Math.floor(level.round/10));
        if (lastAttack == 0) {
            lastAttack = System.currentTimeMillis();
            level.entities.add(new EntityProjectile(level.getPlayer().getX(), level.getPlayer().getY(), x, y, t));
        }
        else{
            if(System.currentTimeMillis() - lastAttack >= t.getFireRate()){
                lastAttack = System.currentTimeMillis();
                level.entities.add(new EntityProjectile(level.getPlayer().getX(), level.getPlayer().getY(), x, y, t));
            }
        }
    System.out.println(t.getDamage());
    }

    @Override
    public void onSwitch(Level level) {

    }
}
