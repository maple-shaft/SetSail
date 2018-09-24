package com.middlepath.ageofsail.mapgenerator;

import java.io.Serializable;
import java.util.Random;

public class Tile implements Serializable {

	private static final long serialVersionUID = -2405778914297889715L;
	private static Random random = new Random(serialVersionUID);
	
	public TileSubType tileSubType;
	public int x;
	public int y;
	public TileType tileType;
	public TileNeighbors tileNeighbors;
	
	private Tile() {
		
	}
	
	public static Tile createTile(int x, int y, TileType tt, TileType[][] map) {
		Tile tile = new Tile();
		tile.tileType = tt;
		tile.tileNeighbors = TileNeighbors.createTileNeighbors(x, y, map);
		
		if (tile.tileType == TileType.GR) {
			for (TileGrassSubType s : TileGrassSubType.values()) {
				if (s.tileNeighbors.equals(tile.tileNeighbors)) {
					tile.tileSubType = s;
					break;
				}
			}
		}
		tile.x = x;
		tile.y = y;
		return tile;
	}
	
	@Override
	public String toString() {
		int gid = -1;
		if (tileSubType == null) {
			gid = tileType.gids[0];
		} else if (tileType == TileType.GR) {
			gid = ((TileGrassSubType)tileSubType).gid;
		} else {
			gid = tileType.gids[0];
		}
		return "<tile gid=\"" + (gid+1) + "\" />\n\t";
	}
	
	public static Tile[][] createTilesFromTileTypes(TileType[][] tileTypes) {
		Tile[][] tiles = new Tile[tileTypes.length][tileTypes[0].length];
		for (int y = 0; y < tileTypes.length; y++) {
			for (int x = 0; x < tileTypes[0].length; x++) {
				tiles[y][x] = createTile(x, y, tileTypes[y][x], tileTypes);
			}
		}
		return tiles;
	}
}
