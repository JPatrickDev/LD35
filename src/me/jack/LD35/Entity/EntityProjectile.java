package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.GreenProjectile;
import me.jack.LD35.Projectile.Projectile;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityProjectile extends Entity{

    float vX,vY;
    float life = 1f;
    Projectile projectile;

    public EntityProjectile(float x, float y, int tX, int tY, Projectile projectile) {
        super(x, y, 16,16);
        float xSpeed = (tX - x);
        float ySpeed = (tY - y);
        float factor = (float) (projectile.getMoveSpeed()/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;
        life = projectile.getLifeSpan();
        this.projectile = projectile;
        drawAngle = (float) -(Math.atan2(this.getX() - tX, this.getY() - tY) * 180 / Math.PI);
    }

    float drawAngle = 0;
    @Override
    public void render(Graphics g) {
        projectile.getImage().setRotation(drawAngle);
        g.drawImage(this.projectile.getImage(),getX(),getY());
        projectile.getImage().setRotation(0f);
    }

    @Override
    public void update(Level level) {
        if(life <= 0)
            level.entities.remove(this);
        addX(vX);
        addY(vY);



    }

    public void hitEntity(Entity entity,Level level) {
        if(entity instanceof EntityRobot && !(this.projectile instanceof GreenProjectile)){
            entity.health-=projectile.getDamage();
            life = 0;
            projectile.onCollideWithEntity(level,entity);
        }
    }
}
