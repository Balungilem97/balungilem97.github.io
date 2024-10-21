import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel {
    final private JButton[][] buttons;  // Grid buttons
    final private char[][] board;       // Logical board
    final private Game game;            // Reference to Game object
    final private char emptySymbol = '-';

    public Board(Game game) {
        this.game = game;
        this.board = new char[3][3];
        buttons = new JButton[3][3];
        setLayout(new GridLayout(3, 3, 5, 5));  // Add padding between buttons
        setBackground(new Color(30, 144, 255)); // Set background color (light blue)
        initializeBoard();
    }

    // Initialize board and buttons with color and style
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = emptySymbol;  // Empty board initially
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setBackground(Color.WHITE);  // Set button background color
                buttons[i][j].setForeground(Color.BLACK);  // Set text color to black initially

                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (board[row][col] == emptySymbol) {
                            char symbol = game.getCurrentPlayer().getSymbol();
                            buttons[row][col].setText(String.valueOf(symbol));
                            board[row][col] = symbol;
                            buttons[row][col].setEnabled(false);  // Disable button after move

                            // Change color based on player
                            if (symbol == 'X') {
                                buttons[row][col].setForeground(Color.RED);
                            } else {
                                buttons[row][col].setForeground(Color.BLUE);
                            }

                            game.checkGameStatus(row, col);
                        }
                    }
                });
                add(buttons[i][j]);
            }
        }
    }

    // Check if the board is full (draw)
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == emptySymbol) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if there's a winner
    public boolean checkWin(char symbol) {
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||  // Row 1
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||  // Row 2
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||  // Row 3
                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||  // Col 1
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||  // Col 2
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||  // Col 3
                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||  // Diagonal 1
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);    // Diagonal 2
    }

    // Disable all buttons (end of game)
    public void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
