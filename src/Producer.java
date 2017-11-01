

public class Producer implements Runnable{
	private int sizeOfJobs;
	private int numberOfJobs;
	private int delayBetweenJobs;
	private String producerName;
	private Printer printer;
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		
		for(int i = 0;i<numberOfJobs;i++){                            //��ÿ������������Ӷ����ӡ����
			String name = producerName+"#"+(i+1);
			PrintJob p = new PrintJob(name, sizeOfJobs);
			System.out.println("P: Adding job '"+name+"' to the queue");
			printer.addJob(p);
			printer.push();
			try {
			Thread.sleep(delayBetweenJobs);
			} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}
		
	}
	public Producer(int sizeOfJobs,int numberOfJobs,int delayBetweenJobs,String producerName,Printer printer){  //��ʼ������ֵ
		this.sizeOfJobs = sizeOfJobs;
		this.numberOfJobs = numberOfJobs;
		this.delayBetweenJobs = delayBetweenJobs;
		this.producerName = producerName;
		this.printer = printer;
	}
}
