package hr.payment;

import hr.notifications.EmailSender;
import hr.persistence.EmployeeRepository;
import hr.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeRepository employeeRepository;
    private EmailSender emailNotifier;

    public PaymentProcessor(EmployeeRepository employeeRepository, EmailSender emailNotifier){
        this.employeeRepository = employeeRepository;
        this.emailNotifier = emailNotifier;
    }

    public int sendPayments(){
        List<Employee> employees = this.employeeRepository.findAll();
        int totalPayments = 0;

        for(Employee employee : employees){
            totalPayments += employee.getMonthlyIncome();
            emailNotifier.notify(employee);
        }

        return totalPayments;
    }
}
