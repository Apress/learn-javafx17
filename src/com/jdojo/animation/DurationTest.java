// DurationTest.java
package com.jdojo.animation;

import javafx.util.Duration;

public class DurationTest {
	public static void main(String[] args) {
		Duration d1 = Duration.seconds(30.0);
		Duration d2 = Duration.minutes(1.5);
		Duration d3 = Duration.valueOf("35.25ms");
		System.out.println("d1  = " + d1);
		System.out.println("d2  = " + d2);
		System.out.println("d3  = " + d3);
		
		System.out.println("d1.toMillis() = " + d1.toMillis());
		System.out.println("d1.toSeconds() = " + d1.toSeconds());
		System.out.println("d1.toMinutes() = " + d1.toMinutes());
	 	System.out.println("d1.toHours() = " + d1.toHours());
		
		System.out.println("Negation of d1  = " + d1.negate());
		System.out.println("d1 + d2 = " + d1.add(d2));
		System.out.println("d1 / 2.0 = " + d1.divide(2.0));
	
		Duration inf = Duration.millis(1.0/0.0);
		Duration unknown = Duration.millis(0.0/0.0);
		System.out.println("inf.isIndefinite() = " + inf.isIndefinite());
		System.out.println("unknown.isUnknown() = " + unknown.isUnknown());
	}
}
