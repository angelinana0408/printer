

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
public class TestPrinterJ {

    public static void main(String[] args) {

        ProducerJ p1 = new ProducerJ(5, 2, 2, "Fred");
        ProducerJ p2 = new ProducerJ(15, 3, 6, "Simon");
        ProducerJ p3 = new ProducerJ(25, 4, 10, "Elizabeth");

        PrinterJ pm = PrinterJ.printer;
        Thread tpm = new Thread(pm);
        tpm.setPriority(Thread.MAX_PRIORITY);
        tpm.start();

        Thread tp1 = new Thread(p1);
        Thread tp2 = new Thread(p2);
        Thread tp3 = new Thread(p3);

        tp1.start();
        tp2.start();
        tp3.start();
        try {
            tp1.join();
        } catch (InterruptedException ex) {
            //
        }
        try {
            tp2.join();
        } catch (InterruptedException ex) {
            //
        }
        try {
            tp3.join();
        } catch (InterruptedException ex) {
            //
        }
        pm.halt();
    }
}
