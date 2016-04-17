package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Level;
import me.jack.LD35.Particles.ExplosionParticle;
import me.jack.LD35.Particles.FireParticle;

/**
 * Created by Jack on 17/04/2016.
 */
public class MissileProjectile extends Projectile{

    public MissileProjectile() {
        super(10f, 20, 50, 1,1, 500, "Missile");
    }

    @Override
    public void onDestroy(Level level) {

    }

    @Override
    public void onCollideWithEntity(Level level, Entity entity) {
        if(entity instanceof EntityRobot){
            for(int i =0 ;i!= 300;i++)
                level.particleSystem.addParticle(new ExplosionParticle((int)entity.getX(),(int)entity.getY()));
        }

    }

    @Override
    public void onSpawn(Level level) {

    }
}
