package hr.main;

import hr.notifications.EmailSender;
import hr.payment.PaymentProcessor;
import hr.persistence.EmployeeFileRepository;
import hr.persistence.EmployeeFileSerializer;

public class PayEmployeesMain {

    /*
    Will take a couple of seconds to execute due to the
    sending of mails.
     */

    public static void main(String[] args) {
        EmployeeFileRepository employeeRepository = new EmployeeFileRepository(new EmployeeFileSerializer());
        EmailSender emailNotifier = new EmailSender();
        PaymentProcessor paymentProcessor = new PaymentProcessor(employeeRepository, emailNotifier);
        int totalPayments = paymentProcessor.sendPayments();
        System.out.println("Total payments " + totalPayments);
    }
}
