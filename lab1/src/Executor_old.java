/**
 * @(#)Executor.java
 *
 *
 * @author
 * @version 1.00 2011/6/13
 */

class Executor_old extends Thread {
 public synchronized void run() {
	int k = 4;
	 
	Counter counter = new Counter(this);
	Vertex S = new Vertex(null,null,null,"S");
	P1 p1 = new P1(S,counter);
	p1.start();
	counter.release();
	
	/*
	P2 p2a = new P2(p1.m_vertex.m_left,counter);
	P2 p2b = new P2(p1.m_vertex.m_right,counter);
	p2a.start();
	p2b.start();
	counter.release();
	*/
	
	int max = (int) Math.pow(2, k);
	
	P2 p2a = new P2(p1.m_vertex.m_left,counter);
	P2 p2b = new P2(p1.m_vertex.m_right,counter);
	p2a.start();
	p2b.start();
	counter.release();
	
	
	P2 p21;// = p2a;
	P2 p22;// = p2b;
	
	P2 p21p = p2a;
	P2 p22p = p2b;
	
	
	for (int i = 0 ; i < k - 1 ; i++ ){
		P2 p2_1 = new P2(p21p.m_vertex.m_left,counter);
		P2 p2_2 = new P2(p21p.m_vertex.m_right,counter);
		p2_1.start();
		p2_2.start();
		p21p = p2_1;
		p22p = p2_2;
		
		counter.release();
		
	}
	
	for (int i = 0 ; i < k - 1 ; i++ ){
		//new P3()
	}
	
	/*
	P2 p2c = new P2(p2a.m_vertex.m_left,counter);
	P2 p2d = new P2(p2a.m_vertex.m_right,counter);
	P3 p3a = new P3(p2b.m_vertex.m_left,counter);
	P3 p3b = new P3(p2b.m_vertex.m_right,counter);
	p2c.start();
	p2d.start();
	p3a.start();
	p3b.start();
	counter.release();
	*/
	//
	/*
	P3 p3c = new P3(p2c.m_vertex.m_left,counter);
	P3 p3d = new P3(p2c.m_vertex.m_right,counter);
	P3 p3e = new P3(p2d.m_vertex.m_left,counter);
	P3 p3f = new P3(p2d.m_vertex.m_right,counter);
	
	p3c.start();
	p3d.start();
	p3e.start();
	 p3f.start();
	 }
	 */
}
}

