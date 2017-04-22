package com.oberthur.RSAKeyGen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rsa")
public class RSAKeyWrapper 
{
	private String modulus;
	private String privateExponent;
	private String publicExponent;
	private String primeP;
	private String primeQ;
	private String primeExponentP;
	private String primeExponentQ;
	private String crtCoefficient;
	
	public void setKey(String mod, String privExp, String pubExp, String primeP, String primeQ, String primeExpP, String primeExpQ, String crtCoeff)
	{
		modulus = mod;
		privateExponent = privExp;
		publicExponent = pubExp;
		this.primeP = primeP;
		this.primeQ = primeQ;
		primeExponentP = primeExpP;
		primeExponentQ = primeExpQ;
		crtCoefficient = crtCoeff;
	}
	
	@XmlElement(name = "modulus")
	public String getModulus() {
		return modulus;
	}
	
	@XmlElement(name = "privateExponent")
	public String getPrivateExponent() {
		return privateExponent;
	}
	
	@XmlElement(name = "publicExponent")
	public String getPublicExponent() {
		return publicExponent;
	}
	
	@XmlElement(name = "primeP")
	public String getPrimeP() {
		return primeP;
	}
	
	@XmlElement(name = "primeQ")
	public String getPrimeQ() {
		return primeQ;
	}
	
	@XmlElement(name = "primeExponentP")
	public String getPrimeExponentP() {
		return primeExponentP;
	}
	
	@XmlElement(name = "primeExponentQ")
	public String getPrimeExponentQ() {
		return primeExponentQ;
	}
	
	@XmlElement(name = "crtCoefficient")
	public String getRrtCoefficient() {
		return crtCoefficient;
	}
}
