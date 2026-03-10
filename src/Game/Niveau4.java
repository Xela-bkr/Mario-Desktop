package Game;

import java.awt.Image;

import javax.swing.ImageIcon;

import Objet.StaticBloc;

public class Niveau4 extends GameComponent{
	
	public Niveau4() {
		super();
	}
	@Override
	public void setBackground() {
		Image backgroundImage = (new ImageIcon(getClass().getResource("/image/violetbackground.png"))).getImage();
		background = new StaticBloc(0, 0, 1350, 800);
		background1 = new StaticBloc(1300, 0, 1350, 800);
		background.setImage(backgroundImage);
		background1.setImage(backgroundImage);
		background.setNom("background");
		background1.setNom("background1");
	}
}
