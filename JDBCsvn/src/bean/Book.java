package bean;

import java.io.File;

public class Book {

	
	private int id;
	private String nameString;
	private File contentFile;
	private File picFile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public File getContentFile() {
		return contentFile;
	}
	public void setContentFile(File contentFile) {
		this.contentFile = contentFile;
	}
	public File getPicFile() {
		return picFile;
	}
	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}
	
	
	public Book(int id, String nameString, File contentFile, File picFile) {
		this.id = id;
		this.nameString = nameString;
		this.contentFile = contentFile;
		this.picFile = picFile;
	}
	
	public Book(int id, String nameString) {
		this.id = id;
		this.nameString = nameString;
	}
	
	
	
}
