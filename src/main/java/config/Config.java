package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config   {
	
public  static Properties loadProperties() {
	
	try(FileInputStream fs = new FileInputStream("variaveis.properties")) {
		Properties props = new Properties();
		props.load(fs);
		return props;
	}catch(IOException e){
		
	}
	return null;
}
	

}
