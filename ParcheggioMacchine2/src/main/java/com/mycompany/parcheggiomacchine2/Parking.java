
package com.mycompany.parcheggiomacchine2;


import java.util.concurrent.Semaphore;

/**
 *
 */
public class Parking {

   
    private static final boolean[] PARKING_PLACES = new boolean[10];
   
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(500);
        }
    }


    public static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("La macchina #%d ha parcheggiato  \n", carNumber);

            try {
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {
                        if (!PARKING_PLACES[i]) {
                            PARKING_PLACES[i] = true;
                            parkingNumber = i;
                            System.out.printf("La macchina #%d a parcheggiato nel posto %d .\n", carNumber, i);
                            break;
                        }
                    }
                }
                //Shopping!
                Thread.sleep(5000);

                synchronized (PARKING_PLACES) {
                    //Free space for car
                    PARKING_PLACES[parkingNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("La macchina #%d a lasciato il parcheggio.\n", carNumber);

            } catch (InterruptedException e) {
            }
        }
    }
}
