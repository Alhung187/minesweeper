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
    
    Container contentPane = getContentPane();
    JPanel buttonPanel = new JPanel();

    //gridLayout is somethign that already makes a grid of many things (in this case buttons) in java which you can navigate
    GridLayout gridLayout = new GridLayout(18, 18, 0, 0);
    buttonPanel.setLayout(gridLayout);

    //sets size of buttons so that they are squares that fit within a window the size of whatever number is being divided by 18
    int buttonSize = 1000 / 18;
    

    
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // This is where you define what happens when a button is clicked 
            //(preferabbly we wont code everything here we can call a class or something to run an algorith)
            JButton clickedButton = (JButton) e.getSource();
            
            int buttonIndex = buttonPanel.getComponentZOrder(clickedButton);
            if (bombs[buttonIndex]) {
                // Action when a bomb is clicked
                clickedButton.setBackground(Color.RED);
                JOptionPane.showMessageDialog(contentPane, "Boom! Game Over.");
            } else {
                // Action for non-bomb buttons
                clickedButton.setEnabled(false); // disable the button
                clickedButton.setBackground(Color.white);
            }

            
        }
    };

   
    // loop adds all the buttons to the grid
    for (int i = 0; i < 324; i++) {
        JButton button = new JButton();
        button.setMargin(new Insets(0, 0, 0, 0)); // this line of code is completley disposable if its still here its becasue i was trying to do something and never deleted it.
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(buttonSize, buttonSize));
        button.setOpaque(true);

        int row = i / 18;
        int col = i % 18;

        if ((row + col) % 2 == 0) {
            button.setBackground(Color.GRAY);// :) I got lazy so i used basic java colors if yall want we can add icons with nice textures.
        } else {
            button.setBackground(Color.GRAY.brighter());
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
    int bombCount = 50; // Total number of bombs
    for (int i = 0; i < bombCount; i++) {
        int position;
        do {
            position = rand.nextInt(324);
        } while (bombs[position]); // make  qZq sure no bomb is placed twice at the same position
        bombs[position] = true;
    }
}




    
    
}