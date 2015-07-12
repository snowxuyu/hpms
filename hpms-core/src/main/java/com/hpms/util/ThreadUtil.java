package com.hpms.util;

//public class ThreadUtil implements Runnable{
public class ThreadUtil extends Thread{

	public void run(){
	 for(int i=0;i<60;i++){
         System.out.println(Thread.currentThread().getName()+i);
     }
	}
}
