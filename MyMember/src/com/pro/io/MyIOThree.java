package com.pro.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;

import com.sun.crypto.provider.SunJCE;

public class MyIOThree {

	public static void main(String[] args) {
		MyIOThree three = new MyIOThree();
		// three.encrypt();
		three.decrypt();
		byte[] salt = three.initSalt();
		try {
			String str = Base64.encodeBase64String(salt);
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] initSalt() {
		SecureRandom random = new SecureRandom(); // 默认是"SHA1PRNG" 算法名称
		// 你也可以根据实际需要指定自己的算法
		// SecureRandom specRandom = SecureRandom.getInstance("SHA1PRNG");
		return random.generateSeed(8);
	}

	public void compress() throws Exception {
		// 对于 gz格式的压缩包，读取文件数据就这么简单
		// FileInputStream fs = new FileInputStream("allgames.gz");
		// GZIPInputStream gz = new GZIPInputStream(fs);

		// 解压读取zip包,稍微复杂一点，因为有归档文件
		FileInputStream fis = new FileInputStream("shareware.zip");
		ZipInputStream zip = new ZipInputStream(fis);
		ZipEntry entry = null;
		int b = 0;
		while ((entry = zip.getNextEntry()) != null) {
			FileOutputStream fos = new FileOutputStream(entry.getName());
			while ((b = zip.read()) != -1) {
				fos.write(b);
			}
			zip.closeEntry();
			fos.flush();
			fos.close();
		}
		zip.close();
	}

	// 解密
	public void decrypt() {
		// 准备一个用于解密的CipherInputStream:
		CipherInputStream cis = null;
		String password = "Mary had a little spider"; // 口令
		try {
			FileInputStream fin = new FileInputStream("D:\\secrets.des");
			char[] pbeKeyData = password.toCharArray();
			PBEKeySpec pbeKeySpec = new PBEKeySpec(pbeKeyData);

			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey desKey = keyFactory.generateSecret(pbeKeySpec);
			Cipher des = Cipher.getInstance("PBEWithMD5AndDES");
			PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[] {
					1, 2, 3, 4, 5, 6, 7, 8 }, 1000);
			des.init(Cipher.DECRYPT_MODE, desKey, parameterSpec); // 初始化

			cis = new CipherInputStream(fin, des);
			byte[] input = new byte[256];
			int len = 0;
			while ((len = cis.read(input)) != -1) {
				String str = new String(input, 0, len);
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cis != null) {
					cis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 加密
	public void encrypt() {
		String infile = "D:\\secrets.txt"; // 加密文件
		String outfile = "D:\\secrets.des"; // 加密后的文件
		String password = "Mary had a little spider";
		CipherOutputStream cout = null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(infile);
			FileOutputStream fout = new FileOutputStream(outfile);
			// 注册实现算法的提供者
			Provider sunJce = new SunJCE();
			Security.addProvider(sunJce);
			// 创建密钥
			char[] pbeKeyData = password.toCharArray();
			PBEKeySpec pbeKeySpec = new PBEKeySpec(pbeKeyData);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
			// 使用数据加密标准
			Cipher pbe = Cipher.getInstance("PBEWithMD5AndDES");
			PBEParameterSpec parameterSpec = new PBEParameterSpec(new byte[] {
					1, 2, 3, 4, 5, 6, 7, 8 }, 1000);
			pbe.init(Cipher.ENCRYPT_MODE, pbeKey, parameterSpec);
			cout = new CipherOutputStream(fout, pbe); // 读取非文件数据时
														// 用pbe.doFinal(input)即可

			byte[] input = new byte[64];
			while (true) {
				int len = fin.read(input);
				if (len == -1) {
					break;
				}
				cout.write(input, 0, len);
			}
			cout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cout != null) {
					cout.close();
				}
				if (fin != null) {
					fin.close();
				}
			} catch (IOException e) {
			}
		}
	}
}
