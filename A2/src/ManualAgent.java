import java.util.concurrent.locks.Lock;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

/**
 * Created by Alexander on 10/24/16.
 * This class is to run a manual booking agent on a given airplane. This class is made to run in a thread with a GUI.
 */
public class ManualAgent extends JFrame implements Runnable {

    private int agent_number;
    private Airplane my_airplane;
    private Lock currently_booking;

    /*
     * Constructs a manually controlled agent
     * @param init_agent_number the integer number this agent will use for booking/marking seats
     * @param init_my_airplane the airplane the agent will be booking seats in
     * @param init_currently_booking the lock/monitor used to check if another thread is trying to book something at the same time
     */
    public ManualAgent(int init_agent_number, Airplane init_my_airplane, Lock init_currently_booking) {
        this.agent_number = init_agent_number;
        this.my_airplane = init_my_airplane;
        this.currently_booking = init_currently_booking;
    }

    public void run() {
        try {

            String[] columns = new String[] {
                    "Seat 0", "Seat 1", "Seat 2", "Seat 3"
            };
            Object[][] data = my_airplane.show_current_seats();
            JTable table = new JTable(data, columns);
            this.add(new JScrollPane(table));
            this.setTitle("Airplane Booking");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);

            while (my_airplane.seats_left()) {
                currently_booking.lock(); //critical section start
                draw_gui();
                //my_airplane.set_if_free(agent_number, row, seat);
                currently_booking.unlock(); //critical section end
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException exception) {
            System.out.println("ManualAgent with agent number " + agent_number + "was terminated early.");
        }

    }  

}
