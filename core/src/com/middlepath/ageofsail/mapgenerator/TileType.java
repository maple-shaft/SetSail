package com.middlepath.ageofsail.mapgenerator;

public enum TileType {

	GR(0, 255, 0, (new int[] {23,46,47})),
	BL(0, 0, 255, (new int[] {71})),
	DT(128, 128, 0, (new int[] {96,97,117,118})),
	DS(255, 255, 0, (new int[] {86,149,109,110})),
	FT(0, 128, 0, (new int[] {154,174,175,153})),
	PT(255, 0, 0, (new int[] {9,10,30,31})),
	SP(0, 255, 255, (new int[] {100})),
	NOTHING(0, 0, 0, new int[] {72});
	
	final int[] gids;
	final int r;
	final int g;
	final int b;
	
	private TileType(int r, int g, int b, int[] gids) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.gids = gids;
	}
	
	public static TileType createTile(int[] argb) {
		for (TileType tt : TileType.values()) {
			if (tt.r == argb[0] && tt.g == argb[1] && tt.b == argb[2]) {
				return tt;
			}
		}
		return NOTHING;
	}
}
