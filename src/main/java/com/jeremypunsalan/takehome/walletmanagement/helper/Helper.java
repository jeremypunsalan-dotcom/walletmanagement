package com.jeremypunsalan.takehome.walletmanagement.helper;

public class Helper {

	public static boolean isDoubleNegative(Double d) {
		return Double.doubleToRawLongBits(d) < 0;
	}
	
}
