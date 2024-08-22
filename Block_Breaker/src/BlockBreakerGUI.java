import javax.swing.JFrame;
import GUI.GamePlay;

public class BlockBreakerGUI extends JFrame{
  
    public BlockBreakerGUI(){
        
        GamePlay gamePlay = new GamePlay();
        setTitle("Block Breaker");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(gamePlay);
    }
}
