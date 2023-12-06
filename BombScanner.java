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
        
        /*** The buttons around the perimeter are spoiled so they need special treatment ***/
        // Will handle very first button of the grid(top left corner)
        if(currentbutton==0){
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }                
        }
        // Will handle top right button on the grid
        else if(currentbutton==14){
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }                
        }
        // Will handle bottom left button in the grid
        else if(currentbutton==210){
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton-14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }                
        }
        // Will handle the last button in the grid(bottom right corner)
        else if(currentbutton==224){
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton-16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }                
        }
        // Will handle top column of the grid
        else if(currentbutton < 15){
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            } 
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }  
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }  
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            } 
        }    
        // Will handle bottom column of the grid    
        else if(currentbutton > 210){
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            } 
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }  
            if(bomblocations[currentbutton-16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }  
            if(bomblocations[currentbutton-14]){
                numberofbombs++;
            }              
        }
        // Will handle buttons in the row at the far right of the grid 
        else if(currentbutton%15==14 && currentbutton!=14 && currentbutton!=224){            
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton+14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }        
        }
        // Will handle buttons in the far left row of the grid
        else if(currentbutton%15==0 && currentbutton!=0 && currentbutton!=210){            
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }           
            if(bomblocations[currentbutton+16]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton+15]){
                numberofbombs++;
            }        
        }
        /*** This is where the spoiled buttons finish ***/
        // Will handle all other bombs
        else{
            if(bomblocations[currentbutton+1]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-1]){
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
            if(bomblocations[currentbutton-14]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-15]){
                numberofbombs++;
            }
            if(bomblocations[currentbutton-16]){
                numberofbombs++;
            }
            
        }
        
       
        return numberofbombs;
    }
}
