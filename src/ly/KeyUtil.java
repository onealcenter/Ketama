package ly;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Utilities for processing key values.
 */
public class KeyUtil {

	public KeyUtil() {
	}

	/**
	 * Get the bytes for a key.
	 *
	 * @param k
	 *            the key
	 * @return the bytes
	 */
	public static byte[] getKeyBytes(String k) {
		try {
			return k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the keys in byte form for all of the string keys.
	 *
	 * @param keys
	 *            a collection of keys
	 * @return return a collection of the byte representations of keys
	 */
	public static Collection<byte[]> getKeyBytes(Collection<String> keys) {
		Collection<byte[]> rv = new ArrayList<byte[]>(keys.size());
		for (String s : keys) {
			rv.add(getKeyBytes(s));
		}
		return rv;
	}

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static long bytes2Long(byte[] byteNum) {
		long num = 0;
		for (int ix = 0; ix < 8; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}
}
