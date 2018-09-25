package com.middlepath.ageofsail.mapgenerator;

public enum TileForestSubType implements TileSubType {

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
	ANYLAND_MAIN(91, MAIN);
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileForestSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}

	@Override
	public int getGid() {
		return this.gid;
	}
}
