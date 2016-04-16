package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Projectile.Projectile;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class EntityProjectile extends Entity {

    private float tX, tY;
    private float xVel, yVel;
    private Projectile projectile;
    private float lifeSpan = 0;

    public EntityProjectile(float x, float y, float tX, float tY, Projectile projectile) {
        super(x, y);
        this.tX = tX;
        this.tY = tY;
        this.projectile = projectile;
        lifeSpan = projectile.getLife();

        float xSpeed = (tX - x);
        float ySpeed = (tY - y);
        float factor = (float) (projectile.getMoveSpeed() / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        xVel = xSpeed;
        yVel = ySpeed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(projectile.getI(), getX(), getY());
    }

    boolean remove = false;

    @Override
    public void update(Level level) {
        addX(xVel);
        addY(yVel);
        lifeSpan-=0.25f;
        if(lifeSpan <  0)
            level.entities.remove(this);

        if(remove){
            level.entities.remove(this);
        }
    }

    public void hitMob(Mob mob){
        mob.health-=projectile.getDamage();
        remove = true;
    }
}
