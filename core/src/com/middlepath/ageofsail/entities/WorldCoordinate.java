package com.middlepath.ageofsail.entities;

public class WorldCoordinate {

	public static final int PIXELWIDTH = 2160;
	public static final int PIXELHEIGHT = 1080;
	public static final int PIXELSPER = 6;
	public static final float PIXELSPERLAT = 7.26f;
	public static final int MERIDIAN = 180;
	public static final int MERIDIAN_ALT = 1260;
	public static final int EQUATOR = 642;
	
	private final int x;
	private final int y;
	
	public WorldCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public WorldCoordinate(String latitude, String longitude) throws Exception {
		this(estimateX(longitude), estimateY(latitude));
	}
	
	public static int estimateX(String longitude) throws Exception {
		if (longitude == null)
			return -1;
		char suffix = longitude.charAt(longitude.length() - 1);
		int num = Integer.parseInt(longitude.substring(0, longitude.lastIndexOf((int)suffix)));
		if (suffix == 'E') {
			return (PIXELSPER * num) + MERIDIAN;
		} else if (suffix == 'W') {
			int temp = (PIXELSPER * (180 - num)) + MERIDIAN_ALT;
			return (temp > PIXELWIDTH) ? (temp - PIXELWIDTH) : temp;
		} else {
			return -1;
		}
	}
	
	public static int estimateY(String latitude) throws Exception {
		if (latitude == null)
			return -1;
		char suffix = latitude.charAt(latitude.length() - 1);
		int num = Integer.parseInt(latitude.substring(0, latitude.lastIndexOf((int)suffix)));
		if (suffix == 'N') {
			//return PIXELHEIGHT - (EQUATOR + (int)(num * PIXELSPERLAT));
			return EQUATOR - (int)(num * PIXELSPERLAT);
		} else if (suffix == 'S') {
			return EQUATOR + (int)(num * PIXELSPERLAT);
		} else {
			return -1;
		}
	}
	
	public String getLatitude() {
		StringBuffer sb = new StringBuffer();
		char latitudeSuffix;
		int ret;
		if (y >= EQUATOR) {
			latitudeSuffix = 'S';
			ret = (int)(new Integer(y - EQUATOR).floatValue() / PIXELSPERLAT);
			sb.append(ret).append(latitudeSuffix);
		} else {
			latitudeSuffix = 'N';
			ret = ((int)((EQUATOR) / PIXELSPERLAT)) - ((int)(new Integer(y).floatValue() / PIXELSPERLAT));
			sb.append(ret).append(latitudeSuffix);
		}
		return sb.toString();
	}
	
	public String getLongitude() {
		StringBuffer sb = new StringBuffer();
		char longitudeSuffix;
		int ret;
		if (x >= MERIDIAN && x < MERIDIAN_ALT) {
			longitudeSuffix = 'E';
			ret = (x - MERIDIAN) / PIXELSPER;
			sb.append(ret).append(longitudeSuffix);
		} else {
			longitudeSuffix = 'W';
			int temp = x - MERIDIAN_ALT;
			if (x < MERIDIAN) {
				temp = (x + PIXELWIDTH) - MERIDIAN_ALT;
			}
			ret = (MERIDIAN - 1) - ((int)(temp / PIXELSPER));
			sb.append(ret).append(longitudeSuffix);
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return getLatitude() + "," + getLongitude();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof WorldCoordinate))
			return false;
		WorldCoordinate o = (WorldCoordinate)obj;
		return this.getLatitude().equals(o.getLatitude()) && this.getLongitude().equals(o.getLongitude());
	}
	
	@Override
	public int hashCode() {
		return this.getLatitude().hashCode() ^ this.getLongitude().hashCode();
	}
	
	public static void main(String[] args) throws Exception {
		// test coordinate approximation
		WorldCoordinate wc = new WorldCoordinate(160,264);
		String expected = "52N,3W";
		String actual = wc.toString();
		System.out.println(expected.equals(actual));
		wc = new WorldCoordinate(136,252);
		expected = "54N,7W";
		actual = wc.toString();
		System.out.println(expected.equals(actual));
		wc = new WorldCoordinate(270,376);
		expected = "37N,15E";
		actual = wc.toString();
		System.out.println(expected.equals(actual));
		
		//Now test pixel estimation
		int actualInt = WorldCoordinate.estimateY("52N");
		int expectedInt = 264;
		System.out.println(expectedInt == actualInt);
	}
}
