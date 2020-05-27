
package com.mycompany.parcheggiomacchine;

public class Macchina extends Thread {
    
    private Parcheggio parc;
    public Macchina(String nome, Parcheggio p) { 
    super(nome);
    this.parc = p;
    start();
    }
    public void run() {
        while (true) 
        {
            
            try { sleep((int)(Math.random() * 20000)); 
            } catch (InterruptedException e) {e.printStackTrace();} synchronized(parc){
            parc.entra();
            System.out.println(getName()+" e entrata , posti liberi:"+parc.posti);
            } 
            
            try { sleep((int)(Math.random() * 10000)); 
            } catch (InterruptedException e) {e.printStackTrace();} synchronized(parc){
            parc.esce();
            System.out.println(getName()+" e uscita , posti liberi:"+parc.posti);
            } 
        } 
    } 
}
