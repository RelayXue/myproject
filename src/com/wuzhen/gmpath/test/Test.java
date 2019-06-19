package com.wuzhen.gmpath.test;

import java.util.Date;

import com.gh.common.DateUtil;

public class Test {
	public static void main(String[] args) {
		for(int i=1;i<=100;i++){
			int j=(int)(Math.random()*(300-i));
			if(j<5){
				System.out.println("中奖");
			}else{
				System.out.println("没中奖");
			}
		}
	}
}
