package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Projectile {

    private float damage;
    private int moveSpeed;
    private int lifeSpan;
    private Image image;
    private long fireRate;
    private String name;
    private Image icon;
    public static SpriteSheet projectileImages;
    public static SpriteSheet projectileIcons;

    public Projectile(float damage, int moveSpeed, int lifeSpan, int tX,int tY,long fireRate,String name) {
        if(projectileImages == null)
            try {
                projectileImages = new SpriteSheet(new Image("res/projectiles.png"),16,16);
                projectileIcons = new SpriteSheet(new Image("res/projectileIcons.png"),32,32);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.lifeSpan = lifeSpan;
        this.image = projectileImages.getSprite(tX,tY);
        this.fireRate = fireRate;
        this.name = name;
        this.icon = projectileIcons.getSprite(tX,tY);
    }

    public abstract void onDestroy(Level level);
    public abstract void onCollideWithEntity(Level level,Entity entity);
    public abstract void onSpawn(Level level);


    public float getDamage() {
        return damage;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public Image getImage() {
        return image;
    }

    public long getFireRate() {
        return fireRate;
    }

    public String getName() {
        return name;
    }

    public Image getIcon() {
        return icon;
    }
}
