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
            if (p.getX() > getX()) {
                if(level.canMove(getX()+5,getY()-1,16,16) == null) {
                    if(level.canMove(getX()+16,getY()+3,16,16) != null) {
                        addX(5);
                    }
                }
            } else {
                if(level.canMove(getX()-5,getY()-1,16,16) == null) {
                    if(level.canMove(getX()-16,getY()+3,16,16) != null)
                    addX(-5);
                }
            }
        }
}
