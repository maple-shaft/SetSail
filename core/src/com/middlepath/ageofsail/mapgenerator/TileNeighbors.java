package com.middlepath.ageofsail.mapgenerator;

import java.util.Iterator;

public class TileNeighbors implements Iterable<TileType> {

	TileType[] neighbors = new TileType[8];
	
	public TileNeighbors() {
	}
	
	@Override
	/**
	 * Compares TileNeighbors objects based on identical neighboring TileType's.
	 * Null is a wild card which means the tile is insignificant.
	 * 
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof TileNeighbors) || this.neighbors == null)
			return false;
		
		TileNeighbors o = (TileNeighbors)obj;
		boolean retVal = true;
		for (int i = 0; i < 8; i++) {
			if (o.neighbors[i] == null || this.neighbors[i] == null) {
				continue;
			} else if (o.neighbors[i] == TileType.ANYLAND && this.neighbors[i] != TileType.BL) {
				continue;
			} else if (this.neighbors[i] == TileType.ANYLAND && o.neighbors[i] != TileType.BL) {
				continue;
			}
			retVal &= o.neighbors[i] == this.neighbors[i];
		}
		return retVal;
	}
	
	/**
	 * Create a TileNeighbors based on a TileType 2D array
	 * 
	 * @param x
	 * @param y
	 * @param map
	 * @return
	 */
	public static TileNeighbors createTileNeighbors(int x, int y, TileType[][] map) {
		TileNeighbors retVal = new TileNeighbors();
		
			
		if (x > 0) {
			retVal.neighbors[3] = map[y][x - 1];
			if (y != 0) {
				retVal.neighbors[0] = map[y - 1][x - 1];
			}
			if (y != 1079) {
				retVal.neighbors[5] = map[y + 1][x - 1];
			}
			
		}
		
		if (x < 2159) {
			retVal.neighbors[4] = map[y][x + 1];
			if (y != 0) {
				retVal.neighbors[2] = map[y - 1][x + 1];
			}
			if (x < 2159 && y < 1079) {
				retVal.neighbors[7] = map[y + 1][x + 1];
			}
		
		}
		
		if (y > 0) {
			retVal.neighbors[1] = map[y - 1][x];
		}
		
		if (y < 1079) {
			retVal.neighbors[6] = map[y + 1][x];
		}
		return retVal;
	}
	
	@Override
	public Iterator<TileType> iterator() {
		return new TileNeighborIterator(this);
	}
	
	class TileNeighborIterator implements Iterator<TileType> {

		private final TileNeighbors tileNeighbors;
		private int counter = 0;
		
		public TileNeighborIterator(TileNeighbors tileNeighbors) {
			this.tileNeighbors = tileNeighbors;
		}
		
		@Override
		public boolean hasNext() {
			return counter <= 7;
		}

		@Override
		public TileType next() {
			return this.tileNeighbors.neighbors[counter++];
		}
		
	}

}
