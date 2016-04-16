package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.projectile.getImage(),getX(),getY());
    }

    @Override
    public void update(Level level) {
        if(life <= 0)
            level.entities.remove(this);
        addX(vX);
        addY(vY);
        life-=0.25f;

    }

    public void hitEntity(Entity entity) {
        if(entity instanceof EntityRobot){
            entity.health-=projectile.getDamage();
            life = 0;
        }
    }
}
