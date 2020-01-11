package com.yshow.pic.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class URLEncryptCus {
	
	public static String coreEncrypt(String mingwen, int enver)
	{
		switch(enver)
		{
			case 1:return encrypt1(mingwen);
			case 2:return null;
			default : return null;
		}
	}
	
	//º”√‹1
	public static String encrypt1(String mingwen)
	{
		byte[] bt = mingwen.getBytes();
		for(int index = 0; index < bt.length; index++)
		{
			bt[index] = (byte)(~bt[index]^0xf4);
		}
		return Base64.encode(bt);
	}
}
