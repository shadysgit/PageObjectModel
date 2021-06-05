package com.w2a.pages;

import com.w2a.base.Page;

public class LoginPage extends Page {

    public ZohoAppPage doLogin(String userName,String password){

        type("loginId_CSS",userName);
        click("nextBtn_XPATH");
        type("password_CSS",password);
        click("signInbtn_XPATH");
        return new ZohoAppPage();
    }

}
