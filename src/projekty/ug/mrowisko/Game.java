package projekty.ug.mrowisko;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	public class Mrowka {
		public int x = rand(WIDTH);
		public int y = rand(HEIGH);
		public int kierunek = 1;
		public int switcher = 0;
		
		
		
		public int getX() {
	        return x;
	    }
		
		
	    public int getY() {
	        return y;
	    }
	    
	    //liczymy prawdopodobieñstwo bior¹c pod uwagê, ¿e czêœciej mrówka pójdzie w kierunku swojego pêdu
	    public int korekta(int kierunek, int los) {
	    	
	    	int new_kierunek=kierunek;
	    	if(los<10) new_kierunek = kierunek;
	    	else if(los<15) new_kierunek = kierunek + 1;
	    	else if(los<20) new_kierunek = kierunek - 1;
	    	else if(los<22) new_kierunek = kierunek + 2;
	    	else if(los<24) new_kierunek = kierunek - 2;
	    	else if(los<25) new_kierunek = kierunek + 3;
	    	else if(los<26) new_kierunek = kierunek - 3;
	    	if(new_kierunek<0) new_kierunek = 9 + new_kierunek;
	    	else if(new_kierunek>8) new_kierunek = 9 - new_kierunek;
	    	return new_kierunek;
	    }

		
	    //  Poruszanie siê i okreœlenie k¹ta mrówki
		public void moveBall() {
			int l;
			System.out.print("x:" + x + " y:" + y);
			int dx = 0;
			int dy = 0;	
			switch (l = korekta(switcher, los())) {
			case 1:
				kierunek = 180;
				dy = 5;
				break;
			case 2:
				kierunek = 135;
				dx = 5;
				dy = 5;
				break;
			case 3:
				kierunek = 90;
				dx = 5;
				break;
			case 4:
				kierunek = 45;
				dx = 5;
				dy = -5;
				break;
			case 5:
				kierunek = 0;
				dy = -5;
				break;
			case 6:
				kierunek = 315;
				dx = -5;
				dy = -5;
				break;
			case 7:
				kierunek = 270;
				dx = -5;
				break;
			case 8:
				kierunek = 225;
				dx = -5;
				dy = 5;
				break;
			}
			switcher = l;
			if ((dx > 0) && (x < WIDTH))
				x = x + dx;
			else if ((dx < 0) && (x > 5))
				x = x + dx;

			if ((dy > 0) && (y < HEIGH))
				y = y + dy;
			else if ((dy < 0) && (y > 5))
				y = y + dy;

			System.out.println(" los:" + l + " x:" + x + " y:" + y);
		}

	}

	 //Przypisywanie mrówek do tablicy mrówek o wielkoœci MRÓWKA = 1000
	public Game() {
		super();
		mrowisko = new Mrowka[MROWISKO];
		for (int i = 0; i < MROWISKO; i++) {
			mrowisko[i] = new Mrowka();
		}

	}

	public final int WIDTH = 1280;
	public final int HEIGH = 1024;
	public final int MROWISKO = 1000;
	private Mrowka mrowisko[];
	private Random generator;

	//generujemy losowe po³o¿enie mrówki Spawn
	public int rand(int a) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(a);
		return randomInt;
	}

	//losujemy kierunek poruszania sie mrówki
	public int los() {
		if (generator == null)
			generator = new Random();
		// System.out.printf("losowane: %d%n", g);
		return generator.nextInt(26);

	}

	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(6);
	int g = randomInt;


	//obracamy grafike mrówki
	public Image rotate(Image img, int kierunek) { 
	      int w = img.getWidth(this);  
	      int h = img.getHeight(this);  
	      BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	      Graphics2D g2d = newImage.createGraphics();;
	      g2d.rotate(Math.toRadians(kierunek), w/2, h/2);  
	      g2d.drawImage(img,0,0,this);
	      return newImage;  
	  }
	
	//rysujemy mrówkê na planszy
	@Override
	public void paint(Graphics g) {
		initBoard();
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < MROWISKO; i++) {
			
	//		g2d.fillOval(mrowisko[i].x, mrowisko[i].y, 10, 10);
			g2d.drawImage(rotate(ant, mrowisko[i].kierunek), mrowisko[i].x, mrowisko[i].y, this);
		}

	}
	

	
	private Image ant;
 

	
	//inicjujemy wczytany obraz
	 private void initBoard() {
	        
	        loadImage();
	        
	        int w = ant.getWidth(this);
	        int h =  ant.getHeight(this);
	        setPreferredSize(new Dimension(w, h));        
	    }
	
	 
	 //wczytujemy obrazek
	private void loadImage() {
		 ImageIcon ii = new ImageIcon("ant.png");  
		// TODO Auto-generated method stub
		ant = ii.getImage();
	}

	//mrowka i jej poruszanie siê oraz malowanie
	public void mrowka() {
		for (int i = 0; i < MROWISKO; i++) {
			mrowisko[i].moveBall();
		}
		repaint();
	}
	



    public void BackgroundImageJFrame() {

        setSize(400,400);
        setVisible(true);

        setLayout(new BorderLayout());

        JLabel background=new JLabel(new ImageIcon("C:\\Workspaces\\Mrowisko\\test.jpg"));

        add(background);

        background.setLayout(new FlowLayout());
       
    }

	
	

	// ---------------------------------MAIN---------------------------------

	//wczytujemy ramkê
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("MROWISKO");
		
		Game game = new Game();
		game.BackgroundImageJFrame();
		frame.add(game);
		frame.setSize(game.WIDTH, game.HEIGH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pêtla nieskoñczona która wykonuje mrówki, odczekuje i dalej porusza je
		while (true) {
			game.mrowka();
			// game.moveBall();
			// game.repaint();
			Thread.sleep(100);
		}
	}
}
