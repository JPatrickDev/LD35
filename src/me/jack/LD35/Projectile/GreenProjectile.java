package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 17/04/2016.
 */
public class GreenProjectile extends Projectile{

    public GreenProjectile() {
        super(10f, 4, 50, 0,1, 500, "Green Bullet");
    }

    @Override
    public void onDestroy(Level level) {

    }

    @Override
    public void onCollideWithEntity(Level level, Entity entity) {

    }

    @Override
    public void onSpawn(Level level) {

    }
}
