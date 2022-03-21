package com.lambkit.component.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AbstractSymmetricCipherService;
 
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
 
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * shiro 秘钥生成器
 * 
 * @author yangyong shiro有自己的随机生成秘钥的方法 秘钥生成器
 *
 */
public class MySymmetricCipherService extends AbstractSymmetricCipherService {
	protected MySymmetricCipherService(String algorithmName) {
		super(algorithmName);
		// TODO Auto-generated constructor stub
	}

	public static byte[] generateNewKeyFromSuper() {
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException var5) {
			String msg = "Unable to acquire AES algorithm.  This is required to function.";
			throw new IllegalStateException(msg, var5);
		}

		kg.init(128);
		SecretKey key = kg.generateKey();
		byte[] encoded = key.getEncoded();
		return encoded;
	}

	/**
	 * 使用shiro官方的生成
	 * org.apache.shiro.crypto.AbstractSymmetricCipherService#generateNewKey()
	 * 
	 * @return
	 */
	public static byte[] getCipherKey() {
		MySymmetricCipherService mySymmetricCipherService = new MySymmetricCipherService("AES");
		Key gKey = mySymmetricCipherService.generateNewKey();
		return gKey.getEncoded();
	}

	public static void main(String[] args) {
		MySymmetricCipherService mySymmetricCipherService = new MySymmetricCipherService("AES");
		Key gKey = mySymmetricCipherService.generateNewKey();
		System.out.println("key: " + gKey.getEncoded());
		System.out.println("key Base64.encodeToString: " + Base64.encodeToString(gKey.getEncoded()));

		byte[] decodeValue = org.apache.shiro.codec.Base64.decode("t0EWNQWKMXYzKTDSQpNNfg==");
		System.out.println("decodeValue: " + decodeValue);
	}
}
