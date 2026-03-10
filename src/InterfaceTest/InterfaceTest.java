package InterfaceTest;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Game.GameMenu;
import Objet.Brick;
import Objet.Colline;
import Objet.Drapeau;
import Objet.Platform;
import Objet.StaticBloc;
import Objet.YellowBox;
import UIComponent.AnimatedButton;

public class InterfaceTest {
	
	private JFrame frame;
	private JPanel mainPanel;
	
	public InterfaceTest() {
		frame = new JFrame("Interface test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setRequestFocusEnabled(true);
		//mainPanel.setLayout(null);
		Drapeau flag  = new Drapeau(20, 20, 100, 300);
		Platform p = new Platform(300, 100, 300, 100);
		Colline colline = new Colline(50, 300, 200, 200);
		p.addImage("/image/E.png", 10, 20, 100, 100);
		mainPanel.add(flag);
		mainPanel.add(p);
		mainPanel.add(colline);
		frame.add(mainPanel);
		
		frame.setVisible(true);
	}	
		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new InterfaceTest());
	}
}
