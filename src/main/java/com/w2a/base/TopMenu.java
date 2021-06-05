package com.w2a.base;

public class TopMenu extends Page  {


    public void goToAccounts(){
        click("accountsMenu_XPATH");
    }

    public void doSignOut(){
        click("profilePic_CSS");
        click("signOut_XPATH");
    }


}
