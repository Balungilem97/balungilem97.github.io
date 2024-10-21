import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private Player player1, player2;
    private Player currentPlayer;
    final private Board board;

    public Game() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();
        board = new Board(this);
        add(board, BorderLayout.CENTER);
        add(createTitlePanel(), BorderLayout.NORTH);  // Add a title panel
    }

    private void initializeGame() {
        String player1Name = JOptionPane.showInputDialog("Enter Player 1's name:");
        String player2Name = JOptionPane.showInputDialog("Enter Player 2's name:");
        player1 = new Player(player1Name, 'X');
        player2 = new Player(player2Name, 'O');
        currentPlayer = player1;  // Player 1 first
    }

    // Create a title panel with styling
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 128, 128));  // Teal background for title
        JLabel title = new JLabel("Tic Tac Toe");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        return titlePanel;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public void checkGameStatus(int row, int col) {
        if (board.checkWin(currentPlayer.getSymbol())) {
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            board.disableBoard();
        } else if (board.isFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            board.disableBoard();
        } else {
            switchPlayer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                game.setVisible(true);
            }
        });
    }
}
