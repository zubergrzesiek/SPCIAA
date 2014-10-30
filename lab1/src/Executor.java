import java.util.ArrayList;

/**
 * @(#)Executor.java
 *
 *
 * @author
 * @version 1.00 2011/6/13
 */

class Executor extends Thread {
 public synchronized void run() {
	try{
	 int k = 4;
	 
	Counter counter = new Counter(this);
	Vertex S = new Vertex(null,null,null,"S");
	P1 p1 = new P1(S,counter);
	p1.start();
	counter.release();
	
	int max = (int) Math.pow(2, k);
	
	P2 p2a = new P2(p1.m_vertex.m_left,counter);
	P2 p2b = new P2(p1.m_vertex.m_right,counter);
	p2a.start();
	p2b.start();
	counter.release();
	
	ArrayList<P2> list = new ArrayList<P2>();
	
	ArrayList<P2> tempList = new ArrayList<P2>();
	
	tempList.add(p2a);
	tempList.add(p2b);
	
	for (int i = 0 ; i < k - 1 ; i++){
		list = (ArrayList<P2>) tempList.clone();
		System.out.println("Rozmiar listy "+ list.size());
		tempList.clear();
		int licznik = 0;
		for (P2 e: list){
			System.out.println("Iterator1 "+ licznik);
			P2 p2 = new P2(e.m_vertex.m_left,counter);
			tempList.add(p2);
			p2.start();
			p2 = new P2(e.m_vertex.m_right,counter);
			tempList.add(p2);
			p2.start();
			
			/*
			Runtime runtime = Runtime.getRuntime();
			runtime.freeMemory();
			*/
			licznik++;
		}
		
		counter.release();
		
	}
	
	int i = 0;
	for (P2 e : tempList){
		System.out.println("Wierzcholek P3 "+ i);
		i++;
		P3 p3 = new P3(e.m_vertex.m_left,counter);
		p3.start();
		p3 = new P3(e.m_vertex.m_right,counter);
		p3.start();
		
	}
	
	System.out.println("Ilosc wierzcholkow "+i);
	
	//counter.release();
	} catch (Exception e){
		
	}
	
 
 }
}

