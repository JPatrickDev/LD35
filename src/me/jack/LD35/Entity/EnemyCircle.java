package me.jack.LD35.Entity;

import me.jack.LD35.Level.Level;
import me.jack.LD35.Level.Tile.Tile;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 16/04/2016.
 */
public class EnemyCircle extends Mob {
    public EnemyCircle(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(getX(), getY(), 16, 16);
    }


    @Override
    public void update(Level level) {
        super.update(level);
        Player p = level.getPlayer();
        float xSpeed = (p.getX() - getX());
        float ySpeed = (p.getY() - getY());
        float factor = (float) (5 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        addX(xSpeed);
        addY(ySpeed);
    }
}
