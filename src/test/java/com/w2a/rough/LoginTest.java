package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.CRMHomePage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

    public static void main(String[] args) throws InterruptedException {
        HomePage home = new HomePage();
        LoginPage login = home.goToSignIn();
        ZohoAppPage appPage = login.doLogin("pythontestshady@gmail.com","Muthi@1234");
        CRMHomePage crm = appPage.clickOnCRM();
        Page.menu.goToAccounts();
        AccountsPage accpg = new AccountsPage();
        CreateAccountPage caccp = accpg.goToCreateAccount();
        //caccp.createAccount();

        Thread.sleep(2000);

        Page.menu.doSignOut();
    }

}
