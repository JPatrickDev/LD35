package me.jack.LD35.Level;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityPlayer;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Tile.Tile;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jack on 16/04/2016.
 */
public class Level {

    private int[][] tiles;
    private int width, height;

    public ArrayList<Rectangle> hitboxes = new ArrayList<>();


    EntityPlayer player;
    public CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width][height];
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                tiles[x][y] = 0;
            }
        }

        for (int x = 0; x != width; x++) {
            tiles[x][0] = 1;
            tiles[x][height - 1] = 1;
        }

        for (int y = 0; y != height; y++) {
            tiles[0][y] = 1;
            tiles[width - 1][y] = 1;
        }

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                int i = getTileAt(x,y);
                if(Tile.tileLookup.get(i).isSolid()){
                    hitboxes.add(new Rectangle(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE,Tile.TILE_SIZE ,Tile.TILE_SIZE));
                }
            }
        }
        player = new EntityPlayer(60,60);
        entities.add(new EntityRobot(60,60));
    }


    public int getTileAt(int x, int y) {
        return tiles[x][y];
    }

    public void setTileAt(int x, int y, int i) {
        tiles[x][y] = i;
    }

    public void render(Graphics g) {
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                Tile.tileLookup.get(tiles[x][y]).render(g, x, y);
            }
        }
        for(Entity e : entities)
        e.render(g);
        player.render(g);
    }
    Random r= new Random();

    public void update(){
        for(Entity e : entities)
        e.update(this);
        player.update(this);

        if(r.nextInt(20) == 0){
            entities.add(new EntityRobot(r.nextInt(400),r.nextInt(400)));
        }
    }
    public boolean canMove(float x, float y, float w, float h) {
        Rectangle checking = new Rectangle((int)x, (int)y, (int)w, (int)h);
        for (Rectangle hitbox : hitboxes) {
            if (checking.intersects(hitbox))
                return false;
        }
        return true;
    }

    public void clicked(int x, int y, int button) {
        player.mouseClick(x,y,button,this);
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}
