package com.mycompany.minesweeper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author hamadkhan
 */
public class WindowMaker extends JFrame
{
    private boolean[] bombs = new boolean[324]; // Array to store bomb information

    public WindowMaker()
    {
        super();
        setupLayout();
        distributeBombs();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
  

public void setupLayout() {
    BombScanner scan = new BombScanner();
    Container contentPane = getContentPane();
    JPanel buttonPanel = new JPanel();

    //gridLayout is somethign that already makes a grid of many things (in this case buttons) in java which you can navigate
    GridLayout gridLayout = new GridLayout(15, 15, 0, 0);
    buttonPanel.setLayout(gridLayout);

    //sets size of buttons so that they are squares that fit within a window the size of whatever number is being divided by 18
    int buttonSize = 795 / 15;
    

    
    ActionListener buttonListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

            //Started the process of checking for a right mouse click
            //i wasn't sure how to get the button location as an input though
            if(e.getSource() instanceof MouseEvent){ 
                MouseEvent interaction = (MouseEvent) e.getSource();
                if(SwingUtilities.isRightMouseButton(interaction)){
                    rightClickButton();
                }
            }
        JButton clickedButton = (JButton) e.getSource();
        int buttonIndex = buttonPanel.getComponentZOrder(clickedButton);

        if (bombs[buttonIndex]) {
            // Set the bomb icon when a bomb is clicked
            System.out.println(buttonIndex);
            ImageIcon bombIcon = new ImageIcon("bomb.gif"); // Load the bomb icon
            clickedButton.setIcon(bombIcon); // Set the bomb icon on the button
            JOptionPane.showMessageDialog(contentPane, "Boom! Game Over.");
            
        } else {
            // Handle non-bomb button click
            
            int numbombs = scan.numberOfBombs(bombs, buttonIndex);
            String numberofbombs = numbombs+"";
            
            if(numbombs != 0){
                clickedButton.setText(numberofbombs);
            }

            
        }
    };

   
    // loop adds all the buttons to the grid
    for (int i = 0; i < 225; i++) {
        JButton button = new JButton();
        button.setMargin(new Insets(0, 0, 0, 0)); // this line of code is completley disposable if its still here its becasue i was trying to do something and never deleted it.
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));
        button.setOpaque(true);

        int row = i / 15;
        int col = i % 15;

        if ((row + col) % 2 == 0) {
            button.setBackground(Color.DARK_GRAY);// :) I got lazy so I used basic java colors if yall want we can add icons with nice textures.
        } else {
            button.setBackground(Color.gray);
        }

        // attaches the ActionListener to the button
        button.addActionListener(buttonListener);

        buttonPanel.add(button);
    }

    FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
    contentPane.setLayout(flowLayout);
    contentPane.add(buttonPanel);
    
    pack(); //This will make the window the size of all the content so we dont need a default window size.
    
}

private void distributeBombs() {
    Random rand = new Random();
    int bombCount = 30; // Total number of bombs
    for (int i = 0; i < bombCount; i++) {
        int position;
        do {
            position = rand.nextInt(225);
        } while (bombs[position]); // makes sure no bomb is placed twice at the same position
        bombs[position] = true;
    }
}
//Method to place a flag
//Not sure if there should be an input or not...
private void rightClickButton(){

}




    
    
}