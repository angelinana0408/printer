
public class TestPrinter {
	public static void main(String[] args) throws InterruptedException{
		Printer p = new Printer();
		Thread printer = new Thread(p);                //ʵ����һ����ӡ�̲߳��������ȼ�Ϊ���
		printer.setPriority(printer.MAX_PRIORITY);
		Producer one = new Producer(5, 5, 2000, "Fred",p);     //ʵ���������������߳�
		Producer two = new Producer(10, 3, 4000, "Elizabeth",p);
		Producer three = new Producer(25, 3, 10000, "Simon",p);
		Thread p1 = new Thread(one);
		Thread p2 = new Thread(two);
		Thread p3 = new Thread(three);
		printer.start();                                      //����ȫ���߳�
		
		p1.start();
		p2.start();
		p3.start();
		
			
		p1.join();                                             //�ȴ������������߳̽����������ӡ�߳�
		p2.join();
		p3.join();
		p.halt();
		p.push();
		System.out.print("  C: Print manager is halted.");
		
		
	}
}
