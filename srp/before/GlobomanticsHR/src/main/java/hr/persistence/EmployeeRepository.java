package hr.persistence;

import hr.personnel.Employee;
import hr.personnel.FullTimeEmployee;
import hr.personnel.PartTimeEmployee;
import hr.serializers.EmployeeFileSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
Helper method to perform CRUD operations on employees. In a production
application we could use the database for persistence. In this demo,
we are storing employees in the file system.
 */

public class EmployeeRepository {

   private EmployeeFileSerializer employeeFileSerializer;

   public EmployeeRepository(EmployeeFileSerializer employeeFileSerializer) {
      this.employeeFileSerializer = employeeFileSerializer;
   }

   public void save(Employee employee) throws IOException {
      StringBuilder sb = employeeFileSerializer.serialize(employee);

      Path path = Paths.get(employee.getFullName()
            .replace(" ", "_") + ".rec");
      Files.write(path, sb.toString().getBytes());
   }

   public List<Employee> findAll() {

      // Employees are kept in memory for simplicity
      Employee anna = new FullTimeEmployee("Anna Smith", 2000);
      Employee billy = new FullTimeEmployee("Billy Leech", 920);

      Employee steve = new PartTimeEmployee("Steve Jones", 800);
      Employee magda = new PartTimeEmployee("Magda Iovan", 920);

      return Arrays.asList(anna, billy, steve, magda);
   }
}
