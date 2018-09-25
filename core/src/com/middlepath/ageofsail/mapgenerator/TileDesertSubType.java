package com.middlepath.ageofsail.mapgenerator;

public enum TileDesertSubType implements TileSubType {
	
	ANYLAND_INSIDEBOTTOMRIGHT(151, INSIDEBOTTOMRIGHT),
	ANYLAND_INSIDETOPRIGHT(130, INSIDETOPRIGHT),
	ANYLAND_INSIDETOPLEFT(129, INSIDETOPLEFT),
	ANYLAND_INSIDEBOTTOMLEFT(150, INSIDEBOTTOMLEFT),
	ANYLAND_BOTTOM(169, BOTTOM),
	ANYLAND_TOP(127, TOP),
	ANYLAND_LEFT(147, LEFT),
	ANYLAND_RIGHT(149, RIGHT),
	ANYLAND_TOPLEFT(126, TOPLEFT),
	ANYLAND_TOPRIGHT(128, TOPRIGHT),
	ANYLAND_BOTTOMLEFT(168, BOTTOMLEFT),
	ANYLAND_BOTTOMRIGHT(170, BOTTOMRIGHT),
	ANYLAND_MAIN(148, MAIN);
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileDesertSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}

	@Override
	public int getGid() {
		return this.gid;
	}
}
