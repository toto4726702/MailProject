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
		   * ����* ͨ��DES���ܽ���ʵ��һ��String�ַ����ļ��ܺͽ���.
		 */
		public static void DES(String str) throws Exception{
			
			SecureRandom sr = new SecureRandom();
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);
			//SecretKey key = kg.generateKey();
			byte rawKeyData[] =  "429387498234".getBytes();
			System.out.println("�ܳ�===>" + rawKeyData);
			byte[] encryptedData = encryptDES(rawKeyData, str);
			decryptDES(rawKeyData, encryptedData);
		}
		
		@Test
		public void AES(){
			String content = "test";  
			String password = "12345678";  
			//����  
			System.out.println("����ǰ��" + content);  
			byte[] encryptResult = encryptAES(content, password);  
			//����  
			byte[] decryptResult = decryptAES(encryptResult,password);  
			System.out.println("���ܺ�" + new String(decryptResult));  
		}

		public static String decryptDES(byte[] rawKeyData, byte[] encryptedData)throws Exception {
			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom sr = new SecureRandom();
			// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
			DESKeySpec dks = new DESKeySpec(rawKeyData);
			// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			// Cipher����ʵ����ɽ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// ��ʽִ�н��ܲ���
			byte decryptedData[] = cipher.doFinal(encryptedData);
			System.out.println("���ܺ�===>" + new String(decryptedData));
			return new String(decryptedData);
		}

		public static byte[] encryptDES(byte[] rawKeyData, String str) throws Exception {
			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom sr = new SecureRandom();
			// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
			DESKeySpec dks = new DESKeySpec(rawKeyData);
			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// ���ڣ���ȡ���ݲ�����
			byte data[] = str.getBytes();
			// ��ʽִ�м��ܲ���
			byte[] encryptedData = cipher.doFinal(data);
			System.out.println("���ܺ�===>" + encryptedData);
			return encryptedData;
		}
		
		public static byte[] encryptAES(String content, String password) {  
	        try {             
	                KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	                kgen.init(128, new SecureRandom(password.getBytes()));  
	                SecretKey secretKey = kgen.generateKey();  
	                byte[] enCodeFormat = secretKey.getEncoded();  
	                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
	                Cipher cipher = Cipher.getInstance("AES");// ����������  
	                byte[] byteContent = content.getBytes("utf-8");  
	                cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��  
	                byte[] result = cipher.doFinal(byteContent);  
	                return result; // ����  
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
	                 Cipher cipher = Cipher.getInstance("AES");// ����������  
	                cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��  
	                byte[] result = cipher.doFinal(content);  
	                return result; // ����  
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
		
		/**��������ת����16���� 
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