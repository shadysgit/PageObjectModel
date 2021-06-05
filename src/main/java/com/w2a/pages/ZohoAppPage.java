package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;
import org.openqa.selenium.By;

public class ZohoAppPage extends Page {

    public CRMHomePage clickOnCRM(){

        driver.findElement(By.xpath("//div[text() = 'CRM' and @class = 'app-nm']")).click();

        return new CRMHomePage();
    }

}
