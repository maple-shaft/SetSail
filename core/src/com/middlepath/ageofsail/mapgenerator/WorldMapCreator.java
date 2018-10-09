package com.middlepath.ageofsail.mapgenerator;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WorldMapCreator {

	public static final int width = 2160;
	public static final int height = 1080;



	public static byte[][] chunkArray(byte[] array, int chunkSize) {
		int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
		byte[][] output = new byte[numOfChunks][];
		for (int i = 0; i < numOfChunks; i++) {
			int start = i * chunkSize;
			int length = Math.min(array.length - start, chunkSize);
			byte[] temp = new byte[length];
			System.arraycopy(array, start, temp, 0, length);
			output[i] = temp;
		}
		return output;
	}

	public static TileType[][] loadTileData() throws Exception {
		InputStream is = WorldMapCreator.class.getResourceAsStream("/maps/uwnhmap.png");
		BufferedImage bufferedImage = ImageIO.read(is);
		TileType[][] retVal = new TileType[height * 2][width * 2];
		int retXCounter = 0;
		int retYCounter = 0;
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				int p = bufferedImage.getRGB(xx, yy);
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				TileType baseTile = TileType.createTile(new int[] { r, g, b });
				retVal[retYCounter][retXCounter] = baseTile;
				retVal[retYCounter][retXCounter + 1] = baseTile;
				retVal[retYCounter + 1][retXCounter] = baseTile;
				retVal[retYCounter + 1][retXCounter + 1] = baseTile;
				retXCounter += 2;
			}
			retYCounter += 2;
			retXCounter = 0;
		}
		
		return retVal;
	}

	public static void changeDocument(TileType[][] tileData) throws Exception {
		// map -> layer -> data -> tile
		FileWriter wrir = new FileWriter(new File("c:/Users/Dustin/Desktop/modmap.tmx"));
		BufferedWriter writer = new BufferedWriter(wrir);
		
		final String startDoc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<map version=\"1.0\" tiledversion=\"1.1.6\" orientation=\"orthogonal\" renderorder=\"right-up\" width=\"4320\" height=\"2160\" tilewidth=\"64\" tileheight=\"64\" infinite=\"0\" nextobjectid=\"1\">\r\n" + 
				" <tileset firstgid=\"1\" source=\"overworld.tsx\"/>\r\n" + 
				" <layer name=\"Tile Layer 1\" width=\"4320\" height=\"2160\">\r\n" + 
				"  <data encoding=\"csv\">";
		final String endDoc = "</data></layer></map>";
		writer.write(startDoc);
		
		StringBuffer sb = new StringBuffer();
		Tile[][] tiles = Tile.createTilesFromTileTypes(tileData);
		for (int y = 0; y < tileData.length; y++) {
			for (int x = 0; x < tileData[0].length; x++) {
				sb.append((tiles[y][x].toString()));
			}
			sb.append("\n\t");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		writer.write(sb.toString());
		writer.write(endDoc);
		writer.flush();
		writer.close();
		return;
	}
	
	public static void loadCities() throws Exception {
		InputStream is = WorldMapCreator.class.getResourceAsStream("/data/cities.csv");
		//is = WorldMapCreator.class.getResourceAsStream("/maps/uwnhmap.png");
		CSVFormat format = CSVFormat.DEFAULT.withDelimiter('|').withFirstRecordAsHeader().withTrim(true);
		for (CSVRecord record : CSVParser.parse(is, Charset.defaultCharset(), format)) {
			System.out.println(record.toString());
			
		}
	}

	public static void main(String[] args) throws Exception {

		//TileType[][] tileData = loadTileData();
		//tileData = firstPass(tileData);
		//tileData = firstPass(tileData);
		
		//tileData = secondPass(tileData);
		/*Map<TileType, Long> countTiles = tileData.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		countTiles.entrySet().stream()
				.forEach(kv -> System.out.println("TileType: " + kv.getKey() + " = " + kv.getValue()));
*/
		//changeDocument(tileData);
		loadCities();
	}

}
