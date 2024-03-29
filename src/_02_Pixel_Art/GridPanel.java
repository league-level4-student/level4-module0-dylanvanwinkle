package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JPanel;

public class GridPanel extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] pixelArray2d;
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixelArray2d = new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		for(int i = 0; i < pixelArray2d.length; i++) {
			for(int j = 0; j < pixelArray2d[i].length; j++) {
				pixelArray2d[i][j] = new Pixel(i*pixelWidth,j*pixelHeight);
			}
		}
		
	}
	
	static void save(GridPanel data) {
		try (FileOutputStream fos = new FileOutputStream(new File(PixelArtMaker.data)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {

		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		for(int i = 0; i < pixelArray2d.length; i++) {
			for(int j = 0; j < pixelArray2d[i].length; j++) {
				if(pixelArray2d[i][j].x <= mouseX && (pixelArray2d[i][j].x + pixelWidth) >= mouseX && 
				pixelArray2d[i][j].y <= mouseY && (pixelArray2d[i][j].y + pixelHeight) >= mouseY) {
					pixelArray2d[i][j].color = color;
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for(int i = 0; i < pixelArray2d.length; i++) {
			for(int j = 0; j < pixelArray2d[i].length; j++) {
				g.setColor(pixelArray2d[i][j].color);
				g.fillRect(pixelArray2d[i][j].x, pixelArray2d[i][j].y, pixelWidth, pixelHeight);
				g.setColor(Color.BLACK);
				g.drawRect(pixelArray2d[i][j].x, pixelArray2d[i][j].y, pixelWidth , pixelHeight);
			}
		}
	}
}
