/**
 * Created by Alexander on 10/24/16.
 * @author Alexander Chatron-Michaud
 * This class is to run an automated booking agent on a given airplane. This class is made to run in a thread.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.ThreadLocalRandom;

public class AutomatedAgent implements Runnable {

    private int agent_number;
    private Airplane my_airplane;
    private Lock currently_booking;

    /**
     * Constructs an automated agent
     * @param init_agent_number the integer number this agent will use for booking/marking seats
     * @param init_my_airplane the airplane the agent will be booking seats in
     * @param init_currently_booking the lock/monitor used to check if another thread is trying to book something at the same time
     */
    public AutomatedAgent(int init_agent_number, Airplane init_my_airplane, Lock init_currently_booking) {
        this.agent_number = init_agent_number;
        this.my_airplane = init_my_airplane;
        this.currently_booking = init_currently_booking;
    }

    /**
     * Automatically books seats every few seconds. Locks before attempting booking to prevent race conditions.
     */
    public void run() {
        try {
            while (my_airplane.seats_left()) {
                int row = ThreadLocalRandom.current().nextInt(0, 50);
                int seat = ThreadLocalRandom.current().nextInt(0, 4);
                currently_booking.lock(); //critical section start
                my_airplane.set_if_free(this.agent_number, row, seat);
                currently_booking.unlock(); //critical section end
                Thread.sleep(100);
            }
        }
        catch (InterruptedException exception) {
            System.out.println("AutomatedAgent with agent number " + agent_number + "was terminated early.");
        }
    }


}
