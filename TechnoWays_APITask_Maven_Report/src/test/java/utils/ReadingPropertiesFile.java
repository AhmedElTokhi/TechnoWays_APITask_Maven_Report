package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadingPropertiesFile {

	protected Properties Prop = null;
	protected FileInputStream LoadFile = null;

	public ReadingPropertiesFile(String FilePath) throws IOException {
		Prop = new Properties();
		LoadFile = new FileInputStream(FilePath);
		Prop.load(LoadFile);
	}

	// ===== Driver Methods =======
	public String GetPrintUserEmailURL() {
		return Prop.getProperty("PrintUserEmailURL");
	}
	
	public String GetprintValue() {
		return Prop.getProperty("printValue");
	}

	public String GetUserPostsURL() {
		return Prop.getProperty("GetUserPostsURL");
	}
	
	public String GetcheckPostValue() {
		return Prop.getProperty("checkPostValue");
	}
	public String GetMinValue() {
		return Prop.getProperty("MinValue");
	}
	public String GetMaxValue() {
		return Prop.getProperty("MaxValue");
	}
	
	
	public String GetAddPostURL (){
		return Prop.getProperty("AddPostURL");
	}
	
	public String GetNewPostTitle(){
		return Prop.getProperty("NewPostTitle");
	}
	
	public String GetNewPostBody() {
		return Prop.getProperty("NewPostBody");
	}
	
}
