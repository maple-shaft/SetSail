package com.middlepath.ageofsail.mapgenerator;

import static com.middlepath.ageofsail.mapgenerator.TileType.*;

/**
 * Contains a gid for the tileset as well as a TileNeighbors object that maps what its surrounding tiles are 
 * supposed to look like for it to qualify.
 * 
 * @author Dustin
 *
 */
public enum TileGrassSubType implements TileSubType {

	ANYLAND_INSIDEBOTTOMRIGHT(25, new TileType[] {BL,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND}),
	ANYLAND_INSIDETOPRIGHT(4, new TileType[] {ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,BL,ANYLAND,ANYLAND}),
	ANYLAND_INSIDETOPLEFT(3, new TileType[] {ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, BL}),
	ANYLAND_INSIDEBOTTOMLEFT(24, new TileType[] {ANYLAND,ANYLAND,BL,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND}),
	ANYLAND_BOTTOM(43, new TileType[] {ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,null,BL,null}),
	ANYLAND_TOP(1, new TileType[] {null, BL, null, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND}),
	ANYLAND_LEFT(21, new TileType[] {null,ANYLAND,ANYLAND,BL,ANYLAND,null,ANYLAND,ANYLAND}),
	ANYLAND_RIGHT(23, new TileType[] {ANYLAND,ANYLAND,null,ANYLAND,BL,ANYLAND,ANYLAND,null}),
	ANYLAND_TOPLEFT(0, new TileType[] {null, BL, null, BL, ANYLAND, null, ANYLAND, ANYLAND}),
	ANYLAND_TOPRIGHT(2, new TileType[] {null, BL, null, ANYLAND, BL, ANYLAND, ANYLAND, null}),
	ANYLAND_BOTTOMLEFT(42, new TileType[] {null,ANYLAND,ANYLAND,BL,ANYLAND,null,BL,null}),
	ANYLAND_BOTTOMRIGHT(44, new TileType[] {ANYLAND,ANYLAND,null,ANYLAND,BL,null,BL,null}),
	ANYLAND_MAIN(22, new TileType[] {null,null,null,null,null,null,null,null});
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileGrassSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}
}
