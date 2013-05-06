package cn.edu.shiep.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class EncryptUtil {

		/**
		   * 　　* 通过DES加密解密实现一个String字符串的加密和解密.
		 */
		public static void DES(String str) throws Exception{
			
			SecureRandom sr = new SecureRandom();
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);
			//SecretKey key = kg.generateKey();
			byte rawKeyData[] =  "429387498234".getBytes();
			System.out.println("密匙===>" + rawKeyData);
			byte[] encryptedData = encryptDES(rawKeyData, str);
			decryptDES(rawKeyData, encryptedData);
		}
		
		@Test
		public void AES(){
			String content = "test";  
			String password = "12345678";  
			//加密  
			System.out.println("加密前：" + content);  
			byte[] encryptResult = encryptAES(content, password);  
			//解密  
			byte[] decryptResult = decryptAES(encryptResult,password);  
			System.out.println("解密后：" + new String(decryptResult));  
		}

		public static String decryptDES(byte[] rawKeyData, byte[] encryptedData)throws Exception {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(rawKeyData);
			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 正式执行解密操作
			byte decryptedData[] = cipher.doFinal(encryptedData);
			System.out.println("解密后===>" + new String(decryptedData));
			return new String(decryptedData);
		}

		public static byte[] encryptDES(byte[] rawKeyData, String str) throws Exception {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(rawKeyData);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 现在，获取数据并加密
			byte data[] = str.getBytes();
			// 正式执行加密操作
			byte[] encryptedData = cipher.doFinal(data);
			System.out.println("加密后===>" + encryptedData);
			return encryptedData;
		}
		
		public static byte[] encryptAES(String content, String password) {  
	        try {             
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                kgen.init(128, new SecureRandom(password.getBytes()));  
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
	                byte[] result = cipher.doFinal(byteContent);  
	                return result; // 加密  
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
		}  
		
		public static byte[] decryptAES(byte[] content, String password) {  
	        try {  
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                 kgen.init(128, new SecureRandom(password.getBytes()));  
	                 SecretKey secretKey = kgen.generateKey();  
	                 byte[] enCodeFormat = secretKey.getEncoded();  
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
	                byte[] result = cipher.doFinal(content);  
	                return result; // 加密  
	        } catch (NoSuchAlgorithmException e) {  
	                e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	                e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	                e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	                e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	                e.printStackTrace();  
	        }  
	        return null;  
		}  
		
		/**将二进制转换成16进制 
		 * @param buf 
		 * @return 
		 */  
		public static String parseByte2HexStr(byte buf[]) {  
		        StringBuffer sb = new StringBuffer();  
		        for (int i = 0; i < buf.length; i++) {  
		                String hex = Integer.toHexString(buf[i] & 0xFF);  
		                if (hex.length() == 1) {  
		                        hex = '0' + hex;  
		                }  
		                sb.append(hex.toUpperCase());  
		        }  
		        return sb.toString();  
		}  
		
}