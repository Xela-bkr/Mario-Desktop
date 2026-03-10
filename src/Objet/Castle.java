package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Castle extends Objet{

	public Castle(int posX, int posY, int largeur, int hauteur) {
		super(posX, posY, largeur, hauteur);
		imagePath = "/image/castle.png";
		this.icone = new ImageIcon(getClass().getResource("/image/castle.png"));
		this.image = icone.getImage();
		this.image = this.image.getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
		setNom("Chateau");
	}

}
