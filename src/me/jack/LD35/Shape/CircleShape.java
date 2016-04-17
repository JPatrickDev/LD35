package me.jack.LD35.Shape;

import me.jack.LD35.Entity.EntityProjectile;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.FireballProjectile;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Projectile.Projectile;

/**
 * Created by Jack on 16/04/2016.
 */
public class CircleShape extends Shape{

    public CircleShape() {
        super(12, 15,50);
    }

    long lastAttack = 0;
    @Override
    public void attack(int x, int y, Level level) {
        Projectile t = new FireballProjectile();
        if (lastAttack == 0) {
            lastAttack = System.currentTimeMillis();
            level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x,y,t));
        }
        else{
            if(System.currentTimeMillis() - lastAttack >= t.getFireRate()){
                lastAttack = System.currentTimeMillis();
                level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x,y,t));
            }
        }
        System.out.println(t.getDamage());
    }

    @Override
    public void onSwitch(Level level) {

    }
}
