import java.util.concurrent.locks.Lock;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

/**
 * Created by Alexander on 10/24/16.
 * @author Alexander Chatron-Michaud
 * This class is to run a manual booking agent on a given airplane. This class is made to run in a thread with a GUI.
 */
public class ManualAgent extends JFrame implements Runnable {

    private int agent_number;
    private Airplane my_airplane;
    private Lock currently_booking;


    /**
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

    /**
     * Runs the GUI connected thread for manual booking
     */
    public void run() {
        try {

            while (my_airplane.seats_left()) {
                currently_booking.lock(); //critical section start
                draw_gui();
                currently_booking.unlock(); //critical section end
                Thread.sleep(1000);
            }
            dispose();
            System.out.println("All seats filled, application finished and exiting...");
        }
        catch (InterruptedException exception) {
            System.out.println("ManualAgent with agent number " + agent_number + "was terminated early.");
        }

    }

    /**
     * Draws the GUI and handles click events so that seats can be booked. Locks before attempting booking to prevent race conditions.
     */
    public void draw_gui() {
        try {
            String[] columns = new String[]{
                    "Seat 0", "Seat 1", "Seat 2", "Seat 3"
            };
            Object[][] data = my_airplane.show_current_seats();
            JTable table = new JTable(data, columns);
            table.setPreferredScrollableViewportSize(new Dimension(1000,800));
            this.add(new JScrollPane(table));
            this.setTitle("Airplane Booking");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        my_airplane.set_if_free(agent_number, row, col);
                    }
                }
            });
            Thread.sleep(1000);
        }
        catch (InterruptedException exception) {
            System.out.println("ManualAgent with agent number " + agent_number + "was terminated early.");
        }
    }
}
