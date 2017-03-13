package com.nts.bigdata.comm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * <pre>
 * com.nts.bigdata.comm
 * CommonUtilities.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 9.
 */
public class CommonUtilities {
	
//	private static final Character[] charArr;
	private static final char[] charArr;
	
	static {
		
		StringBuilder sb = new StringBuilder();
		for(char ch = '0'; ch <= '9'; ch++) {
			
			sb.append(ch);
		
		}
		
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			
			sb.append(ch);
		
		}
		
		charArr = sb.toString().toCharArray();
		/*charArr = sb.toString().chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		
		Collections.shuffle(Arrays.asList(charArr));*/
	
	}
	
	public static String getCurrentDateString(String fmt, Locale loc) {
		
		return new SimpleDateFormat(fmt, loc).format(new Date());
	
	}
	
	public static String makeApMacAddr(int length) {
		
		Random random = new Random();
		char[] randomCharArr = new char[length];
		
		for(int i = 0; i < length; i++) {
			
			if((i + 1) % 3 == 0) {
				
				randomCharArr[i] = ':';
			
			} else {
				
				randomCharArr[i] = charArr[random.nextInt(charArr.length)];
			
			}
		
		}
		
		return new String(randomCharArr);
	
    }
	
	public static int makeNegativeNum(int min, int max) {
		
		return (new Random().nextInt(max + 1 - min) + min) * -1;
	
	}

}
