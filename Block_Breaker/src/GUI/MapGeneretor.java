package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGeneretor {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGeneretor(int row, int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
                
            }
            
        }
        brickWidth = 540/col;
        brickHeight = 350/col;
    }
    public void draw(Graphics2D g2){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g2.setColor(new Color(255, 64, 0));
                    g2.fillRect(j*brickWidth + 70, i*brickHeight + 50, brickWidth, brickHeight);
                    g2.setStroke(new BasicStroke(4));
                    g2.setColor(new Color(20,20,20));
                    g2.drawRect(j*brickWidth + 70, i*brickHeight + 50, brickWidth, brickHeight);                }
                
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
    
}
