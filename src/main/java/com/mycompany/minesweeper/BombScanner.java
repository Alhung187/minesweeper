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

    public ArrayList<Integer> getClearedNeighbors(boolean[] bomblocations, int currentbutton){
        ArrayList<Integer> clearedNeighbors = new ArrayList<Integer>();

        ArrayList<Integer> immediateNeighbors = getNeighbors(currentbutton);
        ArrayList<Integer> nextNeighbors = new ArrayList<Integer>();
        ArrayList<Integer> newImmediateNeighbors = new ArrayList<Integer>();
        ArrayList<Integer> reviewedSquares = new ArrayList<Integer>();

        while(true) {
            System.out.println(immediateNeighbors);
            if(immediateNeighbors.size() == 0){
                break;
            }
            nextNeighbors.clear();
            for(int i = 0; i<immediateNeighbors.size(); i++){
                Integer square = immediateNeighbors.get(i);
                if (!bomblocations[square]) {
                    clearedNeighbors.add(square);
                }
                int bombNumber = numberOfBombs(bomblocations, square);
                if(bombNumber == 0 & !reviewedSquares.contains(square)){
                    nextNeighbors.add(square);
                }
                if(!reviewedSquares.contains(square)){
                    reviewedSquares.add(square);
                }
            }
            newImmediateNeighbors.clear();
            for(int i = 0; i<nextNeighbors.size(); i++){
                ArrayList<Integer> squareNeighbors = getNeighbors(nextNeighbors.get(i));
                for(int j = 0; j<squareNeighbors.size(); j++){
                    if(!newImmediateNeighbors.contains(squareNeighbors.get(j))){
                        newImmediateNeighbors.add(squareNeighbors.get(j));
                    }
                }
            }

            immediateNeighbors.clear();
            immediateNeighbors.addAll(newImmediateNeighbors);
            newImmediateNeighbors.clear();
        }

        return clearedNeighbors;
    }

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
