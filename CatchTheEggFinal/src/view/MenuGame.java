package view;

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

public class MenuGame extends JLabel implements ActionListener {
	private JFrame game, onePlayerFrame, helpFrame;
	private JButton onePlayerButton, twoPlayerButton, highScoreButton, helpButton, aboutButton, exitButton;

	// ----
	private Sound sound_MenuFrame;
	private final String LINK_SOUNDMENUFRAME = "music/menumusic.wav";

	private final String BACKGROUNDMENU = "images/backgroundnewgame.png";

	public MenuGame(JFrame game) {
		this.game = game;
		setLayout(null);

		createBackgroundMenu();
		addOnePlayerButton();
		addTwoPlayerButton();
		addHelpButton();
		addHighScoreButton();
		addAboutButton();
		addExitButton();
		sound_MenuFrame = new Sound(LINK_SOUNDMENUFRAME);
		sound_MenuFrame.start();

	}

	private void createBackgroundMenu() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDMENU));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(1200, 730, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addOnePlayerButton() {
		onePlayerButton = new JButton(new ImageIcon("images/oneplayer.png"));
		onePlayerButton.setContentAreaFilled(false);
		onePlayerButton.setBounds(250, 350, 200, 51);
		onePlayerButton.addActionListener(this);
		add(onePlayerButton);
	}

	private void addTwoPlayerButton() {
		twoPlayerButton = new JButton(new ImageIcon("images/twoplayer.png"));
		twoPlayerButton.setContentAreaFilled(false);
		twoPlayerButton.setBounds(510, 350, 200, 51);
		twoPlayerButton.addActionListener(this);
		add(twoPlayerButton);
	}

	private void addHighScoreButton() {
		highScoreButton = new JButton(new ImageIcon("images/highscore.png"));
		highScoreButton.setContentAreaFilled(false);
		highScoreButton.setBounds(770, 350, 200, 51);
		highScoreButton.addActionListener(this);
		add(highScoreButton);
	}

	private void addAboutButton() {
		aboutButton = new JButton(new ImageIcon("images/about.png"));
		aboutButton.setContentAreaFilled(false);
		aboutButton.setBounds(360, 440, 200, 51);
		aboutButton.addActionListener(this);
		add(aboutButton);
	}

	private void addHelpButton() {
		helpButton = new JButton(new ImageIcon("images/help.png"));
		helpButton.setContentAreaFilled(false);
		helpButton.setBounds(660, 440, 200, 51);
		helpButton.addActionListener(this);
		add(helpButton);

	}

	private void addExitButton() {
		exitButton = new JButton(new ImageIcon("images/exit.png"));
		exitButton.setContentAreaFilled(false);
		exitButton.setBounds(510, 530, 200, 51);
		exitButton.addActionListener(this);
		add(exitButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == onePlayerButton) {
			sound_MenuFrame.suspend();
			new OnePlayerFrame();
			game.setVisible(false);
		}
		if (button == twoPlayerButton) {
			sound_MenuFrame.suspend();
			game.setVisible(false);
			new TwoPlayersSettingFrame();
		}
		if (button == highScoreButton) {
			new HighScoreFrame();
		}
		if (button == aboutButton) {
			new AboutFrame();
		}
		if (button == helpButton) {
			helpFrame = new HelpFrame();
		}
		if (button == exitButton)
			System.exit(0);

	}
}
