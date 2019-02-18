/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.mail.internet.MimeUtility;

public class SecurityHelper {
	private final static int ITERATIONS = 1000;

	public static String encrypt(String key, String plainText) throws Exception {
		try {
			byte[] salt = new byte[8];
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			byte[] digest = md.digest();
			for (int i = 0; i < 8; i++) {
				salt[i] = digest[i];
			}
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes());
			String saltString = new String(encode(salt));
			String ciphertextString = new String(encode(cipherText));
			return saltString.substring(0, 6) + ciphertextString.substring(0, 12) + saltString.substring(6, 12);
		} catch (Exception e) {
			throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
		}
	}

	public static String decrypt(String key, String encryptTxt) throws Exception {
		//int saltLength = 12;
		try {
			String salt = encryptTxt.substring(0, 6) + encryptTxt.substring(18, encryptTxt.length());
			String ciphertext = encryptTxt.substring(6, 18);
			byte[] saltarray = decode(salt.getBytes());
			byte[] ciphertextArray = decode(ciphertext.getBytes());
			PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(keySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
			byte[] plaintextArray = cipher.doFinal(ciphertextArray);
			return new String(plaintextArray);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * base64 encode
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode(byte[] b) throws Exception {
		ByteArrayOutputStream baos = null;
		OutputStream b64os = null;
		try {
			baos = new ByteArrayOutputStream();
			b64os = MimeUtility.encode(baos, "base64");
			b64os.write(b);
			b64os.close();
			return baos.toByteArray();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (baos != null) {
					baos.close();
					baos = null;
				}
			} catch (Exception e) {
			}
			try {
				if (b64os != null) {
					b64os.close();
					b64os = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * base64 decode
	 * @param b
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(byte[] b) throws Exception {
		ByteArrayInputStream bais = null;
		InputStream b64is = null;
		try {
			bais = new ByteArrayInputStream(b);
			b64is = MimeUtility.decode(bais, "base64");
			byte[] tmp = new byte[b.length];
			int n = b64is.read(tmp);
			byte[] res = new byte[n];
			System.arraycopy(tmp, 0, res, 0, n);
			return res;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (bais != null) {
					bais.close();
					bais = null;
				}
			} catch (Exception e) {
			}
			try {
				if (b64is != null) {
					b64is.close();
					b64is = null;
				}
			} catch (Exception e) {
			}
		}
	}
}