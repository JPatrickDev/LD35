package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Mob;
import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 16/04/2016.
 */
public class LaserProjectile extends Projectile{

    public LaserProjectile() {
        super(1f, 20, "Laser", 5, 0, 0);
    }

    @Override
    public void onSpawn(Level level) {

    }

    @Override
    public void onHitMob(Level level, Mob mob) {

    }

    @Override
    public void onDestroy(Level level) {

    }
}
