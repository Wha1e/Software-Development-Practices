import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Alexander on 10/24/16.
 * @author Alexander Chatron-Michaud
 * This script runs the agents as described in the instructions in 3 threads.
 */
public class RunAgents {

    /**
    This method creates three booking agents each in their own threads, one manual and two automatic.
    The process terminates when all the seats in the airplane have been booked.
    The GUI can be interacted with by clicking on a seat to book it (wait a few milliseconds for it to appear)
    A live report of the actions being taken appears in the console.
     */
    public static void main(String[] args) {
        Airplane ap = new Airplane();
        Lock bookingLock = new ReentrantLock();
        Runnable agent1 = new AutomatedAgent(1,ap,bookingLock);
        Runnable agent2 = new AutomatedAgent(2,ap,bookingLock);
        Runnable agent3 = new ManualAgent(3,ap, bookingLock);
        Thread agent1_thread = new Thread(agent1);
        Thread agent2_thread = new Thread(agent2);
        Thread agent3_thread = new Thread(agent3);
        agent1_thread.start();
        agent2_thread.start();
        agent3_thread.start();

    }
}
