package nl.cubicated.cyclicbarrierexample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MetroManager {

    public static void printmsg(Lock lock){
        System.out.println("Dear travellers, wearing a sanitary mask is mandatory on this station"+
        "and in the metros. We kindly thank you for your cooperation. Safe travels.");
        System.out.println("Chers voyageurs, porter un masque sanitaire est obligatoire dans cette station"+
        " et dans les métros. Nous vous remercions de votre coopération. Bonne Voyage.");
        System.out.println("Liebe Reisende, das Tragen einer Hygienenmaske ist in diesem Bahnhof und in den "+
        " U-Bahnen verpflichtet. Wir danken Ihnen für Ihre Mitarbeit. Gute Reise.");
    }
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        new Thread(()->printmsg(lock)).start();
        
        if(lock.tryLock()){
            try{
                System.out.println("Play announcement melody.");
            }finally{
                lock.unlock();
            }
        }else{
            System.out.println("(Announcement could not be played, please try again later.)");
        }
        
    }
}
