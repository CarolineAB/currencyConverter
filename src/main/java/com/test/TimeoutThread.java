package com.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TimeoutThread implements Runnable{

	private long timeout;
//	private boolean isCancel;
	
	public Map<String, BigDecimal> init;
	public Map<String, BigDecimal> dbr;
//	public Map<String, String> map;
	private Thread th;
	
	public  TimeoutThread(long timeout,Map<String, BigDecimal> init,Map<String, BigDecimal> dbr) {
		super();
		th =  new Thread(this);
		this.timeout = timeout;
		this.init = init;
		this.dbr = dbr;
		th.start();
	}

	public Map<String, String> tochange(Map<String, BigDecimal> init ,Map<String, BigDecimal> dbr){
		Map<String, String> map = new HashMap<>();
		for (String k : init.keySet()) {
			BigDecimal initv = init.get(k);//init value
			BigDecimal huilv = dbr.get(k);
			BigDecimal v = initv.divide(huilv, 2, BigDecimal.ROUND_HALF_EVEN);
			String string = initv.toString();
			String string2 = v.toString();
			map.put(k,string+"(USD "+string2+")" );
		}
		return map;
	}
	@Override
	public synchronized void run() {
		try {
			  while (true) {
				  Thread.sleep(timeout);
				  Map<String, String> map = tochange(init,dbr);
				  System.out.println("Print every minute");
				  for (String k : map.keySet()) {
					System.out.println(k+" "+map.get(k));
				}
			  }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
