/**
 * Created by Alexander on 10/24/16.
 * @author Alexander Chatron-Michaud
 * This class is used to manage an airplane with 200 seats, arranged in rows of 4
 */
import java.util.*;

public class Airplane {

    private int[][] seatingArray;
    private int seats_left;

    /**
     * Constructs an Airplane Object
     */
    public Airplane() {
        this.seatingArray = new int[50][4];
        this.seats_left = 200;
    }

    /**
     * This method checks if there are seats left in the plane.
     * @return true if there are seats left, otherwise false
     */
    public boolean seats_left() {
        return (seats_left > 0);
    }

    /**
     * This method checks if a seat is free, and if it is, sets the seat as taken by the agent's number.
     * Precondition: row and number are in the right range
     * @param agent the agent trying to book the seat, whose number will fill that seat if taking it
     * @param row the row number of the seat being booked/checked
     * @param number the seat number (0-3) within the row
     * @return true if seat was successfully booked, false if seat was already taken
     */
    public boolean set_if_free(int agent, int row, int number) {
        if (row < 0 || row > 49 || number < 0 || number > 3) {
            System.out.println("Not a valid seat on the airplane. Please try again.");
            return false;
        }
        if (this.seatingArray[row][number] != 0) {
            System.out.println("Agent " + agent + " failed to book row " + row + " seat " + number);
            return false;
        }
        else {
            this.seatingArray[row][number] = agent;
            this.seats_left -= 1;
            System.out.println("Agent " + agent + " just booked row " + row + " seat " + number);
            return true;
        }
    }

    /**
     * This method shows the current seating arrangement for the purpose of a GUI.
     * @return a clone of the airplanes seating arrangements (to protect the internal reference)
     */
    public Object[][] show_current_seats() {
        Object[][] objectList = new Object[50][4];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.seatingArray[i][j] == 0) {
                    objectList[i][j] = "_";
                }
                else {
                    objectList[i][j] = this.seatingArray[i][j];
                }
            }
        }
        return objectList;
    }

}
