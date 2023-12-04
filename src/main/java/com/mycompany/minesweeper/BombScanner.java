/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweeper;

/**
 *
 * @author hamadkhan
 */
public class BombScanner {
    
    
    public int numberOfBombs(boolean[] bomblocations, int currentbutton){
        
        int numberofbombs = 0;
        
        if(currentbutton > 15){
            if(bomblocations[currentbutton-14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
        }
        else if(currentbutton > 1){
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
        }
        else{
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
        }
       
        return numberofbombs;
    }
}
