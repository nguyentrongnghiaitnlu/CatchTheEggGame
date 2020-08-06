package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.CatchTheEggs;
import view.GameOverTwoPlayerFrame;
import view.Sound;
import view.TwoPlayersSettingFrame;

public class GameOverTwoPlayerGame extends JLabel {
	private TwoPlayers twoPlayers;
	private JFrame gameOverTwoPlayerFrame;
	private JButton okGame;
	private final String IMG_BUTTONOKGAME = "images/ok.png";
	private Font font = new Font("Arial", Font.CENTER_BASELINE, 30);

	// sound when game over;
	private Sound sound_GameOver;
	private final String LINK_SOUNDGAMEOVER = "music/gameovermusic.wav";

	// ---------------------------------------
	// background game
	private final String BACKGROUNDGAMEOVERTWOPLAYER = "images/backgroundwhengameovertwoplayer.png";

	public GameOverTwoPlayerGame(TwoPlayers twoPlayers, GameOverTwoPlayerFrame gameOverTwoPlayerFrame) {
		this.twoPlayers = twoPlayers;
		this.gameOverTwoPlayerFrame = gameOverTwoPlayerFrame;
		createBackgroundGameOverTwoPlayer();

		createPlayer1NameLable();
		createPlayer2NameLable();
		createPlayer1ScoreLable();
		createPlayer2ScoreLable();
		createWinnerNameLable();

		sound_GameOver = new Sound(LINK_SOUNDGAMEOVER);
		sound_GameOver.start();

		okGame();
	}

	private void createBackgroundGameOverTwoPlayer() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDGAMEOVERTWOPLAYER));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(1200, 730, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createPlayer1NameLable() {
		JLabel player1NameLable = new JLabel(twoPlayers.getPlayer1Name());
		player1NameLable.setBounds(200, 150, 250, 80);
		player1NameLable.setFont(font);
		player1NameLable.setForeground(Color.BLUE);

		add(player1NameLable);
	}

	public void createPlayer2NameLable() {
		JLabel player2NameLable = new JLabel(twoPlayers.getPlayer2Name());
		player2NameLable.setBounds(850, 150, 250, 80);
		player2NameLable.setFont(font);
		player2NameLable.setForeground(Color.MAGENTA);

		add(player2NameLable);
	}

	public void createPlayer1ScoreLable() {
		JLabel player1ScoreLable = new JLabel(twoPlayers.getPlayer1Score() + "");
		player1ScoreLable.setBounds(250, 195, 200, 70);
		player1ScoreLable.setFont(font);
		player1ScoreLable.setForeground(Color.BLUE);
		add(player1ScoreLable);
	}

	public void createPlayer2ScoreLable() {
		JLabel player2ScoreLable = new JLabel(twoPlayers.getPlayer2Score() + "");
		player2ScoreLable.setBounds(900, 195, 200, 70);
		player2ScoreLable.setFont(font);
		player2ScoreLable.setForeground(Color.MAGENTA);
		add(player2ScoreLable);
	}

	public void createWinnerNameLable() {
		font = new Font("Arial", Font.CENTER_BASELINE, 50);
		JLabel winnerNameLable = new JLabel();
		winnerNameLable.setFont(font);

		if (twoPlayers.getPlayer1Score() > twoPlayers.getPlayer2Score()) {
			winnerNameLable.setText(twoPlayers.getPlayer1Name());
			winnerNameLable.setForeground(Color.BLUE);
		} else {
			winnerNameLable.setText(twoPlayers.getPlayer2Name());
			winnerNameLable.setForeground(Color.MAGENTA);
		}

		winnerNameLable.setBounds(460, 385, 400, 85);

		add(winnerNameLable);
	}

	private void okGame() {
		okGame = new JButton(new ImageIcon(IMG_BUTTONOKGAME));
		okGame.setContentAreaFilled(false);
		okGame.setBounds(530, 550, 150, 38);
		okGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound_GameOver.suspend();
				gameOverTwoPlayerFrame.setVisible(false);
				new CatchTheEggs();
			}
		});
		add(okGame);
	}

}
