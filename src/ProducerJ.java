

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
public class ProducerJ implements Runnable {

    private int sizeOfJobs;
    private int numberOfJobs;
    private int delayBetweenJobs;
    private String producerName;

    public ProducerJ(int soj, int noj, int dbj, String pn) {
        sizeOfJobs = soj;
        numberOfJobs = noj;
        delayBetweenJobs = dbj;
        producerName = pn;
    }

    public void run() {
        PrinterJ pt = PrinterJ.printer;
        for (int i = 0; i < numberOfJobs; i++) {
            try {
                PrintJob pj = new PrintJob(producerName + "#" + String.valueOf(i), sizeOfJobs);
                pt.addJob(pj);
                System.out.println("P: Adding job '" + pj.getName() + "' to the queue");
            } catch (FullQueueException e) {
                e.getMessage();
            }
            try {
                Thread.sleep(delayBetweenJobs * 1000);
            } catch (InterruptedException ex) {
                //
            }
        }
    }
}
