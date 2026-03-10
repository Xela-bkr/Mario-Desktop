package Game;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import Objet.Item;
import Objet.Platform;
import Objet.StaticBloc;
import Objet.YellowBox;

public class Niveau2 extends GameComponent{
	
	public Niveau2() {
		super();
	}
	@Override
	public void setBackground() {
		this.setBackground(new Color(50, 100, 200));
		background = new StaticBloc(0, 0, 1300, 800);
		background.setImage("sand", "png");
		background1 = new StaticBloc(0, 0, 1300, 800);
		background1.setImage("sand", "png");
	}
	@Override
	public void setSol() {
		Image floorImage = (new ImageIcon(getClass().getResource("/image/brique_pierre1.png"))).getImage();
		for(int i = 0; i<15; i++) {
			StaticBloc sb = new StaticBloc(i*100, 600, 102, 100);
			sb.setImage(floorImage);
			sb.setNom("brique");
			bricks.add(sb);
			objets.add(sb);
		}
	}
	@Override
	public void setBlocs() {
		
		//BLOCS CONSTANTS//
		final int l = 55;
		final int h = 55;
		
		final int y0 = 335;
		final int y1 = 200;
		
		//ITEM CONSTANTS//
		final int itC = 45;
		
		//INIT ARRAYS//
		int[][] brownBlocSetter = {{500, y0}, {555, y0}, {610, y0}, {610, y0-h}, {610, y0-2*h}, {610, y0-3*h},
			{610, y0-4*h}};
		int[][] yellowBlocSetter = {{100, 50}, {800, 335}};
		String[] itemString = {"champignon", "champignon"};
		
		//AFFECTATION//
		for(int i = 0; i<brownBlocSetter.length; i++) {
			StaticBloc sb = new StaticBloc(brownBlocSetter[i][0], brownBlocSetter[i][1], l, h);
			sb.setNom(String.format("StaticBloc%d", i));
			blocs.add(sb);
			objets.add(sb);
		}
		for(int i = 0; i<yellowBlocSetter.length; i++) {
			//Creation des items
			Item it = new Item(yellowBlocSetter[i][0] + 5, yellowBlocSetter[i][1] + 5, itC, itC, itemString[i]);
			it.setIndice(objets.size());
			items.add(it);
			objets.add(it);
			
			//Creation des YellowBox
			YellowBox yb = new YellowBox(yellowBlocSetter[i][0], yellowBlocSetter[i][1], l, h);
			yb.setItem(it);
			yb.setNom(String.format("YellowBox%d", i));
			yellowBoxes.add(yb);
			objets.add(yb);
		}
		Platform p = new Platform(610 + l, 335, 100, 55);
		p.setDefaultRendering(true); p.addImage("/image/espace1.png", 0, 0, 100, 55);
		platforms.add(p);
		this.add(p);
		objets.add(p);
	}
}
