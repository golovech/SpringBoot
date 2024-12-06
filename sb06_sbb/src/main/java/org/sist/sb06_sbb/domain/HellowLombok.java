package org.sist.sb06_sbb.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // @Data 말고 게터세터 주기. 스프링부트만..
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class HellowLombok {
	
	// @RequiredArgsConstructor + final 은 짝꿍이다. 어떤원리인지는 잘 모르겠음
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		//System.out.println("hello world!");
		
		HellowLombok hellowLombok = new HellowLombok("헬로",15);
		
		System.out.println(hellowLombok.getHello());
		System.out.println(hellowLombok.getLombok());
		
	}
	
}

/*
@Getter
@Setter // @Data 말고 게터세터 주기. 스프링부트만..
public class HellowLombok {
	
	private String hello;
	private int lombok;
	
	public static void main(String[] args) {
		//System.out.println("hello world!");
		
		HellowLombok hellowLombok = new HellowLombok();
		
		hellowLombok.setHello("hello"); // 변수주입
		hellowLombok.setLombok(5);
		
		System.out.println(hellowLombok.getHello());
		System.out.println(hellowLombok.getLombok());
		
	}
	
}
*/
