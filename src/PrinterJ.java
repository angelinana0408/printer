


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
public class PrinterJ implements Runnable {

    private CircularQueue printQueue;
    private boolean stateIsRunning;
    public static PrinterJ printer = new PrinterJ();

    public PrinterJ() {
        printQueue = new CircularQueue(10);
        stateIsRunning = true;
    }

    public void halt() {
        stateIsRunning = false;
        synchronized (this) {
            this.notifyAll();
        }
    }

    public synchronized void addJob(PrintJob job) throws FullQueueException {
        printQueue.addBack(job);
        
        this.notifyAll();
        
    }

    private synchronized PrintJob getJob() throws EmptyQueueException {
        
    	this.notifyAll();
        
        return (PrintJob) printQueue.getFront();
    }

    public void run() {
        System.out.println("  C: Print manager is starting up.");
        while (stateIsRunning) {
        	if(printQueue.isEmpty()||(printQueue.getCapacity()==0)){
    			try {
    				System.out.println("  C: Waiting on a job to print.");
    				this.wait();
    				
    			} catch (InterruptedException e) {
    				// TODO 自动生成的 catch 块
    				e.getStackTrace();
    			}
    		}
        	else{
        		
        			PrintJob tmpJob = getJob();
        			System.out.println("  C: Starting job '" + tmpJob.getName() + "'");
        			for (int i = 0; i < tmpJob.getPages(); i++) {
        				try {
        					Thread.sleep(500);
        				} catch (InterruptedException ex) {
                        
        				}
        			}
        			System.out.println("  C: Completed job '" + tmpJob.getName() + "'");
        			printQueue.removeFront();
        	}
        }
    }
}
