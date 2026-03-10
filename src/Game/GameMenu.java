package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import Objet.Brick;
import Objet.Castle;
import Objet.Objet;
import Objet.Piece;
import Objet.StaticBloc;
import Objet.Tuyau;
import Objet.YellowBox;
import Personnage.Ennemy;
import Personnage.Mario;
import Personnage.Personnage;
import UIComponent.AnimatedBorder;
import UIComponent.AnimatedButton;

public class GameMenu extends GameComponent {
	 protected Image image;
	 protected ImageIcon icon;
	 public ArrayList<AnimatedButton> gameButton = new ArrayList<AnimatedButton>();
	 public Boolean[] clickedButton = new Boolean[9];
	
	 public GameMenu() {
		super();
		setLayout(null);
		setRequestFocusEnabled(true);
		setBackgroundImage("/image/blue_screen.png");
		Arrays.fill(clickedButton, false);
		
		////****PREMIER BOUTON****////
		AnimatedButton b1 = new AnimatedButton(300, 200) {
			@Override
			public void setObjets() {
				//MARIO//
				Mario mario = new Mario(65, 90, 45, 70) {
					@Override
					public void animer() {
						Timer aTimer = new Timer(16, new ActionListener() {
							@Override 
							public void actionPerformed(ActionEvent e) {
								setMarcheAutorisation(true);
								if(animationCompteur<60) {
									setDirectionDroite(true);
									marcher("mario", 3);
									setX(getX()+2);
									animationCompteur ++;
								}
								else if(animationCompteur >= 60 && animationCompteur < 80) {
									setDirectionDroite(true);
									sauter("mario");
									setY(getY()-2);
									animationCompteur ++;
								}
								else if(animationCompteur >= 80 && animationCompteur <100) {
									setDirectionDroite(true);
									sauter("mario");
									setY(getY()+2);
									animationCompteur ++;
								}
								else if(animationCompteur >=100 && animationCompteur < 160) {
									setDirectionDroite(false);
									marcher("mario", 3);
									setX(getX()-2);
									animationCompteur ++;
								}
								else if(animationCompteur >= 160 && animationCompteur < 180) {
									setDirectionDroite(false);
									sauter("mario");
									setY(getY()-2);
									animationCompteur ++;
								}
								else if(animationCompteur >= 180 && animationCompteur <200) {
									setDirectionDroite(false);
									sauter("mario");
									setY(getY()+2);
									animationCompteur ++;
								}
								else {
									animationCompteur = 0;
								}
							}
						});aTimer.start();
					}
				};
				personnage.add(mario);
				//MONTAGNE
				StaticBloc mountain = new StaticBloc(200, 20, 100, 160);
				mountain.setImage("montagne", "png");
				objet.add(mountain);
				//SOL
				for(int i = 0; i<6; i++) {
					Brick brick = new Brick(i*50, 160, 50, 40);
					objet.add(brick);
				}
				//BLOCS
				
				//BLOC 1
				StaticBloc sb = new StaticBloc(40, 10, 40, 40) {
					@Override 
					public void animer() {
						Timer animationTimer = new Timer(20, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// Derivation de X
								if(getX()>=180) {isRight = false;}
								if(getX()<=0) {isRight = true;}
								if(isRight){setX(getX()+3);}
								else {setX(getX()-3);}
								
							}
						});animationTimer.start();
						repaint();
					}
				};
				objet.add(sb);
				//BLOC 2
				YellowBox yb = new YellowBox(80, 10, 40, 40) {
					@Override 
						public void animer() {
					Timer animationTimer = new Timer(20, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//Derivation X
							if(getX()>=220) {isRight = false;}
							if(getX()<=40) {isRight = true;}
							if(isRight){setX(getX()+3);}
							else {setX(getX()-3);}
						}
					});animationTimer.start();
					repaint();
				}
			};
			objet.add(yb);
			StaticBloc sb1 = new StaticBloc(120, 10, 40, 40) {
				@Override 
				public void animer() {
					Timer animationTimer = new Timer(20, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//Derivation X
							if(getX()>=260) {isRight = false;}
							if(getX()<=80) {isRight = true;}
							if(isRight){setX(getX()+3);}
							else {setX(getX()-3);}
						}
					});animationTimer.start();
					repaint();
				}
			};
			objet.add(sb1);
			sb.animer();
			yb.animer();
			sb1.animer();
		
			}
		};

		b1.setBackground("src/image/blue_screen.png");
		b1.setBounds(100, 10, 300, 200);
		b1.setPreferredSize(new Dimension(300, 200));
		b1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickedButton[0] = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				b1.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {b1.setBorderPainted(false);}
		});
		this.add(b1);
		gameButton.add(b1);
		
		////****DEUXIEME BOUTON****////
		AnimatedButton b2 = new AnimatedButton(300, 200) {
			@Override
			public void setObjets() {
				StaticBloc sand = new StaticBloc(0, 0, 300, 200);
				sand.setImage("sand", "png");
				objet.add(sand);
				StaticBloc pyramide = new StaticBloc(100, 50, 100, 80);
				pyramide.setImage("pyramidetriple","png" );
				objet.add(pyramide);
				
				StaticBloc door1 = new StaticBloc(50, 34, 100, 146) {
					@Override
					public void animer() {
						Timer animationTimer = new Timer(40, new ActionListener() {
							final int seuilDroit = 50;
							final int seuilGauche = -50;
							@Override
							public void actionPerformed(ActionEvent e) {
								if(isRight) {
									if(getX()>=seuilDroit) {
										isRight = false;
										setX(getX()-2);
									}
									else {setX(getX()+2);}
								}
								else {
									if(getX()<=seuilGauche) {
										isRight = true;
										setX(getX()+2);
									}
									else {setX(getX()-2);}
								}
							}
						});animationTimer.start();
					}
				};
				door1.setImage("door1", "png");
				door1.isRight = false;
				objet.add(door1);
				door1.animer();
				StaticBloc door2 = new StaticBloc(150, 34, 100, 146) {
					@Override
					public void animer() {
						Timer animationTimer = new Timer(40, new ActionListener() {
							final int seuilDroit = 250;
							final int seuilGauche = 150;
							@Override
							public void actionPerformed(ActionEvent e) {
								if(isRight) {
									if(getX()>=seuilDroit) {
										isRight = false;
										setX(getX()-2);
									}
									else {setX(getX()+2);}
								}
								else {
									if(getX()<=seuilGauche) {
										isRight = true;
										setX(getX()+2);
									}
									else {setX(getX()-2);}
								}
							}
						});animationTimer.start();
					}
				};
				door2.setImage("door2", "png");
				door2.isRight = true;
				objet.add(door2);
				door2.animer();
				StaticBloc papyrus1 = new StaticBloc(0, 0, 80, 200);
				papyrus1.setImage("papyrus1", "png");
				
				StaticBloc papyrus = new StaticBloc(220, 0, 80, 200);
				papyrus.setImage("papyrus", "png");
				
				StaticBloc portail = new StaticBloc(25, 0, 250, 200);
				portail.setImage("arche0", "png");
				
				objet.add(papyrus1);
				objet.add(papyrus);
				objet.add(portail);
			}
		};
		b2.setBackground("src/image/blue_screen.png");
		b2.setBounds(500, 10, 300, 200);
		b2.setPreferredSize(new Dimension(300, 200));
		b2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {clickedButton[1] = true;}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				b2.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {b2.setBorderPainted(false);}
		});

		this.add(b2);
		gameButton.add(b2);
		
		////****TROISIEME BOUTON****////
		AnimatedButton b3 = new AnimatedButton(300, 200) {
			@Override
			public void setObjets() {
				StaticBloc soucoupe = new StaticBloc(20, 80, 100, 60) {
					@Override
					public void animer() {
						Timer animationTimer = new Timer(20, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								isRight = true;
								//Selon X
								if(getX()>=350) {setX(-100);}
								else {setX(getX()+2);}
								
							}
						});animationTimer.start();
					}
				};
				soucoupe.setImage("starship", "png");
				soucoupe.animer();
				objet.add(soucoupe);
				StaticBloc meteorite = new StaticBloc(-110, 80, 60, 60) {
					@Override
					public void animer() {
						Timer animationTimer = new Timer(20, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								isRight = true;
								//Selon X
								if(getX()>=350) {setX(-100);}
								else {setX(getX()+2);}
								
							}
						});animationTimer.start();
					}
				};
				meteorite.setImage("moon", "png");
				objet.add(meteorite);
				meteorite.animer();
				Ennemy goomba = new Ennemy(-100, 45, 40, 40, "goomba") {
					public int animationCompteur = 0;
					@Override
					public void setTimerMarche() {
						Timer animationTimer = new Timer(20, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								Image standIMG = (new ImageIcon(getClass().getResource("/image/goomba_stand_right.png"))).getImage();
								Image angryIMG = (new ImageIcon(getClass().getResource("/image/angry_goomba_right.png"))).getImage();
								//Selon X
								if(animationCompteur < 10) {setImage(standIMG);}
								else if(animationCompteur >= 10 && animationCompteur <20) {setImage(angryIMG);}
								else {animationCompteur = 0;}
								
								if(getX()>=350) {setX(-100);}
								else {setX(getX()+2);}
								animationCompteur ++;
								
							}
						});animationTimer.start();
					}
				};
				goomba.animer();
				personnage.add(goomba);
				Mario mario = new Mario(52, 40, 50, 70) {
					@Override
					public void animer() {
						Timer animationTimer = new Timer(20, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								//Selon X
								isDroite = true;
								if(getX()>=400) {setX(-50);}
								else {setX(getX()+2);}
								
							}
						});animationTimer.start();
					}
				};
				mario.setImage("mario_scared_right_1", "png");
				personnage.add(mario);
				//setBackground("src/image/espace.png");
			}
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D)g;
				Image img = (new ImageIcon(getClass().getResource("/image/espace1.png"))).getImage();
				g2d.drawImage(img,  0,  0,  width,  height,  this);
				
				if(personnage.size()>0) {
					for(Personnage p : personnage) {
						g2d.drawImage(p.getImage(), p.getX(), p.getY(), p.getLargeur(), p.getHauteur(), this);
					}
				}
				for (Objet objet : objet) {
				    if (objet.getImage() != null) {
				    	ImageIcon icon = new ImageIcon("src"+objet.imagePath);
				    	Image image0 = icon.getImage();
				    	//g2d.rotate(1f);
				        g2d.drawImage(image0, objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur(), this);
				    } else {
				        // Si l'image est null, tu peux dessiner un rectangle de test pour vérifier que l'objet est bien là
				        g2d.setColor(Color.BLUE);  // Choisis une couleur de test
				        g2d.fillRect(objet.getX(), objet.getY(), objet.getLargeur(), objet.getHauteur());
				    }
				}
			}
		}; 
		b3.setBounds(900, 10, 300, 200);
		b3.setPreferredSize(new Dimension(300, 200));
		b3.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {clickedButton[2] = true;}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {b3.setBorderPainted(true);}
			@Override
			public void mouseExited(MouseEvent e) {b3.setBorderPainted(false);}
		});
		this.add(b3);
		gameButton.add(b3);
		
		////****QUATRIEME BOUTON****////
		AnimatedButton b4 = new AnimatedButton(300, 200) {
			@Override
			public void setObjets() {
				StaticBloc nuage0 = new StaticBloc(20, 20, 60, 30);
				nuage0.setImage("blackcloud", "png");
				objet.add(nuage0);
				
				StaticBloc nuage1 = new StaticBloc(60, 30, 60, 30);
				nuage1.setImage("violetnuage", "png");
				objet.add(nuage1);
				
				StaticBloc nuage2 = new StaticBloc(200, 10, 90, 60);
				nuage2.setImage("blackcloud", "png");
				objet.add(nuage2);
				
				StaticBloc tree0 = new StaticBloc(0, 60, 120, 140);
				tree0.setImage("tree0", "png");
				objet.add(tree0);
				
				Ennemy boo = new Ennemy(200, 50, 50, 50, "boo_left") {
					public int animationCompteur = 0;
					@Override
					public void setDefaultImage(String nom) {
						if(this.getDirectionDroite() == true) {
							this.icon = new ImageIcon(getClass().getResource("/image/"+nom+".png"));
							this.image = icon.getImage();
							//this.image = (new ImageIcon("src/image/"+nom+"_stand_right.png")).getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
						}
						else {
							this.icon = new ImageIcon(getClass().getResource("/image/"+nom+".png"));
							this.image = icon.getImage();
							//this.image = (new ImageIcon("src/image/"+nom+"_stand_left.png")).getImage().getScaledInstance(largeur, hauteur, Image.SCALE_FAST);
						}
					} 
					@Override
					public void setTimerMarche() {
						Timer timer = new Timer(20, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if(animationCompteur < 40) {setDefaultImage("boo_left");}
								else if(animationCompteur>=40 && animationCompteur<80) {setDefaultImage("hiding_boo");}
								else {animationCompteur = 0;}
								animationCompteur ++;
							}
						});timer.start();
					}
					
				};
				boo.setImage("boo_left", "png");
				personnage.add(boo);
			}
		}; 
		b4.setBackground("/image/violetbackground.png");
		b4.setBounds(100, 220, 300, 200);
		b4.setPreferredSize(new Dimension(300, 200));
		b4.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {clickedButton[2] = true;}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {b3.setBorderPainted(true);}
			@Override
			public void mouseExited(MouseEvent e) {b3.setBorderPainted(false);}
		});

		this.add(b4);
		gameButton.add(b4);
		
		////****CINQUIEME BOUTON****////
		AnimatedButton b5 = new AnimatedButton(300, 200); 
		b5.setBounds(500, 220, 300, 200);
		b5.setPreferredSize(new Dimension(300, 200));
		b5.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {clickedButton[2] = true;}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {b3.setBorderPainted(true);}
			@Override
			public void mouseExited(MouseEvent e) {b3.setBorderPainted(false);}
		});
		this.add(b5);
		gameButton.add(b5);
		
		////****SIXIEME BOUTON****////
		AnimatedButton b6 = new AnimatedButton(300, 200); 
		b6.setBounds(900, 220, 300, 200);
		b6.setPreferredSize(new Dimension(300, 200));
		b6.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {clickedButton[2] = true;}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {b3.setBorderPainted(true);}
			@Override
			public void mouseExited(MouseEvent e) {b3.setBorderPainted(false);}
		});
		this.add(b6);
		gameButton.add(b6);
		
		////****SEPTIEME BOUTON****////
		AnimatedButton b7 = new AnimatedButton(300, 200); 
		b7.setBounds(100, 430, 300, 200);
		b7.setPreferredSize(new Dimension(300, 200));
		this.add(b7);
		gameButton.add(b7);
		
		////****HUITIEME BOUTON****////
		AnimatedButton b8 = new AnimatedButton(300, 200); 
		b8.setBounds(500, 430, 300, 200);
		b8.setPreferredSize(new Dimension(300, 200));
		this.add(b8);
		gameButton.add(b8);
		
		////****NEUVIEME BOUTON****////
		AnimatedButton b9 = new AnimatedButton(300, 200); 
		b9.setBounds(900, 430, 300, 200);
		b9.setPreferredSize(new Dimension(300, 200));
		this.add(b9);
		gameButton.add(b9);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		if(image != null) {
			g2d.drawImage(image, 0, 0, 1300, 800, this);
		}
		//for(AnimatedButton ab : gameButton) {
			//ab.getComponents();
			//g2d.drawImage(ab.getImage(), ab.getX(), ab.getY(), ab.getWidth(), ab.getHeight(), this);
			//for(Objet obj : ab.objet) {
				//g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(), obj.getLargeur(), obj.getHauteur(), this);
			//}
		//}
	}
	
	public void setBackgroundImage(String path) {
		icon = new ImageIcon(getClass().getResource(path));
		image = icon.getImage().getScaledInstance(1300, 800, Image.SCALE_SMOOTH);
	}
	@Deprecated
	public void setAnimatedButtons() {
		gameButton.get(0).setBounds(100, 0, 300, 200);
		gameButton.get(1).setBounds(450, 0, 300, 200);
		gameButton.get(2).setBounds(850, 0, 300, 200);
		gameButton.get(3).setBounds(100, 225, 300, 200);
		gameButton.get(4).setBounds(450, 225, 300, 200);
		gameButton.get(5).setBounds(850, 225, 300, 200);
		gameButton.get(6).setBounds(100, 475, 300, 200);
		gameButton.get(7).setBounds(450, 475, 300, 200);
		gameButton.get(8).setBounds(850, 475, 300, 200);
	}
	public void setDefaultClickedButton() {
		clickedButton = new Boolean[gameButton.size()];
		Arrays.fill(clickedButton, false);
	}
}