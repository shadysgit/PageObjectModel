package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;
import org.openqa.selenium.By;

public class AccountsPage extends Page {

    public CreateAccountPage goToCreateAccount(){
        driver.findElement(By.xpath("//lyte-yield[text() = 'Create an Account'] // parent::button")).click();

        return new CreateAccountPage();
    }


    public void goToImportAccounts(){

    }


}
