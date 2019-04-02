package model;

import util.Resources;
import util.StartNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Map extends JPanel {

    private int width, height;
    private int tileSize;
    private Tile[][] mapTile;
    private Tile[][] mapCopy;

    public Map(int[][] mapInt, int widthInTiles, int heightInTiles, int tileSize, HashMap<Integer, Tile> tileSet) {
        width = widthInTiles;
        height = heightInTiles;
        this.tileSize = tileSize;
        init(widthInTiles, heightInTiles);
        initMapTile(mapInt, tileSet);
    }

    private void init(int width, int height) {
        setSize(width * tileSize, height * tileSize);
        setBackground(Color.BLACK);
    }

    private void initMapTile(int[][] mapInt, HashMap<Integer, Tile> tileSet) {
        mapTile = new Tile[mapInt.length][mapInt[0].length];
        for (int y = 0; y < mapInt.length; y++) {
            for (int x = 0; x < mapInt[0].length; x++) {
                for (Integer i : tileSet.keySet()) {
                    if (i == mapInt[y][x]) {
                        mapTile[y][x] = tileSet.get(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < mapTile.length; y++) {
            for (int x = 0; x < mapTile[0].length; x++) {
                switch(mapTile[y][x]) {
                    case START:
                        g.drawImage(Resources.tileStart.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case FINISH:
                        g.drawImage(Resources.tileFinish.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case CHECKPOINT:
                        g.drawImage(Resources.tileCheckpoint.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case ROAD:
                        g.drawImage(Resources.tileRoad.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case GRASS:
                        g.drawImage(Resources.tileGrass.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case WATER:
                        g.drawImage(Resources.tileWater.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case WALL:
                        g.drawImage(Resources.tileWall.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case SAND:
                        g.drawImage(Resources.tileSand.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                    case ICE:
                        g.drawImage(Resources.tileIce.getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH), x * tileSize, y * tileSize, null);
                        break;
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return mapTile[y][x];
    }

    public Tile getTile(int[] coordinates) {
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("method getTile only accepts two ints or an int array with the length of 2 as argument");
        } else {
            return mapTile[coordinates[1]][coordinates[0]];
        }
    }

    public boolean isTileRideable(int x, int y) {
        if (getTile(x, y) == model.Tile.WALL) {
            return false;
        } else {
            return true;
        }
    }

    public int getWidthInTiles() {
        return width;
    }

    public int getHeightInTiles() {
        return height;
    }

    public Tile[][] getMapCopy() {
        mapCopy = new Tile[mapTile.length][mapTile[0].length];
        for (int x = 0; x < mapTile.length; x++) {
            for (int y = 0; y < mapTile[0].length; y++) {
                mapCopy[x][y] = mapTile[x][y];
            }
        }
        return mapCopy;
    }

    @SuppressWarnings("Duplicates")
    public int getStartX() throws StartNotFoundException {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getTile(x, y) == model.Tile.START) {
                    return x;
                }
            }
        }
        System.out.println("Error: start not found on the map");
        throw new StartNotFoundException();
    }

    @SuppressWarnings("Duplicates")
    public int getStartY() throws StartNotFoundException {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getTile(x, y) == model.Tile.START) {
                    return y;
                }
            }
        }
        System.out.println("Error: start not found on the map");
        throw new StartNotFoundException();
    }

}
