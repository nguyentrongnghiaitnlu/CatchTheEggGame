package controller;

import java.awt.Color;
import java.awt.Font;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Basket;
import model.BasketFactory;
import model.Chicken;
import model.Eggs;
import model.GoldenEgg;
import model.ItemAddFiveSecond;
import model.ItemAddTenSecond;
import model.ItemGetTenScore;
import model.ItemGetTwentyScore;
import model.ItemIncreaseDoubleEggScore;
import model.ItemResumeCompetitorGame;
import model.ItemsOfGame;
import model.NormalEgg;
import model.PosionEgg;
import model.SilverEgg;
import view.GameOverTwoPlayerFrame;
import view.Sound;
import view.TwoPlayersFrame;
import view.TwoPlayersSettingFrame;

public class TwoPlayers extends JLabel implements Runnable {
	private final int NUM_KINDSOFEGGPLAYER1 = 4;
	private int timeOfGamePlayer1;
	private JLabel timeOfGameLabelOfPlayer1, scoreOfGameLabelOfPlayer1;
	private ArrayList<Chicken> listChickensOfPlayer1;
	private ArrayList<ItemsOfGame> listItemsOfPlayer1;
	private ArrayList<Eggs> listKindsOfEggPlayer1;
	private ArrayList<Eggs> listKindsOfEggToChange;
	private Basket basketOfPlayer1;
	private final int NUM_CHICKENOFPLAYER1 = 3;
	private final int NUM_ITEMSOFPLAYER1 = 6;
	private Random rd = new Random();
	private int randomKindOfEggForPlayer1;
	private int randomChickenLaysEggForPlayer1;
	private int randomChickenLaysItemForPlayer1;
	private int randomItemForPlayer1;
	private Eggs egg_Player1;
	private Chicken chickenLaysEggForPlayer1;
	private Chicken chickenLaysItemForPlayer1;
	private ItemsOfGame itemPlayer1;
	private boolean createdItemForPlayer1 = false;
	private int timePlayedGamePlayer1;
	private int timeToIncreaseSpeedDropEggPlayer1;
	private int timeToCreateItemForPlayer1;
	private Thread thread;
	private BufferedImage bufferedImage;
	private int scoreGameOfPlayer1 = 0;
	private int timeToIncreasePlayer1EggScore;
	private boolean changePlayer1EggScore;
	private boolean player1IsResumed;
	private int timePlayer1Resumed;

	// ----------------------------------

	// sound when playing game
	private Sound sound;
	private final String LINK_SOUND = "music/musicgameplaying.wav";
	private boolean soundIsTurnedOn;
	private JButton soundButton;
	private final String IMGSOUNDBUTTON_WHENTURNON = "images/volume.png";
	private final String IMGSOUNDBUTTON_WHENTURNOFF = "images/mute.png";

	// ---------------------------------------
	// background game
	private final String BACKGROUNDWHENPLAYINGGAME = "images/backgroundtwoplayer.png";

	// ----------------------------------------------
	private final int NUM_KINDSOFEGGPLAYER2 = 4;
	private ArrayList<Eggs> listKindsOfEggPlayer2;
	private JLabel timeOfGameLabelOfPlayer2, scoreOfGameLabelOfPlayer2;
	private ArrayList<Chicken> listChickensOfPlayer2;
	private ArrayList<ItemsOfGame> listItemsOfPlayer2;
	private Basket basketOfPlayer2;
	private final int NUM_CHICKENOFPLAYER2 = 3;
	private final int NUM_ITEMSOFPLAYER2 = 6;
	private int randomKindOfEggForPlayer2;
	private int randomChickenLaysEggForPlayer2;
	private int randomChickenLaysItemForPlayer2;
	private int randomItemForPlayer2;
	private Eggs egg_Player2;
	private Chicken chickenLaysEggForPlayer2;
	private Chicken chickenLaysItemForPlayer2;
	private ItemsOfGame itemPlayer2;
	private boolean createdItemForPlayer2 = false;
	private int timeOfGamePlayer2;
	private int timePlayedGamePlayer2;
	private int timeToIncreaseSpeedDropEggPlayer2;
	private int timeToCreateItemForPlayer2;
	private int scoreGameOfPlayer2 = 0;
	private int timeToIncreasePlayer2EggScore;
	private boolean changePlayer2EggScore;
	private boolean player2IsResumed;
	private int timePlayer2Resumed;

	private TwoPlayersSettingFrame twoPlayersSetting;
	private TwoPlayersFrame twoPlayersFrame;
	private BasketFactory basketFactory;

	public TwoPlayers(TwoPlayersSettingFrame twoPlayersSetting, TwoPlayersFrame twoPlayersFrame) {
		this.twoPlayersFrame = twoPlayersFrame;
		this.twoPlayersSetting = twoPlayersSetting;
		setLayout(null);
		createBackgroundWhenPlayingGame();
		basketFactory = new BasketFactory();

		timeOfGamePlayer1 = twoPlayersSetting.getTimeOfGame();
		createBasketForPlayer1();
		scoreGameOfPlayer1 = 0;

		listChickensOfPlayer1 = new ArrayList<Chicken>();
		listChickensOfPlayer1.add(new Chicken(50));
		listChickensOfPlayer1.add(new Chicken(250));
		listChickensOfPlayer1.add(new Chicken(450));

		listKindsOfEggPlayer1 = new ArrayList<Eggs>();
		listKindsOfEggPlayer1.add(new GoldenEgg());
		listKindsOfEggPlayer1.add(new SilverEgg());
		listKindsOfEggPlayer1.add(new NormalEgg());
		listKindsOfEggPlayer1.add(new PosionEgg());

		listItemsOfPlayer1 = new ArrayList<ItemsOfGame>();
		listItemsOfPlayer1.add(new ItemAddFiveSecond());
		listItemsOfPlayer1.add(new ItemAddTenSecond());
		listItemsOfPlayer1.add(new ItemIncreaseDoubleEggScore());
		listItemsOfPlayer1.add(new ItemGetTenScore());
		listItemsOfPlayer1.add(new ItemGetTwentyScore());
		listItemsOfPlayer1.add(new ItemResumeCompetitorGame());

		timeOfGamePlayer2 = twoPlayersSetting.getTimeOfGame();
		createBasketForPlayer2();
		scoreGameOfPlayer2 = 0;

		listKindsOfEggPlayer2 = new ArrayList<Eggs>();
		listKindsOfEggPlayer2.add(new GoldenEgg());
		listKindsOfEggPlayer2.add(new SilverEgg());
		listKindsOfEggPlayer2.add(new NormalEgg());
		listKindsOfEggPlayer2.add(new PosionEgg());

		listChickensOfPlayer2 = new ArrayList<Chicken>();
		listChickensOfPlayer2.add(new Chicken(650));
		listChickensOfPlayer2.add(new Chicken(850));
		listChickensOfPlayer2.add(new Chicken(1050));

		listItemsOfPlayer2 = new ArrayList<ItemsOfGame>();
		listItemsOfPlayer2.add(new ItemAddFiveSecond());
		listItemsOfPlayer2.add(new ItemAddTenSecond());
		listItemsOfPlayer2.add(new ItemIncreaseDoubleEggScore());
		listItemsOfPlayer2.add(new ItemGetTenScore());
		listItemsOfPlayer2.add(new ItemGetTwentyScore());
		listItemsOfPlayer2.add(new ItemResumeCompetitorGame());

		scoreGameOfPlayer1();
		timeOfGamePlayer1();
		createEggForPlayer1();

		scoreGameOfPlayer2();
		timeOfGamePlayer2();
		createEggForPlayer2();

		moveBasket();

		// sound when playing game
		sound = new Sound(LINK_SOUND);
		sound.start();
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
					sound.suspend();
					soundIsTurnedOn = false;
					soundButton.setIcon(new ImageIcon(IMGSOUNDBUTTON_WHENTURNOFF));
				} else {
					sound.resume();
					soundIsTurnedOn = true;
					soundButton.setIcon(new ImageIcon(IMGSOUNDBUTTON_WHENTURNON));
				}

			}
		});
		add(soundButton);
	}

	public void createBasketForPlayer1() {
		if (twoPlayersSetting.getPlayer1Basket() == 0)
			basketOfPlayer1 = basketFactory.getBasket("brownbasket");
		else if (twoPlayersSetting.getPlayer1Basket() == 1)
			basketOfPlayer1 = basketFactory.getBasket("bluebasket");
		else if (twoPlayersSetting.getPlayer1Basket() == 2)
			basketOfPlayer1 = basketFactory.getBasket("redbasket");
		else if (twoPlayersSetting.getPlayer1Basket() == 3)
			basketOfPlayer1 = basketFactory.getBasket("greenbasket");
		basketOfPlayer1.setX_Basket(200);
	}

	public void createBasketForPlayer2() {
		if (twoPlayersSetting.getPlayer2Basket() == 0)
			basketOfPlayer2 = basketFactory.getBasket("brownbasket");
		else if (twoPlayersSetting.getPlayer2Basket() == 1)
			basketOfPlayer2 = basketFactory.getBasket("bluebasket");
		else if (twoPlayersSetting.getPlayer2Basket() == 2)
			basketOfPlayer2 = basketFactory.getBasket("redbasket");
		else if (twoPlayersSetting.getPlayer2Basket() == 3)
			basketOfPlayer2 = basketFactory.getBasket("greenbasket");
		basketOfPlayer2.setX_Basket(700);
	}

	public void createEggForPlayer1() {
		randomKindOfEggForPlayer1 = rd.nextInt(NUM_KINDSOFEGGPLAYER1);
		randomChickenLaysEggForPlayer1 = rd.nextInt(NUM_CHICKENOFPLAYER1);
		chickenLaysEggForPlayer1 = listChickensOfPlayer1.get(randomChickenLaysEggForPlayer1);
		egg_Player1 = listKindsOfEggPlayer1.get(randomKindOfEggForPlayer1);
		egg_Player1.setX_Egg(chickenLaysEggForPlayer1.getX_Chicken());
		egg_Player1.setY_Egg(120);
	}

	public void createEggForPlayer2() {
		randomKindOfEggForPlayer2 = rd.nextInt(NUM_KINDSOFEGGPLAYER2);
		randomChickenLaysEggForPlayer2 = rd.nextInt(NUM_CHICKENOFPLAYER2);
		chickenLaysEggForPlayer2 = listChickensOfPlayer2.get(randomChickenLaysEggForPlayer2);
		egg_Player2 = listKindsOfEggPlayer2.get(randomKindOfEggForPlayer2);
		egg_Player2.setX_Egg(chickenLaysEggForPlayer2.getX_Chicken());
		egg_Player2.setY_Egg(120);
	}

	public void handleDropEggForPlayer1() {
		egg_Player1.drop();
		repaintChickenWhenLaysEgg();
		if (egg_Player1.getY_Egg() > 700) {
			createEggForPlayer1();
		}
		if (basketPlayer1HaveEgg()) {
			createEggForPlayer1();
		}
	}

	public void handleDropEggForPlayer2() {
		egg_Player2.drop();
		repaintChickenWhenLaysEgg();
		if (egg_Player2.getY_Egg() > 700) {
			createEggForPlayer2();
		}
		if (basketPlayer2HaveEgg()) {
			createEggForPlayer2();
		}
	}

	public void createItemForPlayer1() {
		randomItemForPlayer1 = rd.nextInt(NUM_ITEMSOFPLAYER1);
		randomChickenLaysItemForPlayer1 = rd.nextInt(NUM_CHICKENOFPLAYER1);
		if (randomChickenLaysEggForPlayer1 == (NUM_CHICKENOFPLAYER1 - 1)
				&& randomChickenLaysItemForPlayer1 == randomChickenLaysEggForPlayer1) {
			randomChickenLaysItemForPlayer1 = randomChickenLaysItemForPlayer1 - 1;
		}
		if (randomChickenLaysItemForPlayer1 == randomChickenLaysEggForPlayer1)
			randomChickenLaysItemForPlayer1 = randomChickenLaysItemForPlayer1 + 1;
		chickenLaysItemForPlayer1 = listChickensOfPlayer1.get(randomChickenLaysItemForPlayer1);
		itemPlayer1 = listItemsOfPlayer1.get(randomItemForPlayer1);
		itemPlayer1.setX_Item(chickenLaysItemForPlayer1.getX_Chicken());
		itemPlayer1.setY_Item(120);
	}

	public void createItemForPlayer2() {
		randomItemForPlayer2 = rd.nextInt(NUM_ITEMSOFPLAYER2);
		randomChickenLaysItemForPlayer2 = rd.nextInt(NUM_CHICKENOFPLAYER2);
		if (randomChickenLaysEggForPlayer2 == (NUM_CHICKENOFPLAYER2 - 1)
				&& randomChickenLaysItemForPlayer2 == randomChickenLaysEggForPlayer2) {
			randomChickenLaysItemForPlayer2 = randomChickenLaysItemForPlayer2 - 1;
		}
		if (randomChickenLaysItemForPlayer2 == randomChickenLaysEggForPlayer2)
			randomChickenLaysItemForPlayer2 = randomChickenLaysItemForPlayer2 + 1;
		chickenLaysItemForPlayer2 = listChickensOfPlayer2.get(randomChickenLaysItemForPlayer2);
		itemPlayer2 = listItemsOfPlayer2.get(randomItemForPlayer2);
		itemPlayer2.setX_Item(chickenLaysItemForPlayer2.getX_Chicken());
		itemPlayer2.setY_Item(120);
	}

	public void createItemForPlayer1AfterXSecond() {
		if (timeToCreateItemForPlayer1 == 20) {
			createItemForPlayer1();
			createdItemForPlayer1 = true;
			timeToCreateItemForPlayer1 = 0;
		}

	}

	public void createItemForPlayer2AfterXSecond() {
		if (timeToCreateItemForPlayer2 == 22) {
			createItemForPlayer2();
			createdItemForPlayer2 = true;
			timeToCreateItemForPlayer2 = 0;
		}
	}

	public void handleDropItemForPlayer1() {
		if (createdItemForPlayer1 == true) {
			itemPlayer1.drop();
			if (itemPlayer1.getY_Item() > 700 || basketPlayer1HaveItem(itemPlayer1))
				createdItemForPlayer1 = false;
		}
	}

	public void handleDropItemForPlayer2() {
		if (createdItemForPlayer2 == true) {
			itemPlayer2.drop();
			if (itemPlayer2.getY_Item() > 700 || basketPlayer2HaveItem(itemPlayer2))
				createdItemForPlayer2 = false;
		}
	}

	private void moveBasket() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_A) {
					basketOfPlayer1.moveLeft();
					repaint();
				}
				if (ke.getKeyCode() == KeyEvent.VK_D) {
					if (basketOfPlayer1.getX_Basket() <= 480) {
						basketOfPlayer1.moveRight();
						repaint();
					}
				}
				if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					if (basketOfPlayer2.getX_Basket() >= 650) {
						basketOfPlayer2.moveLeft();
						repaint();
					}
				}
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					basketOfPlayer2.moveRight();
					repaint();
				}

			}
		});
	}

	public boolean basketPlayer1HaveEgg() {
		Rectangle basketHaveEggRect = new Rectangle(basketOfPlayer1.getX_Basket(), basketOfPlayer1.getY_Basket(), 80,
				50);
		Rectangle eggRect = new Rectangle(egg_Player1.getX_Egg(), egg_Player1.getY_Egg(), 10, 10);
		if (eggRect.intersects(basketHaveEggRect)) {
			updateScoreForPlayer1(egg_Player1.getScore_Egg());
			return true;
		}
		return false;
	}

	public boolean basketPlayer2HaveEgg() {
		Rectangle basketHaveEggRect = new Rectangle(basketOfPlayer2.getX_Basket(), basketOfPlayer2.getY_Basket(), 80,
				50);
		Rectangle eggRect = new Rectangle(egg_Player2.getX_Egg(), egg_Player2.getY_Egg(), 10, 10);
		if (eggRect.intersects(basketHaveEggRect)) {
			updateScoreForPlayer2(egg_Player2.getScore_Egg());
			return true;
		}
		return false;
	}

	public boolean basketPlayer1HaveItem(ItemsOfGame itemX) {
		Rectangle basketHaveItemRect = new Rectangle(basketOfPlayer1.getX_Basket(), basketOfPlayer1.getY_Basket(), 80,
				50);
		Rectangle itemRect = new Rectangle(itemX.getX_Item(), itemX.getY_Item(), 10, 10);
		if (itemRect.intersects(basketHaveItemRect)) {
			if (itemX.getDescription().equalsIgnoreCase("addfivesecond"))
				timeOfGamePlayer1 = itemX.addTime(timeOfGamePlayer1);
			else if (itemX.getDescription().equalsIgnoreCase("addtensecond"))
				timeOfGamePlayer1 = itemX.addTime(timeOfGamePlayer1);
			else if (itemX.getDescription().equalsIgnoreCase("gettenscore")) {
				updateScoreForPlayer1(itemX.getScore());
				updateScoreForPlayer2(-itemX.getScore());
			} else if (itemX.getDescription().equalsIgnoreCase("gettwentyscore")) {
				updateScoreForPlayer1(itemX.getScore());
				updateScoreForPlayer2(-itemX.getScore());
			} else if (itemX.getDescription().equalsIgnoreCase("increasedoubleeggscore")) {
				itemX.increaseEggScore(listKindsOfEggPlayer1);
				changePlayer1EggScore = true;
			} else if (itemX.getDescription().equalsIgnoreCase("resumecompetiorgame")) {
				if (!player2GameOver()) {
					player2IsResumed = true;
				}
			}
			return true;
		}
		return false;
	}

	public boolean basketPlayer2HaveItem(ItemsOfGame itemX) {
		Rectangle basketHaveItemRect = new Rectangle(basketOfPlayer2.getX_Basket(), basketOfPlayer2.getY_Basket(), 80,
				50);
		Rectangle itemRect = new Rectangle(itemX.getX_Item(), itemX.getY_Item(), 10, 10);
		if (itemRect.intersects(basketHaveItemRect)) {
			if (itemX.getDescription().equalsIgnoreCase("addfivesecond"))
				timeOfGamePlayer2 = itemX.addTime(timeOfGamePlayer1);
			else if (itemX.getDescription().equalsIgnoreCase("addtensecond"))
				timeOfGamePlayer2 = itemX.addTime(timeOfGamePlayer1);
			else if (itemX.getDescription().equalsIgnoreCase("gettenscore")) {
				updateScoreForPlayer2(itemX.getScore());
				updateScoreForPlayer1(-itemX.getScore());
			} else if (itemX.getDescription().equalsIgnoreCase("gettwentyscore")) {
				updateScoreForPlayer2(itemX.getScore());
				updateScoreForPlayer1(-itemX.getScore());
			} else if (itemX.getDescription().equalsIgnoreCase("increasedoubleeggscore")) {
				itemX.increaseEggScore(listKindsOfEggPlayer2);
				changePlayer2EggScore = true;
			} else if (itemX.getDescription().equalsIgnoreCase("resumecompetiorgame")) {
				if (!player1GameOver())
					player1IsResumed = true;
			}
			return true;
		}
		return false;
	}

	public void increaseEggScorePlayer1InXSecond() {

		timeToIncreasePlayer1EggScore++;
		if (timeToIncreasePlayer1EggScore == 7) {
			for (int i = 0; i < listKindsOfEggPlayer1.size(); i++) {
				listKindsOfEggPlayer1.get(i).setScore_Egg(listKindsOfEggPlayer1.get(i).getScore_Egg() / 2);
			}
			timeToIncreasePlayer1EggScore = 0;
			changePlayer1EggScore = false;
		}
	}

	public void increaseEggScorePlayer2InXSecond() {

		timeToIncreasePlayer2EggScore++;
		if (timeToIncreasePlayer2EggScore == 7) {
			for (int i = 0; i < listKindsOfEggPlayer2.size(); i++) {
				listKindsOfEggPlayer2.get(i).setScore_Egg(listKindsOfEggPlayer2.get(i).getScore_Egg() / 2);
			}
			timeToIncreasePlayer1EggScore = 0;
			changePlayer2EggScore = false;
		}
	}

	public void player1IsResumedInXSecond() {
		timePlayer1Resumed++;
		if (timePlayer1Resumed == 5) {
			player1IsResumed = false;
			timePlayer1Resumed = 0;
		}

	}

	public void player2IsResumedInXSecond() {
		timePlayer2Resumed++;
		if (timePlayer2Resumed == 5) {
			player2IsResumed = false;
			timePlayer2Resumed = 0;
		}

	}

	public void scoreGameOfPlayer1() {
		scoreOfGameLabelOfPlayer1 = new JLabel();
		scoreOfGameLabelOfPlayer1.setBounds(100, 620, 150, 50);
		scoreOfGameLabelOfPlayer1.setForeground(Color.WHITE);
		scoreOfGameLabelOfPlayer1.setText("Score : " + scoreGameOfPlayer1);
		add(scoreOfGameLabelOfPlayer1);
	}

	public void scoreGameOfPlayer2() {
		scoreOfGameLabelOfPlayer2 = new JLabel();
		scoreOfGameLabelOfPlayer2.setBounds(720, 620, 150, 50);
		scoreOfGameLabelOfPlayer2.setForeground(Color.WHITE);
		scoreOfGameLabelOfPlayer2.setText("Score : " + scoreGameOfPlayer2);
		add(scoreOfGameLabelOfPlayer2);
	}

	public void updateScoreForPlayer1(int score) {
		scoreGameOfPlayer1 = scoreGameOfPlayer1 + score;
		scoreOfGameLabelOfPlayer1.setText("Score : " + scoreGameOfPlayer1);
	}

	public void updateScoreForPlayer2(int score) {
		scoreGameOfPlayer2 = scoreGameOfPlayer2 + score;
		scoreOfGameLabelOfPlayer2.setText("Score : " + scoreGameOfPlayer2);
	}

	public int getScoreOfGame() {
		return scoreGameOfPlayer1;
	}

	public void timeOfGamePlayer1() {
		timeOfGameLabelOfPlayer1 = new JLabel();
		timeOfGameLabelOfPlayer1.setBounds(10, 620, 150, 50); // setting the time label on screen
		timeOfGameLabelOfPlayer1.setForeground(Color.WHITE);
		timeOfGameLabelOfPlayer1.setText("Time : " + timeOfGamePlayer1);
		add(timeOfGameLabelOfPlayer1);
		timePlayedGamePlayer1();
	}

	public void timeOfGamePlayer2() {
		timeOfGameLabelOfPlayer2 = new JLabel();
		timeOfGameLabelOfPlayer2.setBounds(630, 620, 150, 50); // setting the time label on screen
		timeOfGameLabelOfPlayer2.setForeground(Color.WHITE);
		timeOfGameLabelOfPlayer2.setText("Time : " + timeOfGamePlayer2);
		add(timeOfGameLabelOfPlayer2);
		timePlayedGamePlayer2();
	}

	public void timePlayedGamePlayer1() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				timeToCreateItemForPlayer1++;
				createItemForPlayer1AfterXSecond();
				if (changePlayer1EggScore == true) {
					increaseEggScorePlayer1InXSecond();
				}
				if (player1IsResumed) {
					player1IsResumedInXSecond();
				}
				timeToIncreaseSpeedDropEggPlayer1++;
				increaseSpeedDropEggForPlayer1();
				timePlayedGamePlayer1++;
				timeOfGameLabelOfPlayer1.setText("Time : " + (timeOfGamePlayer1 - timePlayedGamePlayer1));
				if (player1GameOver()) {
					time.cancel();
				}
			}
		}, 0, 1000);
	}

	public void timePlayedGamePlayer2() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				timeToCreateItemForPlayer2++;
				createItemForPlayer2AfterXSecond();
				if (changePlayer2EggScore == true) {
					increaseEggScorePlayer2InXSecond();
				}
				if (player2IsResumed) {
					player2IsResumedInXSecond();
				}
				timeToIncreaseSpeedDropEggPlayer2++;
				increaseSpeedDropEggForPlayer2();
				timePlayedGamePlayer2++;
				timeOfGameLabelOfPlayer2.setText("Time : " + (timeOfGamePlayer2 - timePlayedGamePlayer2));
				if (player2GameOver()) {
					time.cancel();
				}
			}
		}, 0, 1000);
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(10);
				if (!player1GameOver() && player1IsResumed == false) {
					handleDropEggForPlayer1();
					handleDropItemForPlayer1();
				}
				if (!player2GameOver() && player2IsResumed == false) {
					handleDropEggForPlayer2();
					handleDropItemForPlayer2();
				}

				if (player1GameOver() && player2GameOver()) {
					Thread.sleep(1500);
					if (soundIsTurnedOn)
						sound.suspend();
					twoPlayersFrame.setVisible(false);
					new GameOverTwoPlayerFrame(this);
					break;
				}
				repaint();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean player1GameOver() {
		return timePlayedGamePlayer1 == timeOfGamePlayer1;
	}

	public boolean player2GameOver() {
		return timePlayedGamePlayer2 == timeOfGamePlayer2;
	}

	public void repaintChickenWhenLaysEgg() {
		if (120 < egg_Player1.getY_Egg() && egg_Player1.getY_Egg() < 130) {
			chickenLaysEggForPlayer1.afterHaveEgg();
		}
		if (egg_Player1.getY_Egg() > 160) {
			chickenLaysEggForPlayer1.beforeHaveEgg();
		}
		if (120 < egg_Player2.getY_Egg() && egg_Player2.getY_Egg() < 130) {
			chickenLaysEggForPlayer2.afterHaveEgg();
		}
		if (egg_Player2.getY_Egg() > 160) {
			chickenLaysEggForPlayer2.beforeHaveEgg();
		}
	}

	public void increaseSpeedDropEggForPlayer1() {
		if (timeToIncreaseSpeedDropEggPlayer1 == 20) {
			for (int i = 0; i < listKindsOfEggPlayer1.size(); i++) {
				listKindsOfEggPlayer1.get(i).increaseSpeedDropEgg();
			}
			timeToIncreaseSpeedDropEggPlayer1 = 0;
		}

	}

	public void increaseSpeedDropEggForPlayer2() {
		if (timeToIncreaseSpeedDropEggPlayer2 == 20) {
			for (int i = 0; i < listKindsOfEggPlayer2.size(); i++) {
				listKindsOfEggPlayer2.get(i).increaseSpeedDropEgg();
			}
			timeToIncreaseSpeedDropEggPlayer2 = 0;
		}

	}

	public TwoPlayers returnThis() {
		return this;
	}

	public void drawGameOfBuffered() {
		bufferedImage = new BufferedImage(1200, 730, BufferedImage.TRANSLUCENT);
		Graphics2D g2d2 = (Graphics2D) bufferedImage.createGraphics();
		if (!player1GameOver()) {
			g2d2.drawImage(basketOfPlayer1.getImg_Basket(), basketOfPlayer1.getX_Basket(),
					basketOfPlayer1.getY_Basket(), null);
			for (int i = 0; i < listChickensOfPlayer1.size(); i++) {
				g2d2.drawImage(listChickensOfPlayer1.get(i).getImg_Chicken(),
						listChickensOfPlayer1.get(i).getX_Chicken(), listChickensOfPlayer1.get(i).getY_Chicken(), null);
			}
			g2d2.drawImage(egg_Player1.getImg_Egg(), egg_Player1.getX_Egg(), egg_Player1.getY_Egg(), null);

			if (createdItemForPlayer1 == true) {
				g2d2.drawImage(itemPlayer1.getImgIcon_Item().getImage(), itemPlayer1.getX_Item(),
						itemPlayer1.getY_Item(), null);
			}

		} else {
			createInformationAfterPlayer1GameOver();
		}

		if (!player2GameOver()) {
			g2d2.drawImage(basketOfPlayer2.getImg_Basket(), basketOfPlayer2.getX_Basket(),
					basketOfPlayer2.getY_Basket(), null);
			for (int i = 0; i < listChickensOfPlayer2.size(); i++) {
				g2d2.drawImage(listChickensOfPlayer2.get(i).getImg_Chicken(),
						listChickensOfPlayer2.get(i).getX_Chicken(), listChickensOfPlayer2.get(i).getY_Chicken(), null);
			}
			g2d2.drawImage(egg_Player2.getImg_Egg(), egg_Player2.getX_Egg(), egg_Player2.getY_Egg(), null);
			if (createdItemForPlayer2 == true) {
				g2d2.drawImage(itemPlayer2.getImgIcon_Item().getImage(), itemPlayer2.getX_Item(),
						itemPlayer2.getY_Item(), null);
			}
		} else {
			createInformationAfterPlayer2GameOver();
		}

	}

	public void createInformationAfterPlayer1GameOver() {
		Font font = new Font("Arial", Font.CENTER_BASELINE, 40);
		JLabel nameLable = new JLabel("TIME OUT");
		nameLable.setBounds(200, 290, 300, 80);
		nameLable.setOpaque(false);
		nameLable.setForeground(Color.RED);
		nameLable.setFont(font);
		nameLable.setBorder(null);
		this.add(nameLable);

	}

	public void createInformationAfterPlayer2GameOver() {
		Font font = new Font("Arial", Font.CENTER_BASELINE, 40);
		JLabel nameLable = new JLabel("TIME OUT");
		nameLable.setBounds(820, 290, 300, 80);
		nameLable.setOpaque(false);
		nameLable.setForeground(Color.RED);
		nameLable.setFont(font);
		nameLable.setBorder(null);
		this.add(nameLable);
	}

	public String getPlayer1Name() {
		return twoPlayersSetting.getNameOfPlayer1();
	}

	public String getPlayer2Name() {
		return twoPlayersSetting.getNameOfPlayer2();
	}

	public int getPlayer1Score() {
		return scoreGameOfPlayer1;
	}

	public int getPlayer2Score() {
		return scoreGameOfPlayer2;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		grabFocus();
		drawGameOfBuffered();
		g.drawImage(bufferedImage, 0, 0, null);

	}
}
