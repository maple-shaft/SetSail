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

	GR_MAIN(22, null),
	GR_TOPLEFT(0, new TileType[] {null, BL, null, BL, GR, null, GR, GR}),
	GR_INSIDETOPLEFT(3, new TileType[] {GR, GR, GR, GR, GR, GR, GR, BL}),
	GR_TOP(1, new TileType[] {null, BL, null, GR, GR, GR, GR, GR}),
	GR_TOPRIGHT(2, new TileType[] {null, BL, null, GR, BL, GR, GR, null}),
	GR_INSIDETOPRIGHT(4, new TileType[] {GR,GR,GR,GR,GR,BL,GR,GR}),
	GR_LEFT(21, new TileType[] {null,GR,GR,BL,GR,null,GR,GR}),
	GR_RIGHT(23, new TileType[] {GR,GR,null,GR,BL,GR,GR,null}),
	GR_BOTTOMLEFT(42, new TileType[] {null,GR,GR,BL,GR,null,BL,null}),
	GR_INSIDEBOTTOMLEFT(24, new TileType[] {GR,GR,BL,GR,GR,GR,GR,GR}),
	GR_BOTTOM(43, new TileType[] {GR,GR,GR,GR,GR,null,BL,null}),
	GR_BOTTOMRIGHT(44, new TileType[] {GR,GR,null,GR,BL,null,BL,null}),
	GR_INSIDEBOTTOMRIGHT(25, new TileType[] {BL,GR,GR,GR,GR,GR,GR,GR});
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileGrassSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}
}
