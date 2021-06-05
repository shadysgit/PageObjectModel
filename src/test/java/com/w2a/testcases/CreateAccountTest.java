package com.w2a.testcases;

import com.w2a.base.TopMenu;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.utilities.TestUtil;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateAccountTest {

    @Test(dataProviderClass = TestUtil.class,dataProvider = "dp")
    public void createAccountTest(Hashtable<String , String> data){
        ZohoAppPage zp = new ZohoAppPage();
        zp.clickOnCRM();
        TopMenu menu = new TopMenu();
        menu.goToAccounts();
        AccountsPage ap = new AccountsPage();
        ap.goToCreateAccount().createAccount(data.get("accountName"));

    }

}
