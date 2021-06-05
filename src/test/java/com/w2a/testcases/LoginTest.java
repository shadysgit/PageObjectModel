package com.w2a.testcases;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.utilities.TestUtil;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest{

    @Test(dataProviderClass = TestUtil.class,dataProvider = "dp")
    public void loginTest(Hashtable<String,String> data) {

        HomePage hp = new HomePage();
        LoginPage lp = hp.goToSignIn();
        lp.doLogin(data.get("userName"),data.get("password"));

    }

}
