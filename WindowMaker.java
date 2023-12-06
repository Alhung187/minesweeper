package com.mycompany.minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

public class WindowMaker extends JFrame {
    private boolean[] bombs = new boolean[225];
    private JButton[] buttons = new JButton[225];
    private JLabel timerLabel;
    private int elapsedTime = 0;
    private Timer timer;
    private int flagCount = 0;
    private JLabel flagCountLabel;
    private final int totalBombCount = 30; // Total number of bombs
    private boolean firstClick = true; // Flag to check for first click

    public WindowMaker() {
        super();
        setupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupLayout() {
        BombScanner scan = new BombScanner();
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Top panel with timer and flag count
        JPanel topPanel = new JPanel();
        timerLabel = new JLabel("Time: 0");
        topPanel.add(timerLabel);

        // Label to display flag count
        flagCountLabel = new JLabel("Flags: 0");
        topPanel.add(flagCountLabel);

        // Initialize and start the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                timerLabel.setText("Time: " + elapsedTime);
            }
        });
        timer.start();

        JPanel buttonPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(15, 15, 0, 0);
        buttonPanel.setLayout(gridLayout);
        int buttonSize = 795 / 15;

        // Right click functionality to place flags
        MouseAdapter rightClick = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                if (SwingUtilities.isRightMouseButton(e) && clickedButton.isEnabled()) {
                    if (clickedButton.getIcon() == null) {
                        clickedButton.setIcon(new ImageIcon("DangerFlag.png"));
                        flagCount++;
                    } else {
                        clickedButton.setIcon(null);
                        flagCount--;
                    }
                    flagCountLabel.setText("Flags: " + flagCount);
                    checkBombReveal();
                }
            }
        };

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                int buttonIndex = buttonPanel.getComponentZOrder(clickedButton);

                if (firstClick) {
                    distributeBombs(buttonIndex);
                    firstClick = false;
                }

                if (clickedButton.getIcon() != null) {
                    // Do nothing if the button is flagged
                    return;
                }

                if (bombs[buttonIndex]) {
                    timer.stop(); // Stop the timer as the game is over
                    ImageIcon bombIcon = new ImageIcon("bomb.gif");
                    clickedButton.setIcon(bombIcon);
                    JOptionPane.showMessageDialog(contentPane, "Boom! Game Over.");
                } else {
                    int numbombs = scan.numberOfBombs(bombs, buttonIndex);
                    if (numbombs == 0) {
                        clickedButton.setEnabled(false);
                        clickedButton.setBackground(Color.WHITE);
                        revealAdjacent(buttonIndex, scan);
                    } else {
                        clickedButton.setText(Integer.toString(numbombs));
                        clickedButton.setEnabled(false);
                        clickedButton.setBackground(Color.WHITE);
                    }
                }
            }
        };

        for (int i = 0; i < 225; i++) {
            JButton button = new JButton();
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(buttonSize, buttonSize));
            button.setOpaque(true);
            button.addMouseListener(rightClick);
            button.addActionListener(buttonListener);

            int row = i / 15;
            int col = i % 15;
            if ((row + col) % 2 == 0) {
                button.setBackground(Color.DARK_GRAY);
            } else {
                button.setBackground(Color.gray);
            }

            buttons[i] = button;
            buttonPanel.add(button);
        }

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        pack();
    }

    private void distributeBombs(int firstClickIndex) {
        Random rand = new Random();
        for (int i = 0; i < totalBombCount; i++) {
            int position;
            do {
                position = rand.nextInt(225);
            } while (bombs[position] || isAdjacent(position, firstClickIndex));
            bombs[position] = true;
        }
    }

    private boolean isAdjacent(int position, int firstClickIndex) {
        int row = position / 15;
        int col = position % 15;
        int firstClickRow = firstClickIndex / 15;
        int firstClickCol = firstClickIndex % 15;

        // Check if the position is adjacent (including diagonally) to the first click
        return Math.abs(row - firstClickRow) <= 1 && Math.abs(col - firstClickCol) <= 1;
    }

    private void revealAdjacent(int index, BombScanner scan) {
        int row = index / 15;
        int col = index % 15;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                int newIndex = newRow * 15 + newCol;

                if (newRow >= 0 && newRow < 15 && newCol >= 0 && newCol < 15) {
                    JButton button = buttons[newIndex];
                    if (!bombs[newIndex] && button.isEnabled() && button.getIcon() == null) {
                        int numbombs = scan.numberOfBombs(bombs, newIndex);

                        if (numbombs == 0) {
                            button.setEnabled(false);
                            button.setBackground(Color.WHITE);
                            revealAdjacent(newIndex, scan);
                        } else {
                            button.setText(Integer.toString(numbombs));
                            button.setEnabled(false);
                            button.setBackground(Color.WHITE);
                        }
                    }
                }
            }
        }
    }

    private void checkBombReveal() {
        if (flagCount == totalBombCount) {
            revealAllBombs();
        }
    }

    private void revealAllBombs() {
        ImageIcon bombIcon = new ImageIcon("bomb.gif");
        for (int i = 0; i < bombs.length; i++) {
            if (bombs[i]) {
                buttons[i].setIcon(bombIcon);
            }
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        WindowMaker window = new WindowMaker();
        window.setVisible(true);
    }
}
