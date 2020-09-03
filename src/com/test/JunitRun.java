package com.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JunitRun {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitClass.class);
		
		for(Failure failure: result.getFailures()) {
			System.out.println(failure.toString());  //print results that failed
			
		}
		System.out.println("Setup Result == "+result.wasSuccessful());
	}

}
