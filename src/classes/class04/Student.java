package classes.class04;

import java.util.HashSet;

public class Student {

    public int id;
    public String name;

    public static int used = 0;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int hashCode() {
        System.out.println("used " + used++);
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return id + " " + name;
    }


    public boolean equals(Object o) {
        return name.equalsIgnoreCase(((Student)o).name) && id == ((Student)o).id;
    }

    public static void main(String[] args) {
        HashSet<Student> hs = new HashSet<>();

        Student s1 = new Student(5, "Bob");
        Student s2 = new Student(1, "Tom");
        Student s3 = new Student(3, "Mike");

        hs.add(s1);
        hs.add(s2);
        hs.add(s3);

        System.out.println(hs.toString());
        System.out.println(hs.contains(new Student(1, "Tom")));
    }
}
