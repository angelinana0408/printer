

public class Printer implements Runnable{
	private Queue printQueue ;
	private boolean stateIsRunning;
	public Printer(){
		printQueue  = new CircularQueue(6);            //�����¶���
		stateIsRunning = true;                         //ѭ����������Ϊtrue
		System.out.println("  C: Print manager is starting up.");
	}
	public synchronized void halt(){
		stateIsRunning = false;                     //��ֹѭ��
	}
	public synchronized void addJob(PrintJob job) throws FullQueueException{
		printQueue.addBack(job);
	}
	private synchronized PrintJob getJob() throws EmptyQueueException{             //�����ǰ����job
		PrintJob job = (PrintJob) printQueue.getFront();
		return job;
		
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		
		while(stateIsRunning){
			synchronized(this){
			this.pop();
			if(!printQueue.isEmpty()){                      //�߳̽���ʱ�˳�ѭ��   		
				PrintJob tmpJob = getJob();
    			System.out.println("  C: Starting job '" + tmpJob.getName() + "'");       //��ʾ��ʼ����������״̬
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
	
	public synchronized void pop(){                                                     //�ж϶����Ƿ�Ϊ��,��Ϊ�յȴ�����
		if(printQueue.isEmpty()||(printQueue.getCapacity()==0)){
			try {
				System.out.println("  C: Waiting on a job to print.");
				this.wait();
				
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.getStackTrace();
			}
		}
	}
	public synchronized void push(){                                                   //�����߳�
		this.notify();
	}
}
