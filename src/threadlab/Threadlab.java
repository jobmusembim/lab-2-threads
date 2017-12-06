/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//section one threads
package threadlab;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.TimeUnit;


/**
 *
 * @author job
 */
public class Threadlab {
public static int age, yrs;
 public static int ages[] = {20, 45, 47, 38, 35, 67, 18, 34};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // A.	Report the ages of people in the array 
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run(){
              for (int i=0; i<ages.length; i++){
                  System.out.println("Ages "+ages[i]);
              } 
            }
        });
        //B.	Report the number of years, which have elapsed since they acquired a national identity card (national identity card is acquired at age 18, thereby one is eligible to vote)
       Thread t2 = new Thread (new Runnable(){
          @Override
          public void run (){
              for (int i=0; i<ages.length; i++){
                  System.out.println("years elapsed " + (ages[i]-18));
          }
          }
       });
       //C.	The number of times they have voted (assume that all the people whose ages are represented in the array above voted immediately they acquired a national ID and that an elected leader stays in office for 5 years before national election happens again. )
       Thread t3 =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<ages.length; i++){
                    int f=(ages[i]-18)/5 + 1;
                    System.out.println("No. voting "+f);
                }
            }
        });
       t1.start();
     try {
         t1.join();
     } catch (InterruptedException iex) {
         
     } catch (Exception ex){
         
     }
         
       t2.start();
       t3.start();      
       /*t1.start();
       t2.start();
       t3.start();*/
}
     public static class MyThread {
        private String task;
        MyThread (String typeOfTaks){
		this.task = typeOfTaks;
        }
        
        //
        static Thread t1 = new Thread (new Runnable()
                {
                @Override
                public void run(){
                    synchronized(ages){
                        System.out.println("locked age array");
                        }
                    
                    for (int i = 0; i < ages.length; i++) {
                        age = ages[i];
                        yrs = age -18;
                        System.out.println("Years elapsed are " + yrs + " years "
                        + "\n");
                        }
                        try{t1.sleep(5000);}catch(InterruptedException ie){};
                    }
                });

        static Thread t2 = new Thread(new Runnable()
                    {
                    @Override
                    public void run(){
                        for (int i = 0; i < ages.length; i++) {
                            age = ages[i];
                            System.out.println("your age is " + age + "\n");
                            }
                        try{t2.sleep(5000);}catch(InterruptedException ie){};
                        }
                    });
    
        static Thread t3 = new Thread(new Runnable()
                    {
                    @Override
                    public void run(){
                        for (int i = 0; i < ages.length; i++) {
                            age = ages[i];
                            yrs = age -18;
                            int voted = (yrs / 5) +1;
                            System.out.println("Number of times voted is" + voted + " times\n");
                            }
                        try{t3.sleep(5000);}catch(InterruptedException ie){};
                        }
                    });
    }

    /**
     *instance of MyThread
     */
    public static void MyThread() {
        MyThread mt = new MyThread("");
        Thread ourThreads[] = {mt.t1,mt.t2,mt.t3};

        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i=0; i<ourThreads.length;i++){
            executor.submit(ourThreads[i]);
        }
            executor.shutdown();
            System.out.println("All tasks submitted");
                try {
                    executor.awaitTermination(1, TimeUnit.DAYS);
                    } catch (InterruptedException ex) {
                    
                    }
                System.out.println("All tasks completed");
    }

}
