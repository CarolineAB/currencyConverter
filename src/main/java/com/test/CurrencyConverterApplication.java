package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class CurrencyConverterApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CurrencyConverterApplication.class, args);
		System.out.println("Service is running...");
		Map<String, BigDecimal> init = init();
		Map<String, BigDecimal> dbr = dbr();
		System.out.println("Please input data which like 'CNY 100',Case sensitive.");
		ConsoleListener cs = new ConsoleListener(new Scanner(System.in), new ConsoleListener.Action() {

            public void act(String msg) {
            	try {
            		if(!StringUtils.isEmpty(msg)){
            		String[] split = msg.split(" ");
            		String k = split[0];//key
            		if(k.length()!=3){// uppercase 3 letter ISO code
            			System.out.println(" The format of data is not right !");
            		}else{
            			if(split.length==2){
            				String num = split[1];
            				BigDecimal bv = new BigDecimal(num);//value
            				if(init.containsKey(k)){
            					BigDecimal initv = init.get(k);
            					BigDecimal add = initv.add(bv);
            					if(!add.equals(0)){
            						init.replace(k, add);
            					}else{
            						init.remove(k);
            					}
            				}else if(dbr.containsKey(k)){
            					if(!bv.equals(0)){
            						init.put(k, bv);
            					}
            				}else{
            					System.out.println("The Currency is not recorded !");
            				}
            			}else{
            				System.out.println("The format of data is not right !");
            			}
            		}
            		
            		}
				} catch (Exception e) {
					System.out.println("ERROR!,The services is stoped!");
					System.exit(0);
//					e.printStackTrace();
				}
//                System.out.println("" + msg);
            }
            
        });
		 cs.addAction("quit", new ConsoleListener.Action() {

	            public void act(String msg) {
	                System.out.println("The services is quit!");
	                System.exit(0);
	            }
	        });
		 cs.listenInNewThread();
		  TimeoutThread timeoutThread = new TimeoutThread(1000*60,init,dbr);
		 
	        while (true) {
	            try {
	                Thread.sleep(1);
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	}
	
	
	public static Map<String, BigDecimal>  init() throws IOException{
		Map<String, BigDecimal> map = new HashMap<>();
		  try {
	            //通过传入File实例化Scanner类
	            Scanner sc = new Scanner(new File("1.txt"));
	            System.out.println("get init data");
	            //按行读取test.txt文件内容
	            while (sc.hasNextLine()) {
	            	String nextLine = sc.nextLine();
	                System.out.println(nextLine);
	                String[] split = nextLine.split(" ");
	                map.put(split[0], new BigDecimal(split[1]));
	            }
	        } catch (FileNotFoundException e) {
	        }
		return map;
	}
	public static Map<String, BigDecimal>  dbr() throws IOException{
		Map<String, BigDecimal> map = new HashMap<>();
		  try {
	            Scanner scanner = new Scanner(new File("data.txt"));
	            while (scanner.hasNextLine()) {
	            	String nextLine = scanner.nextLine();
//	                System.out.println(nextLine);
	                String[] split = nextLine.split(" ");
	                map.put(split[0], new BigDecimal(split[1]));
	            }
	        } catch (FileNotFoundException e) {
	        }
		return map;
	}
	
}
