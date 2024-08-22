import javax.swing.SwingUtilities;

public class Run {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new BlockBreakerGUI().setVisible(true);
            }
        });
    }
    
}
