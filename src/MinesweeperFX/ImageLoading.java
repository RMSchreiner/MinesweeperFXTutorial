package MinesweeperFX;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageLoading extends Applet{
	
	//private Image pig = null;
	private Image truffle = null; //other classes may need to access this
	
	public void paint(Graphics g) {
		
		if (truffle == null)
			truffle = getImage("truffle.png");
		
		//if (pig ==null)
		//	pig = getImage("pig.png");
		
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(truffle, 25, 50, 25, 25, this); //adding this at null enabled it to upload without an error
		//g2.drawImage(pig, 25, 50, 25, 25, this);
	
		
		
		
	}
	
	public Image getImage(String path) {
		Image tempImage = null;
		//this is the method to call to get the image URL in the file system getImage("filename.png");
		//we do this to check that the image is available
		try {
			URL imageURL = ImageLoading.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);	
		}
		catch (Exception e) {
			System.out.println("Error occured" + e.getMessage());
		}
		return tempImage;
	}
	

}
