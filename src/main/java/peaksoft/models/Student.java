package peaksoft.models;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 19.01.2023
 */
public class Student {
    private Long id;
    private String name;
    private Byte age;

    public Student() {
    }

    public Student( String name, Byte age) {
        this.name = name;
        this.age = age;
    }

    public Student(Long id, String name, Byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
