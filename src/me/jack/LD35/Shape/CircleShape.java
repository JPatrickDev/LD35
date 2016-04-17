package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityProjectile;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.FireballProjectile;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Projectile.Projectile;

import java.util.ArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class CircleShape extends Shape{

    public CircleShape() {
        super(12, 15,50,128,"Moves faster than\nthe default shape.\nIts weapon\nsets enemies on fire\nas does its radius\nattack.");
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
    }

    @Override
    public void onSwitch(Level level) {

    }

    @Override
    public void dealAOEDamage(ArrayList<Entity> hit,Level level) {
        for(Entity e : hit){
            if(e instanceof EntityRobot){
                ((EntityRobot)e).onFire = true;
            }
        }
    }
}
