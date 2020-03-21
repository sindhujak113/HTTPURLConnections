package com.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class AccessWebpage 
{
	public static void main(String[] args) 
	{
        String url = "http://www.google.com";
        String filePath = "Google.html";
 
        try 
        {
             URL urlObj = new URL(url);
             HttpURLConnection  urlCon = (HttpURLConnection) urlObj.openConnection();
 
            int responseCode = urlCon.getResponseCode();
            
            InputStream inputStream = urlCon.getInputStream();
            BufferedInputStream reader = new BufferedInputStream(inputStream);
 
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
 
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
 
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }
 
            writer.close();
            reader.close();
 
            System.out.println("Web page saved");
            System.out.println("responseCode returned from the site ["+responseCode+"]");
            
            Map<String, List<String>> map = urlCon.getHeaderFields();
            
            System.out.println("URL Connection header fields as below :\n");
            for (String key : map.keySet()) {
                System.out.println(key + ":");
             
                List<String> values = map.get(key);
             
                for (String aValue : values) {
                    System.out.println("\t" + aValue);
                }
            }
 
        } catch (MalformedURLException e) {
            System.out.println("The specified URL is malformed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurs: " + e.getMessage());
        }
	}
}
