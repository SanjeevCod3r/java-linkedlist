class Student {
    int rollNo;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Student head;

    public StudentList() {
        head = null;
    }

    public void addStudent(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
        } else {
            newStudent.next = head;
            head = newStudent;
        }
    }

    public void deleteStudent(int rollNo) {
        if (head == null) return;

        if (head.rollNo == rollNo) {
            head = head.next;
            return;
        }

        Student temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.rollNo == rollNo) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public Student searchStudent(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No students in the list.");
            return;
        }

        Student temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    public void updateGrade(int rollNo, String newGrade) {
        Student student = searchStudent(rollNo);
        if (student != null) {
            student.grade = newGrade;
        }
    }
}

// Example usage
public class StudentManagement {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        studentList.addStudent(1, "Alice", 20, "A");
        studentList.addStudent(2, "Bob", 21, "B");
        studentList.displayStudents();
        studentList.updateGrade(2, "A+");
        studentList.displayStudents();
        studentList.deleteStudent(1);
        studentList.displayStudents();
    }
}
