/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.minesweeper;
import java.util.*;

/**
 *
 * @author hamadkhan
 */
public class BombScanner {

    public ArrayList<Integer> getNeighbors(int currentbutton){
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        boolean left = false;
        boolean up = false;
        boolean right = false;
        boolean down = false;

        if(currentbutton % 15 != 0){
            neighbors.add(currentbutton-1);
            left = true;
        }

        if(currentbutton > 14){
            neighbors.add(currentbutton-15);
            up = true;
        }

        if(currentbutton % 15 != 14){
            neighbors.add(currentbutton+1);
            right = true;
        }

        if(currentbutton < 210){
            neighbors.add(currentbutton+15);
            down = true;
        }

        if(left & up){
            neighbors.add(currentbutton-16);
        }

        if(right & up){
            neighbors.add(currentbutton-14);
        }

        if(left & down){
            neighbors.add(currentbutton+14);
        }

        if(right & down){
            neighbors.add(currentbutton+16);
        }

        return neighbors;
    }
    
    
    public int numberOfBombs(boolean[] bomblocations, int currentbutton){
        
        int numberofbombs = 0;

        ArrayList<Integer> neighborSquares = getNeighbors(currentbutton);

        Iterator<Integer> iterator = neighborSquares.iterator();
        while (iterator.hasNext()) {
            if(bomblocations[iterator.next()]){
                numberofbombs ++;
            }
        }
       
        return numberofbombs;
    }
}
