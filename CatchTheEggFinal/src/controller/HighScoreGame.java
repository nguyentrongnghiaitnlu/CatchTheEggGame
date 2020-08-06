package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.ThePlayer;

public class HighScoreGame extends JLabel {
	private JTextArea area;
	private final String BACKGROUNDHIGHSCORE = "images/highscoreframe.png";
	private final String FILENAMEOFPLAYER = "file/file.txt";

	public HighScoreGame() {
		createBackgroundHighScore();
		createArea();
	}

	private void createBackgroundHighScore() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDHIGHSCORE));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(800, 700, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createArea() {
		Font font = new Font("Serif", Font.CENTER_BASELINE, 24);
		area = new JTextArea();
		area.setBounds(85, 250, 635, 365);
		area.setBackground(Color.DARK_GRAY);
		area.setForeground(Color.YELLOW);
		area.setEditable(false);
		area.setFont(font);
		setTextArea(sortScore());
		this.add(area);

	}

	public static ArrayList<ThePlayer> readFile(String path) {
		ArrayList<ThePlayer> listPlayers = new ArrayList<ThePlayer>();
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			while (line != null) {
				String[] arr = line.split("-");
				ThePlayer player = new ThePlayer();
				player.setName(arr[0]);
				int score = Integer.parseInt(arr[1]);
				player.setScore(score);
				listPlayers.add(player);
				line = br.readLine();
			}
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPlayers;
	}

	private ArrayList<ThePlayer> sortScore() {
		ArrayList<ThePlayer> listPlayer = readFile(FILENAMEOFPLAYER);
		Collections.sort(listPlayer, new Comparator<ThePlayer>() {

			@Override
			public int compare(ThePlayer p1, ThePlayer p2) {
				return p2.getScore() - p1.getScore();
			}
		});
		return listPlayer;
	}

	public void setTextArea(ArrayList<ThePlayer> listPlayers) {
		String distane = "\n";
		String text = distane;
		int count = 0;
		String head = "       " + "RANK" + "\t  " + "PLAYER" + "\t                       " + "SCORE" + "\n";
		for (int i = 0; i < listPlayers.size(); i++) {
			text = text + "           " + (i + 1) + "\t   " + listPlayers.get(i).getName() + "\t\t"
					+ listPlayers.get(i).getScore() + distane;
			count++;
			if (count == 9)
				break;
		}
		area.setText(head + text);

	}

}
