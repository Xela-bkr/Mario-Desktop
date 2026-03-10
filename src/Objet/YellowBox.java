package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class YellowBox extends Objet {
	
	public Item item;

	public YellowBox(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		imagePath = "/image/yellowbox.png";
		this.icone = new ImageIcon("src/image/yellowbox.png");
		this.image = icone.getImage();
		this.image = this.image.getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		setNom("YellowBox");
	}
	public void setItem(Item it) {
		this.item = it;
	}
	public void setImage(String nom, String format) {
		this.icone = new ImageIcon("src/image/"+nom+format);
		this.image = icone.getImage().getScaledInstance(this.largeur,  this.hauteur,  Image.SCALE_FAST);
	}

}
