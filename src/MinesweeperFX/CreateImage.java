package MinesweeperFX;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;





public class CreateImage {

	public static void main(String[] args) throws IOException {
		int width=300;
		int height=300;
				
		BufferedImage buffImg = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = buffImg.createGraphics(); 
		
		g2d.setColor(Color.GREEN); //import java.awt.Color;
		g2d.fillRect(0, 0,  width,  height);
		
        g2d.setColor(Color.black);
        g2d.fillOval(0,0,width,height);
        
        g2d.setColor(Color.ORANGE);
        g2d.drawString("BOMB", 125, 125);
        
        g2d.dispose();
        
        //savefile
        File file = new File("bombImg.png");  //file is bombImg.png
        ImageIO.write(buffImg, "png", file);
        
        
        
        
	}

}
