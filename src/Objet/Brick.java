package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Brick extends Objet{
	
	public Brick(int x, int y, int largeur, int hauteur) {
		super(x, y, largeur, hauteur);
		imagePath = "/image/brick.png";
		this.icone = new ImageIcon(getClass().getResource("/image/brick.png"));
		this.image = icone.getImage();
		this.image = this.image.getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		setNom("brique");
	}
	public String getImagePath() {return this.imagePath;}

}
