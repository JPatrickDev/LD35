package me.jack.LD35.Shape;

import me.jack.LD35.Entity.EntityProjectile;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.LaserProjectile;

/**
 * Created by Jack on 16/04/2016.
 */
public class CircleShape extends Shape{

    public CircleShape() {
        super(12, 15);
    }

    @Override
    public void attack(int x, int y, Level level) {
        level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x+5,y,new LaserProjectile()));
        level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x+4,y,new LaserProjectile()));
        level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x-4,y,new LaserProjectile()));
        level.entities.add(new EntityProjectile(level.getPlayer().getX(),level.getPlayer().getY(),x-5,y,new LaserProjectile()));

    }

    @Override
    public void onSwitch(Level level) {

    }
}
