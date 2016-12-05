import java.util.Timer;

public class SchedulerMain extends visGraph2 {
    public static void main(String[] args) throws InterruptedException {
        Timer update = new Timer();
        Driver ProcData = new Driver();
        update.schedule(ProcData, 0, 10000);

        for (int i = 0; i <= 3; i++){
            System.out.println("Updating...");
            Thread.sleep(11000);
            if (i == 5){
                System.out.println("Stop Testing");
                System.exit(0);
            }
        }


    }
}
