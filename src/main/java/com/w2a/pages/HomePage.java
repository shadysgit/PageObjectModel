package com.w2a.pages;

import com.w2a.base.Page;

public class HomePage extends Page {

    public LoginPage goToSignIn(){
        click("zohoLoginbtn_CSS");

        return new LoginPage();
    }

}
