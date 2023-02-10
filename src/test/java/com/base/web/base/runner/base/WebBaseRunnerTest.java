package com.base.web.base.runner.base;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.base.web.base.base.ThreadLocalDriver;
import com.base.web.base.base.WebAppFactory;

public class WebBaseRunnerTest 
{
    @BeforeMethod
    public void setup() throws IOException, Throwable{
        WebAppFactory webAppFactory = new WebAppFactory();
        ThreadLocalDriver.setTLWebDriver(webAppFactory.buildDeviceService());
    }

    @AfterMethod
    public synchronized void teardown() {
        ThreadLocalDriver.getTLWebDriver().quit();
    }
}
