package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Basket;
import model.BrownBasket;
import model.Chicken;
import model.Eggs;
import model.GoldenEgg;
import model.ItemAddFiveSecond;
import model.ItemAddTenSecond;
import model.ItemIncreaseDoubleEggScore;
import model.ItemsOfGame;
import model.NormalEgg;
import model.PosionEgg;
import model.SilverEgg;
import view.GameOverOnePlayerFrame;
import view.Sound;

public class OnePlayer extends JLabel implements Runnable {
	private JFrame onePlayerFrame;
	private JLabel timeOfGameLabel, scoreOfGameLabel;
	private ArrayList<Chicken> listChickens;
	private ArrayList<ItemsOfGame> listItems;
	private ArrayList<Eggs> listKindsOfEgg;
//	private ArrayList<Eggs> listKindsOfEggToChange;
	private HashMap<Integer, ItemsOfGame> listItemsAreCollected;
	private Basket basket;
	private final int NUM_CHICKEN = 5;
	private final int NUM_KINDSOFEGG = 4;
	private final int NUM_ITEMS = 3;
	private Random rd = new Random();
	private int randomKindOfEgg;
	private int randomChickenLaysEgg;
	private int randomChickenLaysItem;
	private int randomItem;
	private Eggs egg;
	private Chicken chickenLaysEgg;
	private Chicken chickenLaysItem;
	private ItemsOfGame item1;
	private ItemsOfGame item2;
	private ItemsOfGame item3;
	private int item1_Key = 1;
	private int item2_Key = 2;
	private int item3_Key = 3;
	private boolean createdItem1 = false;
	private boolean createdItem2 = false;
	private boolean createdItem3 = false;
	private boolean changeEggScore = false;
	private JLabel lableHoldItem1, lableHoldItem2, lableHoldItem3;
	private int timeOfGame;
	private int timePlayedGame;
	private int timeToIncreaseSpeedDropEgg;
	private int timeToCreateItem;
	private Thread thread;
	private BufferedImage bufferedImage;
	private int scoreOfGame = 0;
	private int timeToIncreaseEggScore;

	// ----------------------------------

	// sound when playing game
	private Sound sound_OnePlayer;
	private final String LINK_SOUNDONEPLAYER = "music/musicgameplaying.wav";
	private boolean soundIsTurnedOn;
	private JButton soundButton;
	private final String IMGSOUNDBUTTON_WHENTURNON = "images/volume.png";
	private final String IMGSOUNDBUTTON_WHENTURNOFF = "images/mute.png";

	// ---------------------------------------
	// background game
	private final String BACKGROUNDWHENPLAYINGGAME = "images/backgroundoneplayer.png";

	public OnePlayer(JFrame onePlayerFrame) {

		this.onePlayerFrame = onePlayerFrame;
		setLayout(null);
		createBackgroundWhenPlayingGame();

		timeOfGame = 120;
		basket = new BrownBasket();
		basket.setX_Basket(500);
		scoreOfGame = 0;
		
		listItemsAreCollected = new HashMap<Integer, ItemsOfGame>();

		listChickens = new ArrayList<Chicken>();
		listChickens.add(new Chicken(50));
		listChickens.add(new Chicken(290));
		listChickens.add(new Chicken(530));
		listChickens.add(new Chicken(770));
		listChickens.add(new Chicken(1010));

		listKindsOfEgg = new ArrayList<Eggs>();
		listKindsOfEgg.add(new GoldenEgg());
		listKindsOfEgg.add(new SilverEgg());
		listKindsOfEgg.add(new NormalEgg());
		listKindsOfEgg.add(new PosionEgg());

		listItems = new ArrayList<ItemsOfGame>();
		listItems.add(new ItemAddFiveSecond());
		listItems.add(new ItemAddTenSecond());
		listItems.add(new ItemIncreaseDoubleEggScore());

		scoreOfGame();
		timeOfGame();
		lableHoldItem();
		createEgg();
		moveBasket();
		handleEventWhenPressItem();

		// sound when playing game
		sound_OnePlayer = new Sound(LINK_SOUNDONEPLAYER);
		sound_OnePlayer.start();
		soundIsTurnedOn = true;
		addMusicButton();

		thread = new Thread(this);
		thread.start();

	}

	private void createBackgroundWhenPlayingGame() {
		BufferedImage image;
		try {
			// create background
			image = ImageIO.read(new File(BACKGROUNDWHENPLAYINGGAME));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(1200, 730, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addMusicButton() {
		soundButton = new JButton(new ImageIcon(IMGSOUNDBUTTON_WHENTURNON));
		soundButton.setContentAreaFilled(false);
		soundButton.setBounds(1155, 620, 30, 30);
		soundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundIsTurnedOn) {
					sound_OnePlayer.suspend();
					soundIsTurnedOn = false;
					soundButton.setIcon(new ImageIcon(IMGSOUNDBUTTON_WHENTURNOFF));
				} else {
					sound_OnePlayer.resume();
					soundIsTurnedOn = true;
					soundButton.setIcon(new ImageIcon(IMGSOUNDBUTTON_WHENTURNON));
				}

			}
		});
		add(soundButton);
	}

	//tạo quả trứng , random trứng với gà đẻ trứng
	public void createEgg() {
		randomKindOfEgg = rd.nextInt(NUM_KINDSOFEGG);
		randomChickenLaysEgg = rd.nextInt(NUM_CHICKEN);
		chickenLaysEgg = listChickens.get(randomChickenLaysEgg);
		egg = listKindsOfEgg.get(randomKindOfEgg);
		egg.setX_Egg(chickenLaysEgg.getX_Chicken());
		egg.setY_Egg(120);
	}

	//Làm quả trứng rơi
	public void handleDropEgg() {
		egg.drop();
		repaintChickenWhenLaysEgg();
		//nếu quả trúng rơi ra khỏi màn hình thì tạo quả trứng mới
		if (egg.getY_Egg() > 700) {
			createEgg();
		}
		
		//khi giỏ hứng được trứng cũng tạo quả trứng mới
		if (basketHaveEgg()) {
			createEgg();
		}
	}

	public void createItem1() {
		randomItem = rd.nextInt(NUM_ITEMS);
		randomChickenLaysItem = rd.nextInt(NUM_CHICKEN);
		if (randomChickenLaysEgg == (NUM_CHICKEN - 1) && randomChickenLaysItem == randomChickenLaysEgg) {
			randomChickenLaysItem = randomChickenLaysItem - 1;
		}
		if (randomChickenLaysItem == randomChickenLaysEgg)
			randomChickenLaysItem = randomChickenLaysItem + 1;
		chickenLaysItem = listChickens.get(randomChickenLaysItem);
		item1 = listItems.get(randomItem);
		item1.setX_Item(chickenLaysItem.getX_Chicken());
		item1.setY_Item(120);
	}

	public void createItem2() {
		randomItem = rd.nextInt(NUM_ITEMS);
		randomChickenLaysItem = rd.nextInt(NUM_CHICKEN);
		if (randomChickenLaysEgg == (NUM_CHICKEN - 1) && randomChickenLaysItem == randomChickenLaysEgg) {
			randomChickenLaysItem = randomChickenLaysItem - 1;
		}
		if (randomChickenLaysItem == randomChickenLaysEgg)
			randomChickenLaysItem = randomChickenLaysItem + 1;
		chickenLaysItem = listChickens.get(randomChickenLaysItem);
		item2 = listItems.get(randomItem);
		item2.setX_Item(chickenLaysItem.getX_Chicken());
		item2.setY_Item(120);
	}

	public void createItem3() {
		randomItem = rd.nextInt(NUM_ITEMS);
		randomChickenLaysItem = rd.nextInt(NUM_CHICKEN);
		if (randomChickenLaysEgg == (NUM_CHICKEN - 1) && randomChickenLaysItem == randomChickenLaysEgg) {
			randomChickenLaysItem = randomChickenLaysItem - 1;
		}
		if (randomChickenLaysItem == randomChickenLaysEgg)
			randomChickenLaysItem = randomChickenLaysItem + 1;
		chickenLaysItem = listChickens.get(randomChickenLaysItem);
		item3 = listItems.get(randomItem);
		item3.setX_Item(chickenLaysItem.getX_Chicken());
		item3.setY_Item(120);
	}

	public void createItemAfterXSecond() {
		if (timeToCreateItem == 20) {
			if (!listItemsAreCollected.containsKey(item1_Key)) {
				createItem1();
				createdItem1 = true;
			}
			if (listItemsAreCollected.containsKey(item1_Key) && !listItemsAreCollected.containsKey(item2_Key)) {
				createItem2();
				createdItem2 = true;
			}
			if (listItemsAreCollected.containsKey(item1_Key) && listItemsAreCollected.containsKey(item2_Key)
					&& !listItemsAreCollected.containsKey(item3_Key)) {
				createItem3();
				createdItem3 = true;
			}
			timeToCreateItem = 0;
		}
	}

	public void increaseEggScoreInXSecond() {

		timeToIncreaseEggScore++;
		if (timeToIncreaseEggScore == 10) {
			for (int i = 0; i < listKindsOfEgg.size(); i++) {
				listKindsOfEgg.get(i).setScore_Egg(listKindsOfEgg.get(i).getScore_Egg() / 2);
			}
			timeToIncreaseEggScore = 0;
			changeEggScore = false;
		}
	}

	public void handleDropItem() {
		if (createdItem1 == true) {
			item1.drop();
			if (item1.getY_Item() > 700 || basketHaveItem(item1))
				createdItem1 = false;
		}
		if (createdItem2 == true) {
			item2.drop();
			if (item2.getY_Item() > 700 || basketHaveItem(item2))
				createdItem2 = false;
		}
		if (createdItem3 == true) {
			item3.drop();
			if (item3.getY_Item() > 700 || basketHaveItem(item3))
				createdItem3 = false;
		}
	}

	public void handleEventWhenPressItem() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_1) {
					if (listItemsAreCollected.containsKey(item1_Key)) {
						timeOfGame = item1.addTime(timeOfGame);
						listKindsOfEgg = item1.increaseEggScore(listKindsOfEgg);
						if (item1.isIncreaseEggScore())
							changeEggScore = true;
						lableHoldItem1.setIcon(null);
						increaseEggScoreInXSecond();
						listItemsAreCollected.remove(item1_Key);
					}
				}
				if (ke.getKeyCode() == KeyEvent.VK_2) {
					if (listItemsAreCollected.containsKey(item2_Key)) {
						timeOfGame = item2.addTime(timeOfGame);
						listKindsOfEgg = item2.increaseEggScore(listKindsOfEgg);
						if (item2.isIncreaseEggScore())
							changeEggScore = true;
						lableHoldItem2.setIcon(null);
						increaseEggScoreInXSecond();
						listItemsAreCollected.remove(item2_Key);
					}
				}

				if (ke.getKeyCode() == KeyEvent.VK_3) {
					if (listItemsAreCollected.containsKey(item3_Key)) {
						timeOfGame = item3.addTime(timeOfGame);
						listKindsOfEgg = item3.increaseEggScore(listKindsOfEgg);
						if (item3.isIncreaseEggScore())
							changeEggScore = true;
						lableHoldItem3.setIcon(null);
						increaseEggScoreInXSecond();
						listItemsAreCollected.remove(item3_Key);
					}
				}
			}
		});
	}

	private void moveBasket() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					basket.moveLeft();
					repaint(); // redraw at new position
				}
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					basket.moveRight();
					repaint();
				}
			}
		});
	}

	public boolean basketHaveEgg() {
		Rectangle basketHaveEggRect = new Rectangle(basket.getX_Basket(), basket.getY_Basket(), 80, 50);
		Rectangle eggRect = new Rectangle(egg.getX_Egg(), egg.getY_Egg(), 10, 10);
		if (eggRect.intersects(basketHaveEggRect)) {
			updateScore(egg.getScore_Egg());
			return true;
		}
		return false;
	}

	public boolean basketHaveItem(ItemsOfGame itemX) {
		Rectangle basketHaveItemRect = new Rectangle(basket.getX_Basket(), basket.getY_Basket(), 80, 50);
		Rectangle itemRect = new Rectangle(itemX.getX_Item(), itemX.getY_Item(), 10, 10);
		if (itemRect.intersects(basketHaveItemRect)) {
			if (itemX == item1) {
				lableHoldItem1.setIcon(item1.getImgIcon_Item());
				listItemsAreCollected.put(item1_Key, itemX);
			}
			if (itemX == item2) {
				lableHoldItem2.setIcon(item2.getImgIcon_Item());
				listItemsAreCollected.put(item2_Key, itemX);
			}
			if (itemX == item3) {
				lableHoldItem3.setIcon(item3.getImgIcon_Item());
				listItemsAreCollected.put(item3_Key, itemX);
			}
			return true;
		}
		return false;
	}

	public void lableHoldItem() {
		lableHoldItem1 = new JLabel();
		lableHoldItem2 = new JLabel();
		lableHoldItem3 = new JLabel();
		lableHoldItem1.setBounds(400, 620, 100, 50);
		lableHoldItem2.setBounds(600, 620, 100, 50);
		lableHoldItem3.setBounds(800, 620, 100, 50);
		add(lableHoldItem1);
		add(lableHoldItem2);
		add(lableHoldItem3);
	}

	public void scoreOfGame() {
		scoreOfGameLabel = new JLabel();
		scoreOfGameLabel.setBounds(100, 620, 150, 50);
		scoreOfGameLabel.setForeground(Color.WHITE);
		scoreOfGameLabel.setText("Score : " + scoreOfGame);
		add(scoreOfGameLabel);
	}

	public void updateScore(int score) {
		scoreOfGame = scoreOfGame + score;
		scoreOfGameLabel.setText("Score : " + scoreOfGame);
	}

	public int getScoreOfGame() {
		return scoreOfGame;
	}

	public void timeOfGame() {
		timeOfGameLabel = new JLabel();
		timeOfGameLabel.setBounds(10, 620, 150, 50); // setting the time label on screen
		timeOfGameLabel.setForeground(Color.WHITE);
		timeOfGameLabel.setText("Time : " + timeOfGame);
		add(timeOfGameLabel);
		timePlayedGame();
	}

	public void timePlayedGame() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				timeToCreateItem++;
				createItemAfterXSecond();
				if (changeEggScore == true) {
					increaseEggScoreInXSecond();
				}
				timeToIncreaseSpeedDropEgg++;
				increaseSpeedDropEgg();
				timePlayedGame++;
				timeOfGameLabel.setText("Time : " + (timeOfGame - timePlayedGame));
				if (gameOver()) {
					time.cancel();
					if (soundIsTurnedOn)
						sound_OnePlayer.suspend();
					onePlayerFrame.setVisible(false);
					new GameOverOnePlayerFrame(returnThis());
				}
			}
		}, 0, 1000);
	}

	@Override
	public void run() {
		try {
			while (!gameOver()) {
				Thread.sleep(10);
				handleDropEgg();
				handleDropItem();
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean gameOver() {
		return timePlayedGame == timeOfGame;
	}

	public void repaintChickenWhenLaysEgg() {
		if (120 < egg.getY_Egg() && egg.getY_Egg() < 130) {
			chickenLaysEgg.afterHaveEgg();
		}
		if (egg.getY_Egg() > 160) {
			chickenLaysEgg.beforeHaveEgg();
		}
	}

	public void increaseSpeedDropEgg() {
		if (timeToIncreaseSpeedDropEgg == 20) {
			for (int i = 0; i < listKindsOfEgg.size(); i++) {
				listKindsOfEgg.get(i).increaseSpeedDropEgg();
			}
			timeToIncreaseSpeedDropEgg = 0;
		}

	}

	public OnePlayer returnThis() {
		return this;
	}

	public void drawGameOfBuffered() {
		bufferedImage = new BufferedImage(1200, 730, BufferedImage.TRANSLUCENT);
		Graphics2D g2d2 = (Graphics2D) bufferedImage.createGraphics();
		g2d2.drawImage(basket.getImg_Basket(), basket.getX_Basket(), basket.getY_Basket(), null);
		for (int i = 0; i < listChickens.size(); i++) {
			g2d2.drawImage(listChickens.get(i).getImg_Chicken(), listChickens.get(i).getX_Chicken(),
					listChickens.get(i).getY_Chicken(), null);
		}
		g2d2.drawImage(egg.getImg_Egg(), egg.getX_Egg(), egg.getY_Egg(), null);
		if (createdItem1 == true) {
			g2d2.drawImage(item1.getImgIcon_Item().getImage(), item1.getX_Item(), item1.getY_Item(), null);
		}
		if (createdItem2 == true) {
			g2d2.drawImage(item2.getImgIcon_Item().getImage(), item2.getX_Item(), item2.getY_Item(), null);
		}
		if (createdItem3 == true) {
			g2d2.drawImage(item3.getImgIcon_Item().getImage(), item3.getX_Item(), item3.getY_Item(), null);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		grabFocus();
		if (!gameOver()) {
			drawGameOfBuffered();
			g.drawImage(bufferedImage, 0, 0, null);
		}
	}
}
