package mini;

/**
 * Utility class with static methods for loop practice.
 */
public class LoopsInfinityAndBeyond {

	/**
	 * Private constructor to disable instantiation.
	 */
	private LoopsInfinityAndBeyond() {
	}

	/**
	 * Define a flying saucer as the following string pattern: one ‘(‘, followed by
	 * zero to many ‘=’, followed by one ‘)’. Write a Java method that, given a
	 * string find the first instance of a flying saucer (starting from the left)
	 * and return its length. If no flying saucer exists return 0.
	 * <p>
	 * For example: Given: "(==)" Return: 4
	 * <p>
	 * Given: "***()**(===)" Return: 2
	 * <p>
	 * Given: "****(***)" Return: 0
	 * 
	 * @param source input string
	 * @return the length
	 */
	public static int flyingSaucerLength(String s) {
		int count = 0;
		int start = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				count = 1;
				start = i;
			} else if (s.charAt(i) == ')') {
				count++;
				if (count >= 2) {
					return i - start + 1;
				}
			} else if (s.charAt(i) == '=') {
				count++;
			} else if (s.charAt(i) != '=' && count >= 1) {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * Write a Java method that, given a string which many contain a flying saucer
	 * broken into two parts with characters in between, return a string where the
	 * flying is fixed by removing the in between characters. Look for the two parts
	 * of the flying saucer from left to right and fix the saucer with the first
	 * available parts.
	 * <p>
	 * For example: Given: ***(==****===)*** Return: ***(=====)***
	 * <p>
	 * Given: ***(==****)**=)* Return: ***(==)**=)*
	 * <p>
	 * Given: ***(==)** Return: ***(==)**
	 * 
	 * @param s
	 * @return
	 */
	public static String fixFlyingSaucer(String s) {
		int count = 0;
		int start = -1;
		int end = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				count = 0;
				start = i;
			} else if (s.charAt(i) == ')') {
				if (count > 0 && start != -1) {
					end = i;
					break;
				} else {
					start = -1;
				}
			} else if (s.charAt(i) == '=') {
				count++;
			}
		}
		if (start != -1 && end != -1) {
			String firstPart = s.substring(0, start);
			String secondPart = s.substring(end + 1);
			int numEquals = count;
			String fixedSaucer = firstPart + "(" + "=".repeat(numEquals) + ")" + secondPart;
			return fixedSaucer;
		} else {
			return s;
		}
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, return the number of flying saucers. For this problem a flying
	 * saucer may wrap around from the right side of the string to the left.
	 * <p>
	 * For example: Given: ***(===)*** Return: 1
	 * <p>
	 * Given: =)**(==)**( Return: 2
	 * <p>
	 * Given: ***(=*=)** Return: 0
	 * 
	 * @param s
	 * @return
	 */

	public static int countFlyingSaucers(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				String firstPart = s.substring(0, i + 1);
				String endPart = s.substring(i + 1, s.length());
				s = endPart + firstPart;
				break;
			}
		}
		int openCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				openCount++;
			} else if (s.charAt(i) == ')') {
				openCount--;
				count++;
			} else if (s.charAt(i) != '=' && openCount > 0) {
				break;
			}

		}
		return count;
	}

	/**
	 * Write a Java method that, given a string which many contain many flying
	 * saucers, shifts all of the saucers one character to the right. For this
	 * problem a flying saucer may wrap around from the right side of the string to
	 * the left. The returned string should have the same number of characters as
	 * the given string. This is achieved by moving the character to the right of a
	 * saucer to its left. It can be assumed that saucers will never be touching
	 * each other (i.e., there will always be at least one character between any two
	 * saucers). Also, a saucer will not touch itself (e.g., "=)(=").
	 * <p>
	 * For example: Given: ***(===)*** Return: ****(===)**
	 * <p>
	 * Given: =)**(==)**( Return: (=)***(==)*
	 * <p>
	 * Given: a()bcde(=*=)fg Return: ab()cde(=*=)fg
	 * 
	 * @param s
	 * @return
	 */


	public static String flyingSaucersFly(String s) {
	    char[] arr = s.toCharArray();
	    int lastSaucerIndex = -1;

	    for (int i = 1; i < arr.length; i++) {
	        if (arr[i] == '(' && (arr[i-1] != ')' && arr[i-1] != '*')) {
	            lastSaucerIndex += 2;
	            char tmp = arr[i];
	            for (int j = i; j > lastSaucerIndex + 1; j--) {
	                arr[j] = arr[j-1];
	            }
	            arr[lastSaucerIndex+1] = tmp;
	        }
	    }

	    if (lastSaucerIndex < arr.length - 2) {
	        char tmp = arr[arr.length - 1];
	        for (int j = arr.length - 1; j > lastSaucerIndex + 1; j--) {
	            arr[j] = arr[j-1];
	        }
	        arr[lastSaucerIndex+1] = tmp;
	    }

	    return new String(arr);
	}
}
