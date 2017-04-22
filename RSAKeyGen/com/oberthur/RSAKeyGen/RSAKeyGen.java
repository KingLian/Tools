package com.oberthur.RSAKeyGen;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

import com.oberthur.RSAKeyGen.view.RSAKeyGenController;

public class RSAKeyGen 
{

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
		// get parameter or key size
		int keySize = 1024;
		if(args.length > 0) 
		{
			if(args[0] != null) 
			{
				if(Integer.parseInt(args[0]) > 0) 
				{
					keySize = Integer.parseInt(args[0]);
				}
			}
		}
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);	// Initialise key size
		
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		Key publicKey = keyPair.getPublic();
		Key privateKey = keyPair.getPrivate();
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		RSAPrivateCrtKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);
		
		BigInteger modulus = publicKeySpec.getModulus();
		BigInteger publicExp = publicKeySpec.getPublicExponent();
		BigInteger privateExp = privateKeySpec.getPrivateExponent();
		
		System.out.println("Modulus : \n" + bigIntegerToString(modulus));
		System.out.println("Public Exponent : \n" + bigIntegerToString(publicExp));
		System.out.println("Private Exponent : \n" + bigIntegerToString(privateExp));
		
		BigInteger primeP = privateKeySpec.getPrimeP();
		BigInteger primeQ = privateKeySpec.getPrimeQ();
		BigInteger primeExpP = privateKeySpec.getPrimeExponentP();
		BigInteger primeExpQ = privateKeySpec.getPrimeExponentQ();
		BigInteger crtCoef = privateKeySpec.getCrtCoefficient();
		
		System.out.println("Prime P : \n" + bigIntegerToString(primeP));
		System.out.println("Prime Q : \n" + bigIntegerToString(primeQ));
		System.out.println("Prime Exponent P : \n" + bigIntegerToString(primeExpP));
		System.out.println("Prime Exponent Q : \n" + bigIntegerToString(primeExpQ));
		System.out.println("CRT Coefficient : \n" + bigIntegerToString(crtCoef));
		
		RSAKeyGenController.controller.setKeyValues(modulus, publicExp, privateExp, primeP, primeQ, primeExpP, primeExpQ, crtCoef);
		
	}
	
	private static String bigIntegerToString(BigInteger number)
	{
		String sb = "";
		byte[] num = number.toByteArray();
		for(int i = 0; i < num.length; i++)
			sb += (String.format("%02X ", num[i]));
		return sb;
	}

}
