
public class TestPrinter {
	public static void main(String[] args) throws InterruptedException{
		Printer p = new Printer();
		Thread printer = new Thread(p);                //实例化一个打印线程并设置优先级为最高
		printer.setPriority(printer.MAX_PRIORITY);
		Producer one = new Producer(5, 5, 2000, "Fred",p);     //实例化三个生产者线程
		Producer two = new Producer(10, 3, 4000, "Elizabeth",p);
		Producer three = new Producer(25, 3, 10000, "Simon",p);
		Thread p1 = new Thread(one);
		Thread p2 = new Thread(two);
		Thread p3 = new Thread(three);
		printer.start();                                      //启动全部线程
		
		p1.start();
		p2.start();
		p3.start();
		
			
		p1.join();                                             //等待三个生产者线程结束后结束打印线程
		p2.join();
		p3.join();
		p.halt();
		p.push();
		System.out.print("  C: Print manager is halted.");
		
		
	}
}
