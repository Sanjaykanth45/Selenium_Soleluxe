package org.sam;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FbLoginPojo extends BaseClass {

    public FbLoginPojo() {
        PageFactory.initElements(driver, this); 
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(xpath = "//input[@name='pass']")
    private WebElement password;

    @FindBy(name = "pass")
    private WebElement loginBtn;

    
    //Getters for all private web elements
	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}


}
