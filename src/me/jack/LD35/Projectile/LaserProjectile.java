package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Level.Level;

/**
 * Created by Jack on 16/04/2016.
 */
public class LaserProjectile extends Projectile{

    public LaserProjectile(int fireRate,float damageBoost){
        super(5+damageBoost, 10, 20, 0, 0,fireRate,"Laser");
        if(fireRate < 70)
            fireRate = 70;
    }

    @Override
    public void onDestroy(Level level) {

    }

    @Override
    public void onCollideWithEntity(Level level,Entity entity) {

    }

    @Override
    public void onSpawn(Level level) {

    }
}
