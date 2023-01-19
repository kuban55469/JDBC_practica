package peaksoft;

import peaksoft.enums.Gender;
import peaksoft.models.Student;
import peaksoft.services.StudentService;
import peaksoft.services.StudentServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();

        while (true) {
            System.out.println("""
                    1- create table
                    2- save students
                    3- find by student id
                    4- find all students
                    5- update student
                    6- delete by student id
                    7- sort students by age
                    8- check by age
                    9- delete all students
                    """);
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> System.out.println(studentService.crateTable());
                case 2 -> {
                    System.out.print("Enter name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("Enter age: ");
                    byte age = new Scanner(System.in).nextByte();
                    System.out.println(studentService.saveStudents(new Student(name, age)));
                }
                case 3 -> System.out.println(studentService.findByStudentId(4L));
                case 4 -> studentService.findAllStudents().forEach(System.out::println);
                case 5 -> studentService.updateStudent(3L, new Student("Yson", (byte) 50));
                case 6 -> System.out.println(studentService.deleteByStudentId(3L));
                case 7 -> {
                    System.out.print("Enter asc or desc: ");
                    String ascOrDesc = new Scanner(System.in).nextLine();
                    studentService.getAllStudentsSortByAge(ascOrDesc).forEach(System.out::println);
                }
                case 8 -> System.out.println(studentService.checkByAge());
                case 9 -> studentService.deleteAllStudents();
                default -> System.out.println("Error!!");
            }
        }
    }
}
