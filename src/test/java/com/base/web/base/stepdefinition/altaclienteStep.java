package com.base.web.base.stepdefinition;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.web.base.base.ThreadLocalDriver;
import com.base.web.base.base.WebAppFactory;
import com.base.web.base.base.writeExcel;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class altaclienteStep {
    public WebDriver driver = null;
    WebAppFactory appWebFactory;
    writeExcel objExcelFile = new writeExcel();
    
    @Before
    public void setup() throws Throwable{
        appWebFactory = new WebAppFactory();
        driver = ThreadLocalDriver.getTLWebDriver();
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started WebExampleStep in thread: " + threadId + ", in JVM: " + processName);
    }

    @Given("El usuario ingresa al Login Page")
    public void userIsOnHomepageOut() throws Throwable 
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(appWebFactory.getWebUrl());
        Thread.sleep(1000);
        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.id("proceed-link")).click();
    }
    
    @When("El usuario ingresa el {string} y {string}")
    public void userEntersUsernameAndPasswordOut(String username, String password) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("signOnName")));
        driver.findElement(By.id("signOnName")).sendKeys(username);
    	driver.findElement(By.id("password")).sendKeys(password);
    	driver.findElement(By.id("sign-in")).click();

    }
    
    @Then("Redirecciona al Home Page")
    public void successMessageIsDisplayedOut() throws Throwable {
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign Off')]")));
       
        WebElement iframe = driver.findElement(By.xpath("/html/frameset/frame[1]"));
        driver.switchTo().frame(iframe);

        Thread.sleep(2000);
        String exp_message = "Sign Off";
    	String actual = driver.findElement(By.xpath("//a[contains(text(),'Sign Off')]")).getText();
        Assert.assertEquals(exp_message, actual);
        System.out.println("assert complete");
        driver.switchTo().parentFrame();
    }

    @When("El usuario da click en Menu")
    public void clickMenu() throws Throwable {
        Thread.sleep(1000);
        WebElement iframe = driver.findElement(By.xpath("/html/frameset/frame[2]"));
        driver.switchTo().frame(iframe);
    	
        driver.findElement(By.id("imgError")).click();
    }

    @And("El usuario da click en Cliente")
    public void clickCliente() throws Throwable {
    	driver.findElement(By.xpath("//img[@alt='Cliente']")).click();
    }

    @And("El usuario da click en Individual Customer")
    public void clickVerCliente() throws Throwable {
    	driver.findElement(By.xpath("//a[contains(text(),'Individual Customer ')]")).click();
        driver.switchTo().parentFrame();
    }

    @And("El usuario ingresa datos de cliente {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void inputData(String mnemocino, String dni, String apaterno, String amaterno, String nombre, String ncompleto, String estadocivil, String nacimiento, String gbdirec, String empresa) throws Throwable {
    	String MainWindow=driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())			
        {
            String ChildWindow=i1.next();		
            		
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {
                driver.switchTo().window(ChildWindow);    		
            }
        }

        driver.findElement(By.id("fieldName:MNEMONIC")).sendKeys(mnemocino);
        Thread.sleep(1000);
        driver.findElement(By.id("fieldName:LEGAL.ID:1")).sendKeys(dni);
        driver.findElement(By.id("fieldName:FAMILY.NAME")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("fieldName:FAMILY.NAME")).sendKeys(apaterno);
        driver.findElement(By.id("fieldName:NAME.2:1")).sendKeys(amaterno);
        driver.findElement(By.id("fieldName:NAME.1:1")).sendKeys(nombre);
        driver.findElement(By.id("fieldName:SHORT.NAME:1")).sendKeys(ncompleto);
        Select selectProducto = new Select(driver.findElement(By.id("fieldName:MARITAL.STATUS")));
		selectProducto.selectByVisibleText(estadocivil);
        driver.findElement(By.id("radio:mainTab:GENDER")).click();
        driver.findElement(By.id("fieldName:DATE.OF.BIRTH")).sendKeys(nacimiento);

        driver.findElement(By.xpath("//img[@dropfield='fieldName:OCCUPATION:1']")).click();
        driver.findElement(By.xpath("//td[contains(text(),'ABOGADO')]")).click();

        driver.findElement(By.xpath("//img[@dropfield='fieldName:STREET:1']")).click();
        driver.findElement(By.xpath("//td[contains(text(),'AMAZONAS')]")).click();

        driver.findElement(By.id("fieldName:ADDRESS:1:1")).sendKeys(gbdirec);

        driver.findElement(By.xpath("//img[@dropfield='fieldName:PHONE.1:1']")).click();
        driver.findElement(By.xpath("//td[contains(text(),'PERSONAL')]")).click();
        driver.findElement(By.id("fieldName:SMS.1:1")).sendKeys("999999999");
        driver.findElement(By.id("fieldName:EMAIL.1:1")).sendKeys("claynes@gmail.com");


        driver.findElement(By.xpath("//span[contains(text(),'Datos Laborales')]")).click();
        driver.findElement(By.id("fieldName:EMPLOYERS.NAME:1")).sendKeys(empresa);
        
        driver.findElement(By.xpath("//span[contains(text(),'Proteccion de Datos')]")).click();
        driver.findElement(By.id("radio:tab3:CONFID.TXT")).click();

        driver.findElement(By.xpath("//img[@alt='Validate a deal']")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//img[@alt='Commit the deal']")).click();

       //   
        
        Thread.sleep(5000);
    }

    @Then("Se muestra el codigo de alta")
    public void assertAlta() throws Throwable {
       //String cod = driver.findElement(By.id("transactionId")).getCssValue("value");
       String cod = driver.findElement(By.xpath("//*[@id='messages']/tbody/tr[2]/td[2]/table[2]/tbody/tr/td")).getText();

       System.out.println("este es : " +cod); 
       String cod2 = "ALTA CLIENTE"; 
        String[] datoWrite = {cod,cod2}; 
        //Escriba los datos a llenar 
        objExcelFile.writeToExcel(datoWrite);
    }






    @And("El usuario da click en Operaciones Minoristas")
    public void clickOperacionesMinoristas() throws Throwable {
    	driver.findElement(By.xpath("//img[@alt='Operaciones Minoristas']")).click();
    }
    @And("El usuario da click en Buscar Cliente")
    public void buscarCliente() throws Throwable {
    	driver.findElement(By.xpath("//a[contains(text(),'Buscar Cliente ')]")).click();
        driver.switchTo().parentFrame();
    }
    @And("El usuario ingresa datos de cliente {string}")
    public void inputDni(String dni) throws Throwable {
    	String MainWindow=driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext())			
        {
            String ChildWindow=i1.next();		
            		
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {
                driver.switchTo().window(ChildWindow);    		
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("value:3:1:1")));
        driver.findElement(By.id("value:3:1:1")).sendKeys(dni);
    	driver.findElement(By.xpath("//a[@alt='Run Selection']")).click();
    }
    @Then("Se validan los datos")
    public void validarDatos() throws Throwable {
    	driver.findElement(By.xpath("//a[@title='Single Customer View.']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Basic Details')]")).click();
        Thread.sleep(3000);
    }

    @Then("Se valida el estado")
    public void validarEstado() throws Throwable {
    	String actualString = driver.findElement(By.xpath("//td[contains(text(),'Activo')]")).getText();
        Assert.assertNotEquals("Activo", actualString);
        System.out.println(actualString);
    }

    @AfterStep
	public void addScreenshot(Scenario scenario){

          //validate if scenario has failed
		if(scenario.isFailed()) {
			final byte[] screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot2, "image/png", "imageError"); 
		}else{
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
		
	}

    @After
	public void tearDown() throws Exception 
    {
		if(driver != null){
            driver.quit();
        }
	}

}
