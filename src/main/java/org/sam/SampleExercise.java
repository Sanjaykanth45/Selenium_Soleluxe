package org.sam;

public class SampleExercise extends BaseClass {

	public static void main(String[] args) {
		launchBrowser();
		windowMaximize();
		launchUrl("https://www.facebook.com/");
		
		FbLoginPojo f = new FbLoginPojo();
		
		passText("sanjay", f.getEmail());
		passText("sanjay12", f.getPassword());
		
		driver.navigate().refresh();
		
		passText("vishal", f.getEmail());
		passText("vishal12", f.getPassword());
	}
}
