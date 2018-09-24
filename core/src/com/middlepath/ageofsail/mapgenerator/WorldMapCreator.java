package com.middlepath.ageofsail.mapgenerator;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WorldMapCreator {

	public static final int width = 2160;
	public static final int height = 1080;

	public static byte[] reverse(byte[] array) {

		int j = array.length - 1;
		// swap the values at the left and right indices //////
		for (int i = 0; i <= j; i++) {
			byte temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			j--;
		}

		return array;
	}

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

	public static MyHandler loadTMX() throws Exception {
		InputStream is = WorldMapCreator.class.getResourceAsStream("/maps/wth.tmx");
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		MyHandler handler = new MyHandler();
		saxParser.parse(is, handler);
		
		// File fXmlFile = new File("/Users/mkyong/staff.xml");
		// DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// Document doc = dBuilder.parse(is);

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		// doc.getDocumentElement().normalize();
		return handler;

	}

	public static TileType[][] loadTileData() throws Exception {
		InputStream is = WorldMapCreator.class.getResourceAsStream("/maps/uwnhmap.png");
		BufferedImage bufferedImage = ImageIO.read(is);
		TileType[][] retVal = new TileType[height][width];
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				int p = bufferedImage.getRGB(xx, yy);
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				retVal[yy][xx] = TileType.createTile(new int[] { r, g, b });
			}
		}
		return retVal;
	}

	public static void changeDocument(MyHandler handler, TileType[][] tileData) throws Exception {
		// map -> layer -> data -> tile
		// NodeList nl =
		// doc.getFirstChild().getChildNodes().item(1).getFirstChild().getChildNodes();

		FileWriter wrir = new FileWriter(new File("c:/Users/Dustin/Desktop/modmap.tmx"));
		BufferedWriter writer = new BufferedWriter(wrir);
		
		final String startDoc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<map version=\"1.0\" tiledversion=\"1.1.6\" orientation=\"orthogonal\" renderorder=\"right-up\" width=\"2160\" height=\"1080\" tilewidth=\"64\" tileheight=\"64\" infinite=\"0\" nextobjectid=\"1\">\r\n" + 
				" <tileset firstgid=\"1\" source=\"overworld.tsx\"/>\r\n" + 
				" <layer name=\"Tile Layer 1\" width=\"2160\" height=\"1080\">\r\n" + 
				"  <data>";
		final String endDoc = "</data></layer></map>";
		writer.write(startDoc);
		LinkedList<String> nl = handler.getTileTags();
		
		Tile[][] tiles = Tile.createTilesFromTileTypes(tileData);
		for (int y = 0; y < tileData.length; y++) {
			for (int x = 0; x < tileData[0].length; x++) {
				String t = nl.pop();
				if (tileData[y][x] == TileType.BL) {
					writer.write(t);
					continue;
				} else {
					writer.write(tiles[y][x].toString());
				}
			}
		}
			
		writer.write(endDoc);
		writer.flush();
		writer.close();
		//FileWriter writer = new FileWriter(new File("c:/Users/Dustin/Desktop/modmap.tmx"));

		//writer.write(finalDoc.toString());
		//Transformer xformer = TransformerFactory.newInstance().newTransformer();
		//xformer.transform(new DOMSource(doc), new StreamResult(new File("c:/Users/Dustin/Desktop/modmap.tmx")));
		return;
	}

	public static int getRandomGid(int[] gids) {
		Random r = new Random();
		int gindex = r.nextInt(gids.length);
		return gids[gindex];
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MyHandler doc = loadTMX();

		//System.out.println(doc.getDocumentElement());

		TileType[][] tileData = loadTileData();

		/*Map<TileType, Long> countTiles = tileData.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		countTiles.entrySet().stream()
				.forEach(kv -> System.out.println("TileType: " + kv.getKey() + " = " + kv.getValue()));
*/
		changeDocument(doc, tileData);
		
	}

}
