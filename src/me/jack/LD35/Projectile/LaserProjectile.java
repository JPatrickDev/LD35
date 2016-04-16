package me.jack.LD35.Projectile;

import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 16/04/2016.
 */
public class LaserProjectile extends Projectile{

    public LaserProjectile(){
        super(5f, 10, 20, 0, 0);
    }

    @Override
    public void onDestroy(Level level) {

    }

    @Override
    public void onCollideWithEntity(Level level) {

    }

    @Override
    public void onSpawn(Level level) {

    }
}
