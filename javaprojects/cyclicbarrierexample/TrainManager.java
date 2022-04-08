package nl.cubicated.cyclicbarrierexample;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrainManager{
    private void openDoors(){
        System.out.println("Opening doors.");
    }
    private void passengersLeaveTrain(){
        System.out.println("Passengers leave train.");
    }
    private void passengersEnterTrain(){
        System.out.println("Passengers enter train.");
    }
    private void closeDoors(){
        System.out.println("Closing doors");
    }
    public void doTask(CyclicBarrier c1,CyclicBarrier c2,
     CyclicBarrier c3){
         try{
             openDoors();
             c1.await();
             passengersLeaveTrain();
             c2.await();
             passengersEnterTrain();
             c3.await();
             closeDoors();
         }catch(InterruptedException|BrokenBarrierException e){
             e.printStackTrace();
         }
     }
     public static void main(String[] args) {
         ExecutorService service=null;
         try{
             service=Executors.newFixedThreadPool(4);
             var manager=new TrainManager();
             var c1= new CyclicBarrier(4);
             var c2= new CyclicBarrier(4);
             var c3= new CyclicBarrier(4, 
             ()->System.out.println("---All passengers onboard---"));
             for(int i=0; i<4;i++)
                service.submit(()->manager.doTask(c1, c2, c3));
             
         }finally{
             if(service!=null) service.shutdown();
         }
     }
}