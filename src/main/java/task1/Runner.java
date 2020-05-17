package task1;

import task1.entities.Employee;
import task1.entities.Position;
import task1.entities.Salary;

import java.io.*;

public class Runner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Salary salary = new Salary(24.6);
        Position position = new Position("programmer", salary);
        Employee employee1 = new Employee("Bob", position, 23);
        Employee employee2 = new Employee("David", position, 36);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("employee.txt"));
        objectOutputStream.writeObject(employee1);
        objectOutputStream.writeObject(employee2);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("employee.txt"));
        Employee employeeRestored1 = (Employee) objectInputStream.readObject();
        Employee employeeRestored2 = (Employee) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("Before Serialize: " + "\n" + employee1 + "\n" + employee2);
        System.out.println("After Restored: " + "\n" + employeeRestored1 + "\n" + employeeRestored2);

    }
}
