/** 
 * Class for a student with an age, name, and student ID.
 * @author Alexander Chatron-Michaud
 */
public class Student {

	private int age;
	private String name;
	private int id;

	public Student(int init_age, String init_name, int init_id) {
		if ((init_id > 99999) || (init_id < 10000) || (init_age < 5)) {
			System.out.println("Error: Student ID is not 5 digits or student is under 5 years old");
			throw new IllegalStateException();
		}
		age = init_age;
		name = init_name;
		id = init_id;
	}

	/** 
	 *Returns the ID of the student
	 *@return Integer form ID of the student
	 */
	public int get_id() {
		return this.id;
	}

	/** 
	 *Returns the age of the student
	 *@return Integer form age of the student
	 */
	public int get_age() {
		return this.age;
	}

}