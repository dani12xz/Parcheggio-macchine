
package com.mycompany.parcheggiomacchine;

public class Main {

    public static void main(String[] args) {
        
        Parcheggio park = new Parcheggio(10);
        for (int i=1; i<= 10; i++) 
        {
        Macchina c = new Macchina("Auto "+i, park);
        }
        
    }
    
}
