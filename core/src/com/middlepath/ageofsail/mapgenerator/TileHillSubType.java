package com.middlepath.ageofsail.mapgenerator;

public enum TileHillSubType implements TileSubType {

	ANYLAND_INSIDEBOTTOMRIGHT(114, createHills(INSIDEBOTTOMRIGHT)),
	ANYLAND_INSIDETOPRIGHT(93, createHills(INSIDETOPRIGHT)),
	ANYLAND_INSIDETOPLEFT(92, createHills(INSIDETOPLEFT)),
	ANYLAND_INSIDEBOTTOMLEFT(113, createHills(INSIDEBOTTOMLEFT)),
	ANYLAND_BOTTOM(180, createHills(BOTTOM)),
	ANYLAND_TOP(54, createHills(TOP)),
	ANYLAND_LEFT(73, createHills(LEFT)),
	ANYLAND_RIGHT(76, createHills(RIGHT)),
	ANYLAND_TOPLEFT(52, createHills(TOPLEFT)),
	ANYLAND_TOPRIGHT(55, createHills(TOPRIGHT)),
	ANYLAND_BOTTOMLEFT(178, createHills(BOTTOMLEFT)),
	ANYLAND_BOTTOMRIGHT(181, createHills(BOTTOMRIGHT)),
	ANYLAND_MAIN(116, MAIN);
	
	final int gid;
	final TileNeighbors tileNeighbors;
	
	TileHillSubType(int gid, TileType[] tileNeighbors) {
		this.gid = gid;
		this.tileNeighbors = new TileNeighbors();
		this.tileNeighbors.neighbors = tileNeighbors;
	}

	@Override
	public int getGid() {
		return this.gid;
	}
	
	static TileType[] createHills(TileType[] tt) {
		TileType[] retVal = new TileType[8];
		for (int i = 0; i < 8; i++) {
			if (tt[i] == TileType.BL) {
				retVal[i] = TileType.NOTHILL;
			} else if (tt[i] == TileType.ANYLAND) {
				retVal[i] = TileType.DT;
			} else {
				retVal[i] = null;
			}
		}
		return retVal;
	}
}
