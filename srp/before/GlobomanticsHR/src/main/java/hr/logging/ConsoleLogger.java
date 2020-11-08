package hr.logging;

public class ConsoleLogger {


   public void logInfo(String message) {
      System.out.println("Saved employee " + message);
   }

   public void logError(Exception e) {
      System.out.println("ERROR: Could not save employee. " + e);
   }
}
