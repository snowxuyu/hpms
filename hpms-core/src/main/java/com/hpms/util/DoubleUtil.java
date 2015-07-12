package com.hpms.util;

public class DoubleUtil {

	public static double parseLower(Double d) {
		if (d == null || "".equals(d)) {
			return 0;
		} else {
			return d;
		}
	}
}
