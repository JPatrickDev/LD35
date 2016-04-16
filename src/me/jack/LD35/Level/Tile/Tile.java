package me.jack.LD35.Level.Tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;

/**
 * Created by Jack on 16/04/2016.
 */
public class Tile {

    private Image image;
    private boolean solid;
    private int tileId;

    public static SpriteSheet tileSprites;
    public static HashMap<Integer, Tile> tileLookup = new HashMap<Integer, Tile>();
    public static int ID = 0;

    public static final int TILE_SIZE = 16;

    public static void init(){
        new FloorTile();

        new WallTile();

    }
    public Tile(int tX, int tY, boolean solid) {
        if (tileSprites == null) {
            try {
                tileSprites = new SpriteSheet(new Image("res/tiles.png"), 16, 16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        this.image = tileSprites.getSprite(tX, tY);
        this.solid = solid;
        this.tileId = ID;
        tileLookup.put(ID, this);
        ID++;
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(image,x * TILE_SIZE,y * TILE_SIZE);
    }

    public boolean isSolid() {
        return solid;
    }
}
