package com.yshow.pic.dao.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yshow.pic.contant.GlobelContant;

public class FileOperat {
	private static final Logger LOG = LoggerFactory.getLogger(FileOperat.class);
	public Properties readConfPic()
	{
		try(FileInputStream fileInputStream = new FileInputStream(GlobelContant.CONFPIC_PATH);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8"))
		{
			Properties properties = new Properties();
			properties.load(inputStreamReader);
			return properties;
		}catch(Exception e)
		{
			LOG.error("", e);
		}
		return null;
	}
	
	static public void writeFile(String path, String content) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
			byte[] b = content.getBytes();
			fileOutputStream.write(b, 0, b.length);
			fileOutputStream.flush();
		} catch (FileNotFoundException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		}
	}
	
	static public StringBuilder readerFile(String path)
	{
		File file = new File(path);
		try(FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
		{
			StringBuilder stringBuilder = new StringBuilder();
			for(String buf; (buf = bufferedReader.readLine())!=null;)
			{
				stringBuilder.append(buf);
			}
			return stringBuilder;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
