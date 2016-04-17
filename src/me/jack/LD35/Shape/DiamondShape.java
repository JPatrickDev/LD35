package me.jack.LD35.Shape;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityProjectile;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;
import me.jack.LD35.Projectile.Projectile;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

import java.util.ArrayList;

/**
 * Created by Jack on 17/04/2016.
 */
public class DiamondShape extends Shape {

    public DiamondShape() {
        super(8, 10f, 200, 256, "An upgraded version\nof the default\nshape. Has much\nhigher fire rate.\nThe radius attack\ncauses massive\ndamage");
    }

    long lastAttack = 0;

    @Override
    public void attack(int x, int y, Level level) {
        Projectile t = new LaserProjectile(60,10);
        if (lastAttack == 0) {
            lastAttack = System.currentTimeMillis();
            level.entities.add(new EntityProjectile(level.getPlayer().getX(), level.getPlayer().getY(), x, y, t));
            SoundEngine.getInstance().play("laser");
        } else {
            if (System.currentTimeMillis() - lastAttack >= t.getFireRate()) {
                lastAttack = System.currentTimeMillis();
                level.entities.add(new EntityProjectile(level.getPlayer().getX(), level.getPlayer().getY(), x, y, t));
                SoundEngine.getInstance().play("laser");
            }
        }
    }

    @Override
    public void onSwitch(me.jack.LD35.Level.Level level) {

    }

    @Override
    public void dealAOEDamage(ArrayList<Entity> hit, me.jack.LD35.Level.Level level) {
        for(Entity e : hit){
            if(e instanceof EntityRobot) {
                e.health -= 20;
            }
        }
    }
}

