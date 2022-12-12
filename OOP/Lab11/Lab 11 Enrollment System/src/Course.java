// Thanyanit Jongjitragan 6188075
public class Course {
	String code, name, description;
	int credit, max_seat, semister, year;
	Course[] prereq;
	double fee;
	boolean offering_status;
	Lecturer lecturer;
	Student[][] student;
	
	public Course(String code, String name, int credit, String description, Course[] requireCourses, double fee, int max_seat, int semister, int academic_year, Lecturer lecturer)
	{
		this.code = code;
		this.name = name;
		this.credit = credit;
		this.description = description;
		prereq = requireCourses;
		this.fee = fee;
		this.max_seat = max_seat;
		this.semister = semister;
		year = academic_year;
		offering_status = false;
		this.lecturer = lecturer;
		student = new Student[max_seat][2];
	}
}
