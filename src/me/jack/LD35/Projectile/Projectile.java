package me.jack.LD35.Projectile;

import me.jack.LD35.Entity.Mob;
import me.jack.LD35.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import uk.co.jdpatrick.JEngine.Image.ImageUtil;

/**
 * Created by Jack on 16/04/2016.
 */
public abstract class Projectile {

    private float damage;
    private int moveSpeed;
    private String name;
    public Image i;
    public float life;

    public static SpriteSheet sheet;

    public Projectile(float damage, int moveSpeed, String name, float life,int tX,int tY) {
        if(sheet == null)
            try {
                sheet = new SpriteSheet(new Image("res/projectiles.png"),16,16);
            } catch (SlickException e) {
                e.printStackTrace();
            }

        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.name = name;
       /* try {
            this.i = new Image("res/projectiles.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }*/
        this.i = sheet.getSprite(tX,tY);
        this.life = life;
    }

    public abstract void onSpawn(Level level);
    public abstract void onHitMob(Level level, Mob mob);
    public abstract void onDestroy(Level level);

    public float getDamage() {
        return damage;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public String getName() {
        return name;
    }

    public Image getI() {
        return i;
    }

    public float getLife() {
        return life;
    }
}
