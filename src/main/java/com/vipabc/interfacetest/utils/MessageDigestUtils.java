package com.vipabc.interfacetest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MessageDigestUtils {

	public static final String UTF_8 = "UTF-8";

	private static final int STREAM_BUFFER_LENGTH = 1024;

	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static MessageDigest getMd5Digest() {
		return getDigest("MD5");
	}

	private static MessageDigest getShaDigest() {
		return getDigest("SHA");
	}

	private static MessageDigest getSha512Digest() {
		return getDigest("SHA-512");
	}

	private static byte[] digest(MessageDigest digest, InputStream data) throws IOException {
		byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
		int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

		while (read > -1) {
			digest.update(buffer, 0, read);
			read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
		}

		return digest.digest();
	}

	public static byte[] md5(byte[] data) {
		return getMd5Digest().digest(data);
	}

	public static byte[] md5(InputStream data) throws IOException {
		return digest(getMd5Digest(), data);
	}

	public static byte[] md5(String data) {
		return md5(getBytesUtf8(data));
	}

	public static String md5Hex(byte[] data) {
		return encodeHexString(md5(data));
	}

	public static String md5Hex(InputStream data) throws IOException {
		return encodeHexString(md5(data));
	}

	public static String md5Hex(String data) {
		return encodeHexString(md5(data));
	}

	public static byte[] sha(byte[] data) {
		return getShaDigest().digest(data);
	}

	public static byte[] sha(InputStream data) throws IOException {
		return digest(getShaDigest(), data);
	}

	public static byte[] sha(String data) {
		return sha(getBytesUtf8(data));
	}

	public static byte[] sha512(byte[] data) {
		return getSha512Digest().digest(data);
	}

	public static byte[] sha512(InputStream data) throws IOException {
		return digest(getSha512Digest(), data);
	}

	public static byte[] sha512(String data) {
		return sha512(getBytesUtf8(data));
	}

	public static String sha512Hex(byte[] data) {
		return encodeHexString(sha512(data));
	}

	public static String sha512Hex(InputStream data) throws IOException {
		return encodeHexString(sha512(data));
	}

	public static String sha512Hex(String data) {
		return encodeHexString(sha512(data));
	}

	public static String shaHex(byte[] data) {
		return encodeHexString(sha(data));
	}

	public static String shaHex(InputStream data) throws IOException {
		return encodeHexString(sha(data));
	}

	public static String shaHex(String data) {
		return encodeHexString(sha(data));
	}

	public static byte[] getBytesUtf8(String string) {
		return getBytesUnchecked(string, UTF_8);
	}

	public static byte[] getBytesUnchecked(String string, String charsetName) {
		if (string == null) {
			return null;
		}
		try {
			return string.getBytes(charsetName);
		} catch (Exception e) {
			throw new IllegalStatusException(charsetName, e);
		}
	}

	public static String encodeHexString(byte[] data) {
		return new String(encodeHex(data));
	}

	public static char[] encodeHex(byte[] data) {
		return encodeHex(data, true);
	}

	public static char[] encodeHex(byte[] data, boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	protected static char[] encodeHex(byte[] data, char[] toDigits) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

}
