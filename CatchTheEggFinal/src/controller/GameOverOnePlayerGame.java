package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ThePlayer;
import view.CatchTheEggs;
import view.GameOverOnePlayerFrame;
import view.JTextFieldLimit;
import view.Sound;

public class GameOverOnePlayerGame extends JLabel {
	private JFrame gameOverFrame;
	private JTextField nameOfPlayer;
	private JLabel scoreOfPlayer;
	private JButton saveGame;
	private final String IMG_BUTTONSAVEGAME = "images/save.png";
	private JButton cancle; // no save game
	private final String IMG_BUTTONCANCLE = "images/cancle.png";
	private final String FILENAMEOFPLAYER = "file/file.txt";
	private ArrayList<ThePlayer> listPlayer = new ArrayList<ThePlayer>();
	private OnePlayer onePlayer;

	// sound when game over;
	private Sound sound_GameOver;
	private final String LINK_SOUNDGAMEOVER = "music/gameovermusic.wav";

	// ---------------------------------------
	// background game
	private final String BACKGROUNDWHENGAMEOVER = "images/backgroundwhengameover.png";

	public GameOverOnePlayerGame(OnePlayer onePlayer, GameOverOnePlayerFrame gameOverOnePlayerFrame) {
		this.onePlayer = onePlayer;
		this.gameOverFrame = gameOverOnePlayerFrame;

		setLayout(null);
		createBackgroundWhenOverGame();

		sound_GameOver = new Sound(LINK_SOUNDGAMEOVER);
		sound_GameOver.start();

		saveGame();
	}

	private void createBackgroundWhenOverGame() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDWHENGAMEOVER));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(1200, 730, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGame() {
		Font font = new Font("Arial", Font.CENTER_BASELINE, 20);
		nameOfPlayer = new JTextField(new JTextFieldLimit(11), "Your name", 10);
		nameOfPlayer.setBounds(550, 287, 130, 45);
		nameOfPlayer.setFont(font);
		add(nameOfPlayer);

		scoreOfPlayer = new JLabel();
		scoreOfPlayer.setOpaque(true);
		scoreOfPlayer.setBackground(Color.WHITE);
		scoreOfPlayer.setBounds(569, 436, 84, 35);
		scoreOfPlayer.setForeground(Color.BLUE);
		scoreOfPlayer.setFont(font);
		scoreOfPlayer.setHorizontalAlignment(JLabel.CENTER);
		scoreOfPlayer.setText(("" + onePlayer.getScoreOfGame()));
		add(scoreOfPlayer);

		saveGame = new JButton(new ImageIcon(IMG_BUTTONSAVEGAME));
		saveGame.setContentAreaFilled(false);
		saveGame.setBounds(510, 500, 100, 26);
		saveGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameOfPlayer.getText();
				int score = Integer.parseInt(scoreOfPlayer.getText());
				ThePlayer player = new ThePlayer(name, score);
				listPlayer.add(player);
				if (name.equalsIgnoreCase("Your name")) {
					JOptionPane.showMessageDialog(null, "Please enter your name");
				} else {
					writeNamePlayerIntoFile(name, score);
					sound_GameOver.suspend();
					gameOverFrame.setVisible(false);
					new CatchTheEggs();
				}
			}
		});
		add(saveGame);

		cancle = new JButton(new ImageIcon(IMG_BUTTONCANCLE));
		cancle.setContentAreaFilled(false);
		cancle.setBounds(610, 500, 100, 26);
		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound_GameOver.suspend();
				gameOverFrame.setVisible(false);
				new CatchTheEggs();
			}
		});
		add(cancle);
	}

	public void writeNamePlayerIntoFile(String nameOfPlayer, int scoreOfPlayer) {
		String content = "";
		File file = new File(FILENAMEOFPLAYER);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true))) {

			for (ThePlayer player : listPlayer) {
				content = player.getName() + "-" + player.getScore() + "\n";
			}

			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
