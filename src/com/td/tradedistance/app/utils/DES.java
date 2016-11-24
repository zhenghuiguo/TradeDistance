package com.td.tradedistance.app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.td.tradedistance.app.global.Global;

public class DES {
	/**
	 * <p>
	 * 功能描述:加密
	 * </p>
	 * 
	 * @param encryptString
	 * @param encryptKey
	 * @return String
	 * @throws Exception
	 * @author:郑惠国
	 * @update:[日期2013-5-13] [更改人姓名][变更描述]
	 */
	public static String encryptDES(String encryptString, String encryptKey)
			throws Exception {
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance(Global.DES_MODEL);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
		return Base64.encode(encryptedData);
	}

	/**
	 * <p>
	 * 功能描述:解密
	 * </p>
	 * 
	 * @param decryptString
	 * @param decryptKey
	 * @return String
	 * @throws Exception
	 * @author:郑惠国
	 * @update:[日期2013-5-13] [更改人姓名][变更描述]
	 */
	@SuppressWarnings("static-access")
	public static String decryptDES(String decryptString, String decryptKey)
			throws Exception {
		byte[] byteMi = new Base64().decode(decryptString);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance(Global.DES_MODEL);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte decryptedData[] = cipher.doFinal(byteMi);
		return new String(decryptedData);
	}

	public static void main(String[] avg) {
		try {
			System.out.println(encryptDES("dhdhd", "a9324c48"));
			// System.out.println(encryptDES("dhdhd", "a9324c48"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}