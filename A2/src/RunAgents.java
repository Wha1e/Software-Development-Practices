import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Alexander on 10/24/16.
 * This script runs the agents as described in the instructions in 3 threads.
 */
public class RunAgents {

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
