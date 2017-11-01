

public class Printer implements Runnable{
	private Queue printQueue ;
	private boolean stateIsRunning;
	public Printer(){
		printQueue  = new CircularQueue(6);            //生成新队列
		stateIsRunning = true;                         //循环条件设置为true
		System.out.println("  C: Print manager is starting up.");
	}
	public synchronized void halt(){
		stateIsRunning = false;                     //终止循环
	}
	public synchronized void addJob(PrintJob job) throws FullQueueException{
		printQueue.addBack(job);
	}
	private synchronized PrintJob getJob() throws EmptyQueueException{             //获得最前方的job
		PrintJob job = (PrintJob) printQueue.getFront();
		return job;
		
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		
		while(stateIsRunning){
			synchronized(this){
			this.pop();
			if(!printQueue.isEmpty()){                      //线程结束时退出循环   		
				PrintJob tmpJob = getJob();
    			System.out.println("  C: Starting job '" + tmpJob.getName() + "'");       //显示开始、结束工作状态
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
	
	public synchronized void pop(){                                                     //判断队列是否为空,若为空等待输入
		if(printQueue.isEmpty()||(printQueue.getCapacity()==0)){
			try {
				System.out.println("  C: Waiting on a job to print.");
				this.wait();
				
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.getStackTrace();
			}
		}
	}
	public synchronized void push(){                                                   //唤醒线程
		this.notify();
	}
}
