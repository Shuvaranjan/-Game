package GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
    // JPanel GamePlay;
    private boolean play = false;
    private int score = 0;

    private int Total_bricks = 50;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGeneretor map;

    public GamePlay() {
        map = new MapGeneretor(5, 10);
        addKeyListener(this);
        setFocusable(true);
        // setCursor(Cursor.getPredefinedCursor(Cursor.));
        Toolkit toolkit = getToolkit();
        Image cursorimg = toolkit.getImage(ClassLoader.getSystemResource("IMG/round.png"));
        Point hotspot = new Point(0, 0);
        setCursor(toolkit.createCustomCursor(cursorimg, hotspot, TOOL_TIP_TEXT_KEY));

        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background color
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, 0, 692, 592);

        // // borders
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(682, 0, 3, 592, 20, 20);
        g.fillRoundRect(0, 0, 692, 3, 20, 20);
        g.fillRoundRect(0, 0, 3, 592, 20, 0);

        // paddle
        g.setColor(Color.ORANGE);
        g.fillRoundRect(playerX, 550, 100, 8, 8, 8);

        // ball
        g.setColor(new Color(157, 0, 255));
        g.drawOval(ballPosX, ballPosY, 20, 20);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if (play) {
         

        }

        if (Total_bricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 45));
            g.drawString("You Won!", 220, 320);
            g.setFont(new Font("Arial", Font.PLAIN, 25));
            g.drawString("Your Score: " + score, 240, 350);

            g.setColor(Color.RED);
            g.setFont(new Font("HP Simple", Font.PLAIN, 22));
            g.drawString("[Press Enter to Restart]", 230, 380);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Score: " + score, 550, 30);

        if (ballPosY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 45));
            g.drawString("Game Over!", 220, 320);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Your Highest Score: " + score, 240, 350);

            g.setFont(new Font("HP Simple", Font.PLAIN, 22));
            g.drawString("[Press Enter to Restart]", 230, 380);

            
         

        }
      
        // drawing map
        map.draw((Graphics2D) g);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;

            }

            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rectangle;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            Total_bricks--;
                            score = score + 8;

                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;

                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }

                    }

                }

            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if (ballPosX <= 0) {
                ballXdir = -ballXdir;

            }
            if (ballPosY <= 0) {
                ballYdir = -ballYdir;

            }
            if (ballPosX > 670) {
                ballXdir = -ballXdir;

            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not using
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;

            } else {
                moveRight();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;

            } else {
                moveLeft();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 300;
                score = 0;
                Total_bricks = 50;
                map = new MapGeneretor(5, 10);

                repaint();

            }

        }
    }

    private void moveRight() {
        play = true;
        playerX += 25;
    }

    private void moveLeft() {
        play = true;
        playerX -= 25;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not using
    }

}
