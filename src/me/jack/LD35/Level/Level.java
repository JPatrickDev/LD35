package me.jack.LD35.Level;

import me.jack.LD35.Entity.Entity;
import me.jack.LD35.Entity.EntityPlayer;
import me.jack.LD35.Entity.EntityRobot;
import me.jack.LD35.Level.Tile.Tile;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import uk.co.jdpatrick.JEngine.Particle.ParticleSystem;

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


    public ParticleSystem particleSystem = new ParticleSystem();
    EntityPlayer player;
    public CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();

    public int round = 1;
    public int needToSpawn = 5;
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
        particleSystem.render(g,0,0);
    }
    Random r= new Random();

    public void update(){
        for(Entity e : entities)
        e.update(this);
        player.update(this);

        if(r.nextInt((int) (5 + (Math.pow(round,2))/4)) == 0 && needToSpawn > 0){
            int sX = 0,sY = 0;
            if(r.nextBoolean()){
                if(r.nextBoolean()){
                    sX = 0;
                    sY = r.nextInt(height-2) + 1;
                    sY*=Tile.TILE_SIZE;
                }else{
                    sX = (width-1) * Tile.TILE_SIZE;
                    sY = r.nextInt(height-2) + 1;
                    sY*=Tile.TILE_SIZE;
                }
            }else{
                if(r.nextBoolean()){
                    sY = 0;
                    sX = r.nextInt(width-2) + 1;
                    sX*=Tile.TILE_SIZE;
                }else{
                    sY = (height-1) * Tile.TILE_SIZE;
                    sX = r.nextInt(width-2) + 1;
                    sX*=Tile.TILE_SIZE;
                }
            }
            entities.add(new EntityRobot(sX,sY));
            needToSpawn--;
        }
        if(needToSpawn <= 0){
            round++;
            needToSpawn = (int) Math.pow(round,2);
        }
        particleSystem.update();

        //System.out.println("R: " + round + " " + needToSpawn);
    }

    public boolean canMove(float x, float y, float w, float h) {
        Rectangle checking = new Rectangle((int)x, (int)y, (int)w, (int)h);
        for (Rectangle hitbox : hitboxes) {
            if (checking.intersects(hitbox))
                return false;
        }
        return true;
    }
    public void keyPressed(int key,char c){
        if(c == '1'){
            player.shift(1);
        }else if(c == '2'){
            player.shift(2);
        }else if(c == '3'){
            player.shift(3);
        }else if(c == '4'){
            player.shift(4);
        }else if(c == '5'){
            player.shift(5);
        }
    }

    public void clicked(int x, int y, int button) {
        player.mouseClick(x,y,button,this);
    }

    public void pressed(int x, int y, int button) {
        player.mouseDown(x,y,button,this);
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}
