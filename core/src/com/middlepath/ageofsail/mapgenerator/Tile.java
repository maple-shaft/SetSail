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
		tile.tileNeighbors = TileNeighbors.createTileNeighbors(x, y, map);
		
		if (tt == TileType.GR) {
			for (TileGrassSubType s : TileGrassSubType.values()) {
				if (s.tileNeighbors.equals(tile.tileNeighbors)) {
					tile.tileSubType = s;
					break;
				}
			}
		}
		if (tt == TileType.DS) {
			for (TileDesertSubType s : TileDesertSubType.values()) {
				if (s.tileNeighbors.equals(tile.tileNeighbors)) {
					tile.tileSubType = s;
					break;
				}
			}
		}
		if (tt == TileType.FT) {
			for (TileForestSubType s : TileForestSubType.values()) {
				if (s.tileNeighbors.equals(tile.tileNeighbors)) {
					tile.tileSubType = s;
					break;
				}
			}
		}
		if (tt == TileType.DT) {
			for (TileHillSubType s : TileHillSubType.values()) {
				if (s.tileNeighbors.equals(tile.tileNeighbors)) {
					tile.tileSubType = s;
					break;
				}
			}
		}
		//if (tt != TileType.BL && tile.tileSubType != TileGrassSubType.ANYLAND_MAIN)
		//	tile.tileType = TileType.GR;
		//else
		tile.tileType = tt;
		tile.x = x;
		tile.y = y;
		return tile;
	}
	
	@Override
	public String toString() {
		int gid = -1;
		if (tileSubType == null) {
			gid = tileType.gids[0];
		} else {
			gid = tileSubType.getGid();
		}
		return (gid+1) + ",";
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
