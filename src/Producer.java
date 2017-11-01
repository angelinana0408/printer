

public class Producer implements Runnable{
	private int sizeOfJobs;
	private int numberOfJobs;
	private int delayBetweenJobs;
	private String producerName;
	private Printer printer;
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		
		for(int i = 0;i<numberOfJobs;i++){                            //向每个生产者中添加多个打印任务
			String name = producerName+"#"+(i+1);
			PrintJob p = new PrintJob(name, sizeOfJobs);
			System.out.println("P: Adding job '"+name+"' to the queue");
			printer.addJob(p);
			printer.push();
			try {
			Thread.sleep(delayBetweenJobs);
			} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
	}
	public Producer(int sizeOfJobs,int numberOfJobs,int delayBetweenJobs,String producerName,Printer printer){  //初始化属性值
		this.sizeOfJobs = sizeOfJobs;
		this.numberOfJobs = numberOfJobs;
		this.delayBetweenJobs = delayBetweenJobs;
		this.producerName = producerName;
		this.printer = printer;
	}
}
