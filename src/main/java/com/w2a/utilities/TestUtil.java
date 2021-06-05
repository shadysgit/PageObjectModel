package com.w2a.utilities;

import com.w2a.base.Page;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

public class TestUtil extends Page {

    public static  String screenShotName ;


    public static void captureScreenshot() throws IOException {

        Date d = new Date();
        screenShotName = d.toString().replace(":","_").replace(" ","_")+".jpg";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile,new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenShotName));


    }

    @DataProvider(name = "dp")
    public Object[] getData(Method m){
        String sheetName = m.getName();
        int rows = excel.rowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[] data = new Object[rows-1];
        Hashtable<String,String> table = null;
        for (int rowNum = 2 ; rowNum <= rows ; rowNum++){
            table = new Hashtable<String, String>();
            for(int colNum = 0 ; colNum < cols ; colNum++){
                table.put(excel.getCellData(sheetName,colNum,1),excel.getCellData(sheetName,colNum,rowNum));
            }
            data[rowNum-2] = table;
        }
        return data;
    }


}
