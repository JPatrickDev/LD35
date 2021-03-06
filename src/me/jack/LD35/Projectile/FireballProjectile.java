package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Particles.FireParticle;

/**
 * Created by Jack on 16/04/2016.
 */
public class FireballProjectile extends Projectile{

    public FireballProjectile() {
        super(1f, 5, 50,1,0,200,"Fire Ball");
    }

    @Override
    public void onDestroy(Level level) {

    }

    @Override
    public void onCollideWithEntity(Level level,Entity entity) {
        if(entity instanceof EntityRobot){
            ((EntityRobot)entity).onFire = true;
            for(int i =0 ;i!= 300;i++)
            level.particleSystem.addParticle(new FireParticle((int)entity.getX(),(int)entity.getY()));
        }

    }

    @Override
    public void onSpawn(Level level) {

    }
}
