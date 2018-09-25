package com.middlepath.ageofsail.mapgenerator;

import static com.middlepath.ageofsail.mapgenerator.TileType.ANYLAND;
import static com.middlepath.ageofsail.mapgenerator.TileType.BL;

public interface TileSubType {

	TileType[] INSIDEBOTTOMRIGHT = new TileType[] {BL,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND};
	TileType[] INSIDETOPRIGHT = new TileType[] {ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,BL,ANYLAND,ANYLAND};
	TileType[] INSIDETOPLEFT = new TileType[] {ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND, BL};
	TileType[] INSIDEBOTTOMLEFT = new TileType[] {ANYLAND,ANYLAND,BL,ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND};
	TileType[] BOTTOM = new TileType[] {ANYLAND,ANYLAND,ANYLAND,ANYLAND,ANYLAND,null,BL,null};
	TileType[] TOP = new TileType[] {null, BL, null, ANYLAND, ANYLAND, ANYLAND, ANYLAND, ANYLAND};
	TileType[] LEFT = new TileType[] {null,ANYLAND,ANYLAND,BL,ANYLAND,null,ANYLAND,ANYLAND};
	TileType[] RIGHT = new TileType[] {ANYLAND,ANYLAND,null,ANYLAND,BL,ANYLAND,ANYLAND,null};
	TileType[] TOPLEFT = new TileType[] {null, BL, null, BL, ANYLAND, null, ANYLAND, ANYLAND};
	TileType[] TOPRIGHT = new TileType[] {null, BL, null, ANYLAND, BL, ANYLAND, ANYLAND, null};
	TileType[] BOTTOMLEFT = new TileType[] {null,ANYLAND,ANYLAND,BL,ANYLAND,null,BL,null};
	TileType[] BOTTOMRIGHT = new TileType[] {ANYLAND,ANYLAND,null,ANYLAND,BL,null,BL,null};
	TileType[] MAIN = new TileType[] {null,null,null,null,null,null,null,null};
	
	int getGid();
}
