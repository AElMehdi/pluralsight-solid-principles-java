package hr.main;

import hr.logging.ConsoleLogger;
import hr.persistence.EmployeeRepository;
import hr.personnel.Employee;
import hr.serializers.EmployeeFileSerializer;

import java.io.IOException;
import java.util.List;

public class SaveEmployeesMain {
    public static void main(String[] args) {
        // Grab employees
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);
        List<Employee> employees = repository.findAll();

        // Save all
        for (Employee e : employees){
            try {
                repository.save(e);
                consoleLogger.logInfo(e.toString());
            } catch (IOException ioException) {
                consoleLogger.logError(ioException);
            }
        }
    }
}
