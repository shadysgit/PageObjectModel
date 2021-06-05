package com.w2a.utilities;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class SelLogger extends Logger{

    final static Logger logger = Logger.getLogger(SelLogger.class.getName());

    public SelLogger(String name) {
        super(name);
    }

    public static void getLogger(){
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/log4j.properties");
    }


}
