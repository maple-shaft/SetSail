package com.middlepath.ageofsail.mapgenerator;

/**
 * Contains a gid for the tileset as well as a TileNeighbors object that maps what its surrounding tiles are 
 * supposed to look like for it to qualify.
 * 
 * @author Dustin
 *
 */
public enum TileGrassSubType implements TileSubType {

	ANYLAND_INSIDEBOTTOMRIGHT(25, INSIDEBOTTOMRIGHT),
	ANYLAND_INSIDETOPRIGHT(4, INSIDETOPRIGHT),
	ANYLAND_INSIDETOPLEFT(3, INSIDETOPLEFT),
	ANYLAND_INSIDEBOTTOMLEFT(24, INSIDEBOTTOMLEFT),
	ANYLAND_BOTTOM(43, BOTTOM),
	ANYLAND_TOP(1, TOP),
	ANYLAND_LEFT(21, LEFT),
	ANYLAND_RIGHT(23, RIGHT),
	ANYLAND_TOPLEFT(0, TOPLEFT),
	ANYLAND_TOPRIGHT(2, TOPRIGHT),
	ANYLAND_BOTTOMLEFT(42, BOTTOMLEFT),
	ANYLAND_BOTTOMRIGHT(44, BOTTOMRIGHT),
	ANYLAND_MAIN(22, MAIN);
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileGrassSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}

	@Override
	public int getGid() {
		return this.gid;
	}
}
