/**
 * 
 */
package controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

/**
 * @author MohamadIdzhar
 *
 */

public class URLSecurity {

	private static Cipher ecipher;
	private static Cipher dcipher;
	private static SecretKey key;
	
	public static SecretKey keyGenerate() throws Exception{
		
		// generate secret key using DES algorithm
		SecretKey key = KeyGenerator.getInstance("DES").generateKey();
		return key;
	}
	
	public static byte[] keyKeyToByte(SecretKey key) throws Exception{
		
		byte[] data = key.getEncoded();
	    //System.out.println("KEY Key to Byte : "+ data);
	    
	     return data;
	}
	
	public static SecretKey keyByteToKey(byte[] data) throws Exception{
				
		SecretKey key2 = new SecretKeySpec(data, 0, data.length, "DES");
	    //System.out.println("KEY Byte to Key: " + key2);
	    return key2;
	}
	
	public static String keyByteToString(byte[] data) throws Exception{
		
		String stringData = new String(data);
		
	    //System.out.println("KEY Byte to String : " + stringData);
	    return stringData;
	}
	
	public static byte[] keyStringToByte(String stringData) throws Exception{
		
		byte[] byteData = stringData.getBytes();
		
	    //System.out.println("KEY String to Byte : " +  byteData);
	    return byteData;
	}
	
	
	public static String encrypt(String str, SecretKey key) {

		try {
			ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);

			// encode the string into a sequence of bytes using the named
			// charset

			// storing the result into a new byte array.
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64
			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String decrypt(String str, SecretKey key) {

		try {
			
			dcipher = Cipher.getInstance("DES");
			
			dcipher.init(Cipher.DECRYPT_MODE, key);
			
			// decode with base64 to get bytes

			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset

			return new String(utf8, "UTF8");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}
	
	public static String encryptionURL(String data, String sessionKey) throws Exception{
		
		byte[] byteKey = keyStringToByte(sessionKey);
		SecretKey keyURL = keyByteToKey(byteKey);
		
		String encURLData = encrypt(data, keyURL);
		
		return encURLData;
	}
	
	public static String decryptionURL(String encData, String sessionKey) throws Exception{
		
		byte[] byteKey = keyStringToByte(sessionKey);
		SecretKey keyURL = keyByteToKey(byteKey);
		
		String decURLData = decrypt(encData, keyURL);
		return decURLData;
	}
	
	public static String sessionKeyGenerate() throws Exception{
		SecretKey oriKey = keyGenerate();
		//System.out.println("Key : " + oriKey);
		
		byte[] byteDataIn = keyKeyToByte(oriKey);
		String stringDataIn = keyByteToString(byteDataIn);
		return stringDataIn;
	}
	
	public static String asciiToHex(String asciiValue) throws Exception
	{
	    char[] chars = asciiValue.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	        hex.append(Integer.toHexString((int) chars[i]));
	    }
	    return hex.toString();
	}
	
	public static String hexToASCII (String hexValue) throws Exception
	{
	    StringBuilder output = new StringBuilder("");
	    for (int i = 0; i < hexValue.length(); i += 2)
	    {
	        String str = hexValue.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    return output.toString();
	}
	
	public static String encryptFinal(String data, String sessionKey) throws Exception
	{
		String encrypted = asciiToHex(encryptionURL(data, sessionKey));
		return encrypted;
	}
	
	public static String decryptFinal(String encData, String sessionKey) throws Exception
	{
		String decrypted = decryptionURL(hexToASCII(encData), sessionKey);
		return decrypted;
	}
	
	
	public static void main(String[] args) {

		try {
			
			String sessionKey = sessionKeyGenerate();
			String data = "Test new encapsulation engine <~!@#$%^&*()>,./";
			String sessionKeyEnc = asciiToHex(sessionKey);

			/*key = keyGenerate();
			System.out.println("Key : " + key);
			
			byte[] byteDataIn = keyKeyToByte(key);
			String stringDataIn = keyByteToString(byteDataIn);
			byte[] byteDataOut =  keyStringToByte(stringDataIn);
			//SecretKey key2 = keyByteToKey(byteDataOut);
			//System.out.println("Key after convert : " +key2);
			
			//String encrypted = encrypt("You are java!!!!!", key);
			//String decrypted = decrypt(encrypted, key2);
			*/
		
			
			String sessionKeyResult = hexToASCII(sessionKeyEnc);
			String encrypted = encryptFinal(data, sessionKey);
			String decrypted = decryptFinal(encrypted, sessionKey);
			
			System.out.println("Raw Data : " + data);
			System.out.println("Session Key : " + sessionKey);
			System.out.println("Session Key ENC : " + sessionKeyEnc);
			System.out.println("Session Key Result : " + sessionKeyResult);
			System.out.println("Encrypted Data : " + encrypted);
			System.out.println("Decrypted Data : " + decrypted);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
