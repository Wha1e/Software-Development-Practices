import java.util.*;

/** 
 * Class to manage a lineup of students entering a gym party
 * @author Alexander Chatron-Michaud
 */
public class Queue {

	private LinkedList<Student> line;

	public Queue() {
		line = new LinkedList<Student>();
	}

	/** 
	 *Adds a student to the line if they are a valid student
	 *@param a    the student to be added
	 */
	public void enqueue(Student a) {
		if (isValid(a)) {
			line.addLast(a);
		}
	}

	/** 
     * If there is a student in line, remove the first student in line
     * so they can enter the party.
     * @return the student to enter the party, or null if line is empty
	 */
	public Student dequeue() {
		if (line.size() == 0) {
			System.out.println("Error: No students in queue to dequeue");
			throw new IllegalStateException();
		}
		return line.removeFirst();
	}

	/** 
	 *Checks if a student is valid to enter the party. 
	 *A student is valid if their 5 digit 
	 *ID begins with 22 and they are at least 15 years old.
	 *@param a     the student in question
	 *@return True if the student is valid to enter party, False is the student is not valid to enter party
	 */
	public boolean isValid(Student a) {
		if ((a.get_age() >= 15) && (a.get_id() >= 22000) && (a.get_id() < 23000)){
			return true;
		}
		else {
			return false;
		}
	}
	
}