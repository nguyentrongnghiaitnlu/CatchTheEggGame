package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TwoPlayersSettingFrame extends JFrame implements ActionListener, MouseListener {

	private final String IMG_BUTTONGOTOLEFTAFTER = "images/gotoleftafter.png";
	private final String IMG_BUTTONGOTORIGHTAFTER = "images/gotorightafter.png";
	private final String IMG_BUTTONGOTOLEFTBEFORE = "images/gotoleftbefore.png";
	private final String IMG_BUTTONGOTORIGHTBEFORE = "images/gotorightbefore.png";
	private final String IMG_BUTTONSTART = "images/start.png";
	private final String IMG_BACKGROUND = "images/background.png";
	private final String IMG_PLAYER1 = "images/player1.png";
	private final String IMG_PLAYER2 = "images/player2.png";
	private final String IMG_REDBASKET = "images/redbasket.png";
	private final String IMG_BROWNBASKET = "images/brownbasket.png";
	private final String IMG_BLUEBASKET = "images/bluebasket.png";
	private final String IMG_GREENBASKET = "images/greenbasket.png";
	private final String IMG_PLUSTIME = "images/plus.png";
	private final String IMG_PLUSTIME2 = "images/plus2.png";
	private final String IMG_IMPLUSTIME = "images/implus.png";
	private final String IMG_IMPLUSTIME2 = "images/implus2.png";

	private JLabel backGround;
	private JLabel labelHoldBasketForPlayer1;
	private JLabel labelHoldBasketForPlayer2;

	private int player1Basket;
	private int player2Basket;
	private int timeOfGame = 120;

	private JTextField timeOfGame_JTF;
	private JTextField namePlayer1;
	private JTextField namePlayer2;

	private JButton startButton;
	private JButton goToLeftButtonOfPlayer1;
	private JButton goToRightButtonOfPlayer1;
	private JButton goToLeftButtonOfPlayer2;
	private JButton goToRightButtonOfPlayer2;
	private JButton plusTime;
	private JButton implusTime;
	private ArrayList<Integer> listObserver;

	private String nameOfPlayer1;
	private String nameOfPlayer2;

	private ArrayList<String> listBasketsForPlayer1;
	private ArrayList<String> listBasketsForPlayer2;

	public TwoPlayersSettingFrame() {

		listBasketsForPlayer1 = new ArrayList<String>();
		listBasketsForPlayer1.add(IMG_BROWNBASKET);
		listBasketsForPlayer1.add(IMG_BLUEBASKET);
		listBasketsForPlayer1.add(IMG_REDBASKET);
		listBasketsForPlayer1.add(IMG_GREENBASKET);

		listBasketsForPlayer2 = new ArrayList<String>();
		listBasketsForPlayer2.add(IMG_BROWNBASKET);
		listBasketsForPlayer2.add(IMG_BLUEBASKET);
		listBasketsForPlayer2.add(IMG_REDBASKET);
		listBasketsForPlayer2.add(IMG_GREENBASKET);

		createBackGround();
		createPlayerLabel();
		enterNamePlayer1();
		enterNamePlayer2();
		createGTLeftButtonOfPlayer1();
		createGTRightButtonOfPlayer1();
		createGTLeftButtonOfPlayer2();
		createGTRightButtonOfPlayer2();
		labelHoldBasket();
		createTimeOfGame();
		createStartButton();

		setTitle("Two Players");
		setSize(800, 730);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void createBackGround() {
		backGround = new JLabel();
		backGround.setBounds(0, 0, 800, 730);
		createBackgroundForLabel(backGround, IMG_BACKGROUND);
		add(backGround);

	}

	public void createPlayerLabel() {
		JLabel player1Label = new JLabel();
		player1Label.setBounds(100, 75, 200, 51);
		createBackgroundForLabel(player1Label, IMG_PLAYER1);
		backGround.add(player1Label);

		JLabel player2Label = new JLabel();
		player2Label.setBounds(500, 75, 200, 51);
		createBackgroundForLabel(player2Label, IMG_PLAYER2);
		backGround.add(player2Label);
	}

	public void enterNamePlayer1() {

		namePlayer1 = new JTextField(new JTextFieldLimit(15), "Name Player 1", 10);
		namePlayer1.setBounds(143, 200, 130, 40);
		backGround.add(namePlayer1);
	}

	public void enterNamePlayer2() {
		namePlayer2 = new JTextField(new JTextFieldLimit(15), "Name Player 2", 10);
		namePlayer2.setBounds(543, 200, 130, 40);
		backGround.add(namePlayer2);
	}

	public void labelHoldBasket() {
		player1Basket = 0;
		labelHoldBasketForPlayer1 = new JLabel();
		labelHoldBasketForPlayer1.setBounds(160, 365, 100, 52);
		createBackgroundForLabel(labelHoldBasketForPlayer1, listBasketsForPlayer1.get(player1Basket));

		backGround.add(labelHoldBasketForPlayer1);

		player2Basket = 0;
		labelHoldBasketForPlayer2 = new JLabel();
		labelHoldBasketForPlayer2.setBounds(560, 365, 100, 52);
		createBackgroundForLabel(labelHoldBasketForPlayer2, listBasketsForPlayer2.get(player2Basket));
		backGround.add(labelHoldBasketForPlayer2);
	}

	public void createGTLeftButtonOfPlayer1() {
		goToLeftButtonOfPlayer1 = new JButton(new ImageIcon(IMG_BUTTONGOTOLEFTBEFORE));
		goToLeftButtonOfPlayer1.setContentAreaFilled(false);
		goToLeftButtonOfPlayer1.setBounds(30, 345, 48, 90);
		goToLeftButtonOfPlayer1.addActionListener(this);
		goToLeftButtonOfPlayer1.addMouseListener(this);
		goToLeftButtonOfPlayer1.setBorderPainted(false);
		backGround.add(goToLeftButtonOfPlayer1);
	}

	public void createGTRightButtonOfPlayer1() {
		goToRightButtonOfPlayer1 = new JButton(new ImageIcon(IMG_BUTTONGOTORIGHTBEFORE));
		goToRightButtonOfPlayer1.setContentAreaFilled(false);
		goToRightButtonOfPlayer1.setBounds(323, 345, 48, 90);
		goToRightButtonOfPlayer1.addActionListener(this);
		goToRightButtonOfPlayer1.addMouseListener(this);
		goToRightButtonOfPlayer1.setBorderPainted(false);
		backGround.add(goToRightButtonOfPlayer1);
	}

	public void createGTLeftButtonOfPlayer2() {
		goToLeftButtonOfPlayer2 = new JButton(new ImageIcon(IMG_BUTTONGOTOLEFTBEFORE));
		goToLeftButtonOfPlayer2.setContentAreaFilled(false);
		goToLeftButtonOfPlayer2.setBounds(430, 345, 48, 90);
		goToLeftButtonOfPlayer2.addActionListener(this);
		goToLeftButtonOfPlayer2.addMouseListener(this);
		goToLeftButtonOfPlayer2.setBorderPainted(false);
		backGround.add(goToLeftButtonOfPlayer2);
	}

	public void createGTRightButtonOfPlayer2() {
		goToRightButtonOfPlayer2 = new JButton(new ImageIcon(IMG_BUTTONGOTORIGHTBEFORE));
		goToRightButtonOfPlayer2.setContentAreaFilled(false);
		goToRightButtonOfPlayer2.setBounds(723, 345, 48, 90);
		goToRightButtonOfPlayer2.addActionListener(this);
		goToRightButtonOfPlayer2.addMouseListener(this);
		goToRightButtonOfPlayer2.setBorderPainted(false);
		backGround.add(goToRightButtonOfPlayer2);
	}

	public void createTimeOfGame() {
		JLabel labelTimeOfGame = new JLabel("Set Time");
		labelTimeOfGame.setForeground(Color.WHITE);
		labelTimeOfGame.setBounds(370, 490, 100, 30);
		backGround.add(labelTimeOfGame);
//		timeOfGame = new Integer(120);
		
		timeOfGame_JTF = new JTextField(timeOfGame+"");
		timeOfGame_JTF.setBounds(350, 520, 100, 30);
		backGround.add(timeOfGame_JTF);
		
		
		plusTime = new JButton(new ImageIcon(IMG_PLUSTIME));
//		plusTime.setContentAreaFilled(false);
		plusTime.setContentAreaFilled(false);
		plusTime.addMouseListener(this);
		plusTime.setBorderPainted(false);
		plusTime.setBounds(280, 508, 60, 60);
//		listObserver.add(timeOfGame);
		backGround.add(plusTime);
		
		implusTime = new JButton(new ImageIcon(IMG_IMPLUSTIME));
//		plusTime.setContentAreaFilled(false);
		implusTime.setContentAreaFilled(false);
		implusTime.addMouseListener(this);
		implusTime.setBorderPainted(false);
		implusTime.setBounds(460, 508, 60, 60);
		backGround.add(implusTime);
		
		
		
//		labelTimeOfGame.setBounds(x, y, width, height);

	}

	public void createStartButton() {
		startButton = new JButton(new ImageIcon(IMG_BUTTONSTART));
		startButton.setContentAreaFilled(false);
		startButton.setBounds(300, 600, 200, 51);
		startButton.addActionListener(this);
		goToRightButtonOfPlayer2.setBorderPainted(false);
		backGround.add(startButton);
	}

	private void createBackgroundForLabel(JLabel lable, String imgOfLable) {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(imgOfLable));
			ImageIcon icon = new ImageIcon(
					image.getScaledInstance(lable.getWidth(), lable.getHeight(), image.SCALE_SMOOTH));
			lable.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == goToLeftButtonOfPlayer1) {
			if (player1Basket == 0)
				player1Basket = 3;
			else
				player1Basket--;
			createBackgroundForLabel(labelHoldBasketForPlayer1, listBasketsForPlayer1.get(player1Basket));
		}
		if (button == goToRightButtonOfPlayer1) {
			if (player1Basket == 3)
				player1Basket = 0;
			else
				player1Basket++;
			createBackgroundForLabel(labelHoldBasketForPlayer1, listBasketsForPlayer1.get(player1Basket));
		}
		if (button == goToLeftButtonOfPlayer2) {
			if (player2Basket == 0)
				player2Basket = 3;
			else
				player2Basket--;
			createBackgroundForLabel(labelHoldBasketForPlayer2, listBasketsForPlayer2.get(player2Basket));
		}
		if (button == goToRightButtonOfPlayer2) {
			if (player2Basket == 3)
				player2Basket = 0;
			else
				player2Basket++;
			createBackgroundForLabel(labelHoldBasketForPlayer2, listBasketsForPlayer2.get(player2Basket));
		}

		if (button == startButton) {
			nameOfPlayer1 = namePlayer1.getText();
			nameOfPlayer2 = namePlayer2.getText();
			if (nameOfPlayer1.equalsIgnoreCase("Name Player 1") || nameOfPlayer2.equalsIgnoreCase("Name Player 2")
					|| timeOfGame_JTF.getText().equalsIgnoreCase("Time...")) {
				JOptionPane.showMessageDialog(null, "Please enter your name and set time to play !!!");
			} else {
				timeOfGame = Integer.parseInt(timeOfGame_JTF.getText());
				this.setVisible(false);
				new TwoPlayersFrame(this);
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == goToLeftButtonOfPlayer1) {
			goToLeftButtonOfPlayer1.setIcon(new ImageIcon(IMG_BUTTONGOTOLEFTBEFORE));
		}

		if (button == goToRightButtonOfPlayer1) {
			goToRightButtonOfPlayer1.setIcon(new ImageIcon(IMG_BUTTONGOTORIGHTBEFORE));
		}

		if (button == goToLeftButtonOfPlayer2) {
			goToLeftButtonOfPlayer2.setIcon(new ImageIcon(IMG_BUTTONGOTOLEFTBEFORE));
		}

		if (button == goToRightButtonOfPlayer2) {
			goToRightButtonOfPlayer2.setIcon(new ImageIcon(IMG_BUTTONGOTORIGHTBEFORE));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == goToLeftButtonOfPlayer1) {
			goToLeftButtonOfPlayer1.setIcon(new ImageIcon(IMG_BUTTONGOTOLEFTAFTER));
		}

		if (button == goToRightButtonOfPlayer1) {
			goToRightButtonOfPlayer1.setIcon(new ImageIcon(IMG_BUTTONGOTORIGHTAFTER));
		}

		if (button == goToLeftButtonOfPlayer2) {
			goToLeftButtonOfPlayer2.setIcon(new ImageIcon(IMG_BUTTONGOTOLEFTAFTER));
		}

		if (button == goToRightButtonOfPlayer2) {
			goToRightButtonOfPlayer2.setIcon(new ImageIcon(IMG_BUTTONGOTORIGHTAFTER));
		}
		if (button == plusTime) {
			timeOfGame=timeOfGame+10;
			timeOfGame_JTF.setText(timeOfGame+"");
		}
		if (button == implusTime) {
			timeOfGame=timeOfGame-10;
			timeOfGame_JTF.setText(timeOfGame+"");
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == plusTime) {
			plusTime.setIcon(new ImageIcon(IMG_PLUSTIME2));
		}
		
		if (button == implusTime) {
			
			implusTime.setIcon(new ImageIcon(IMG_IMPLUSTIME2));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == plusTime) {
			plusTime.setIcon(new ImageIcon(IMG_PLUSTIME));
		}
		
		if (button == implusTime) {
			implusTime.setIcon(new ImageIcon(IMG_IMPLUSTIME));
		}

	}

	public int getTimeOfGame() {
		return timeOfGame;
	}

	public String getNameOfPlayer1() {
		return nameOfPlayer1;
	}

	public String getNameOfPlayer2() {
		return nameOfPlayer2;
	}

	public int getPlayer1Basket() {
		return player1Basket;
	}

	public int getPlayer2Basket() {
		return player2Basket;
	}

}
