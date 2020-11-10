package hr.payment;

import hr.notifications.EmailSender;
import hr.persistence.EmployeeFileRepository;
import hr.personnel.Employee;
import hr.personnel.FullTimeEmployee;
import hr.personnel.Intern;
import hr.personnel.PartTimeEmployee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentProcessorTest {
   private EmployeeFileRepository employeeFileRepository;
   private EmailSender employeeNotifier;

   @BeforeAll
   void beforeAll() {
      List<Employee> employees = Arrays.asList(
            new FullTimeEmployee("McBlobster Greedy", 1000),
            new PartTimeEmployee("Blob Blab", 500),
            new Intern("Deddy Dudat", 200, 10));

      employeeNotifier = mock(EmailSender.class);
      employeeFileRepository = mock(EmployeeFileRepository.class);
      when(employeeFileRepository.findAll()).thenReturn(employees);

   }

   @Test
   public void send_payments_should_pay_all_employee_salaries() {

      // arrange
      PaymentProcessor paymentProcessor = new PaymentProcessor(employeeFileRepository, employeeNotifier);

      // act
      int result = paymentProcessor.sendPayments();

      // assert
      assertEquals(1700, result);
   }
}