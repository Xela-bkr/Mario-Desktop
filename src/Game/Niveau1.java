package Game;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Objet.Castle;
import Objet.Colline;
import Objet.Item;
import Objet.Piece;
import Objet.Platform;
import Objet.StaticBloc;
import Objet.Tuyau;
import Objet.YellowBox;
import Personnage.Ennemy;

public class Niveau1 extends GameComponent{
	
	private static final long serialVersionUID = 1L;

	//****VARIABLES****//
	
	public Niveau1() {super();}
	
	//****GETTERS****//
	
	//****SETTERS****//
	@Override
	public void setBlocs() {
		
		final int BW = 55;
		final int IW = 45;
		
		final int y0 = 355;
		final int y1 = 200;
	
		for(int i = 0; i<7; i++) {
			if(i == 1) {
				YellowBox yb = new YellowBox(400 + 55*i, y0, BW, BW);
				Item it = new Item(yb.getX()+5, yb.getY() + 5, IW, IW, "champignon");
				yb.setItem(it);
				//yb.setNom(String.format("YellowBox%d", i));
				items.add(it);
				yellowBoxes.add(yb);
				objets.add(it);
				objets.add(yb);
			}
			else if(i == 5) {
				YellowBox yb = new YellowBox(400 + 55*i, y0, BW, BW);
				Item it = new Item(yb.getX()+5, yb.getY() + 5, IW, IW, "etoile");
				yb.setItem(it);
				//yb.setNom(String.format("YellowBox%d", i));
				items.add(it);
				yellowBoxes.add(yb);
				objets.add(it);
				objets.add(yb);
			}
			else {
				StaticBloc sb = new StaticBloc(i*55 + 400, y0, BW, BW);
				blocs.add(sb);
				objets.add(sb);
				
			}
			if(i<=4) {
			 StaticBloc sb = new StaticBloc(i*55 + 1070, y0, BW, BW);
			 blocs.add(sb);
			 objets.add(sb);
			}
			else {
				StaticBloc sb = new StaticBloc(1345 - 55, y0+(i-4)*BW, BW, BW);
				 blocs.add(sb);
				 objets.add(sb);
			}
		}
	}
	@Override
	public void setTuyau() {
		int[][] tuyauSetter = {{900, 530, 80, 80}, {1600, 530, 80, 80}, {2400, 530, 80, 80}};
		for(int i = 0; i<tuyauSetter.length; i++) {
			Tuyau tuyau = new Tuyau(tuyauSetter[i][0], tuyauSetter[i][1], tuyauSetter[i][2], tuyauSetter[i][3]);
			tuyaux.add(tuyau);
			objets.add(tuyau);
		}
	}
	@Override
	public void setBackground() {
		Image backgroundImage = (new ImageIcon(getClass().getResource("/image/blue_screen.png"))).getImage();
		background = new StaticBloc(0, 0, 1350, 800);
		background1 = new StaticBloc(1300, 0, 1350, 800);
		background.setImage(backgroundImage);
		background1.setImage(backgroundImage);
		background.setNom("background");
		background1.setNom("background1");
	}
	@Override
	public void setSol() {

		Image brickImage = (new ImageIcon(getClass().getResource("/image/brick.png"))).getImage();
		for(int i = 0; i<20; i++) {
			StaticBloc sb = new StaticBloc(i*100, 600, 102, 100);
			sb.setImage(brickImage);
			sb.setNom("brique");
			bricks.add(sb);
			objets.add(sb);
		}
	}
	@Override
	public void setPiece() {
		final int taille = 40;
		final int espace = 2;
		for(StaticBloc sb : blocs) {
			if(sb.getY()== 355) {
				Piece p = new Piece(sb.getX() + sb.getLargeur()/2 - taille/2, sb.getY()- taille - espace, taille, taille);
				p.animer1();
				pieces.add(p);
				objets.add(p);
			}
		}
	}
	@Override 
	public void setPlatform() {
		Platform platform = new Platform(300, 150, 220, 55);
		platform.setBasicPlatform();
		
		Platform platform1 = new Platform(1200, 150, 110, 55);
		platform1.setBasicPlatform();
		
		objets.add(platform);
		objets.add(platform1);
		
		platforms.add(platform);
		platforms.add(platform1);
		
		this.add(platform);
		this.add(platform1);
	}
	@Override
	public void setEnnemies() {
		
		final int FLOOR_Y = 600; // Position du sol
		
		final int GS = 70; // Dimension du Goomba
		
		final int rkWidth = 74; // Largeur du koopa rouge
		final int rkHeight = 100; // hauteur du koopa rouge
		
		final int YG = FLOOR_Y - GS - 20; // Position initiale du Goomba avec 20px de marge
		final int YK = FLOOR_Y - rkHeight - 20; // position initiale du Koopa rouge avec 20px de marge
		
		addEnnemy(1200, YG, GS, GS, "goomba", true);
		addEnnemy(1300, YG, GS, GS, "goomba", false);
		addEnnemy(2000, YG, GS, GS, "goomba", true);
		addEnnemy(2500, YK, rkWidth, rkHeight, "redkoopa", true );
	}
	@Override
	public void setDecoration() {
		
		int[][] nuageSetter = {{ 0, 20, 100, 80}, {60, 20, 100, 80}, {200, 150, 120, 100}, {340, 65, 80, 80}, {380, 168, 80, 80},
							   {500, 40, 130, 110}, {565, 40, 130, 110}, {760, 200, 80, 70}, {899, 0, 60, 50}, {1000, 250, 40, 40}, 
							   {1020, 250, 40, 40}};
		
		Image nuage1Image = (new ImageIcon(getClass().getResource("/image/nuage1.png"))).getImage();
		nuages = new StaticBloc[nuageSetter.length];
		for(int i = 0; i<nuages.length; i++) {
			StaticBloc nuage = new StaticBloc(i*50, 0, 40, 30);
			nuage.setImage(nuage1Image);
			nuage.setNom("nuage");
			nuage.setAll(nuageSetter[i][0], nuageSetter[i][1], nuageSetter[i][2], nuageSetter[i][3]);
			nuages[i] = nuage;
			objets.add(nuage);
		} 
		StaticBloc colline0 = new StaticBloc(700, 300, 200, 300, "/image/colline.png");
		objets.add(colline0);
		
		Colline colline1 = new Colline(1400, 300, 150, 300, new Color(50, 165, 20));
		colline1.setPatchColor(new Color(40, 200, 10));
		//objets.add(colline1);
		//this.add(colline1);
	}
}