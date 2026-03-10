package Objet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class Platform extends Objet{
	/**
	 * @author Alexandre BAKIRI
	 */
	private static final long serialVersionUID = 1L;

	//****VARIABLES****//
	private ArrayList<Image> Image = new ArrayList<Image>();
	private ArrayList<int[]> ImageDim = new ArrayList<int[]>();
	private Boolean DefaultRendering;
	/**
	 * @param posX
	 * @param posY
	 * @param largeur
	 * @param hauteur
	 */
	
	//****CONSTRUCTEUR****//
	public Platform(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		setBounds(posX, posY, largeur, hauteur);
		setDefaultRendering(true);
		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
	}
	
	//****GETTERS****//
	public ArrayList getImageData() {return this.Image;}
	
	//****SETTERS****// 
	public void setDefaultRendering(Boolean b) {
		DefaultRendering = b;
	}
	public void setBasicPlatform() {
		if(getLargeur()%55 == 0) {
			setDefaultRendering(false);
			for(int i = 0; i<4; i++) {addImage("/image/brownbloc.png", i*55, 0, 55, 55);}
			addImage("/image/grass.png", 0,  0, getLargeur() , 10);
			setBorder(null);
		}
	}
	//****METHODES****//
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(DefaultRendering){	
			g2d.setColor(Color.red);
			g2d.fillRect(0, 0, largeur, hauteur);
		}
		if(Image.size()>0) {
			for(int i = 0; i<Image.size(); i++) {
				g2d.drawImage(Image.get(i), ImageDim.get(i)[0], ImageDim.get(i)[1], 
						ImageDim.get(i)[2], ImageDim.get(i)[3], this);
				}
		}
	}
	/**
	 * @param image
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * Method for adding an Image that has to be draw
	 */
	public void addImage(Image image, int posX, int posY, int width, int height) {
		int[] tab = {posX, posY, width, height};
		Image.add(image);
		ImageDim.add(tab);
	}
	/**
	 * @param path
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * taking a path as a variable
	 */
	public void addImage(String path, int posX, int posY, int width, int height) {
		int[] tab = {posX, posY, width, height};
		Image.add(stringToImage(path));
		ImageDim.add(tab);
	}
	public Image stringToImage(String path) {
		if(path.contains("src/")) {path.replace("src/", "/");}
		return (new ImageIcon(getClass().getResource(path))).getImage();
	}
}
