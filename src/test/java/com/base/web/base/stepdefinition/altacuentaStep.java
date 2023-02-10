package com.base.web.base.stepdefinition;

import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.web.base.base.ThreadLocalDriver;
import com.base.web.base.base.WebAppFactory;
import com.base.web.base.base.writeExcel;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class altacuentaStep {

    public WebDriver driver = null;
    WebAppFactory appWebFactory;
    writeExcel objExcelFile = new writeExcel();
    String arreglo ;

    @Before
    public void setup() throws Throwable{
        appWebFactory = new WebAppFactory();
        driver = ThreadLocalDriver.getTLWebDriver();
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started WebExampleStep in thread: " + threadId + ", in JVM: " + processName);
    }

    @And("El usuario da click en Catalogo de Productos")
    public void clickCtalogo() throws Throwable {
        Thread.sleep(1000);
    	
        driver.findElement(By.xpath("//a[contains(text(),'Catálogo de Productos ')]")).click();
        driver.switchTo().parentFrame();

    }

    @And("El usuario ingresa a cuentas de ahorros")
    public void ingresaCuentaAhorro() throws Throwable {
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
        driver.manage().window().maximize();
        WebElement iframe = driver.findElement(By.xpath("/html/frameset/frameset[2]/frameset[1]/frame[2]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id("treestop1")).click();
        driver.findElement(By.xpath("//*[@id='r1']/td[4]/a/img")).click();
        driver.switchTo().parentFrame();
    }

    @And("El usuario crea una cuenta simple")
    public void creaCuentaSimple() throws Throwable {
        WebElement iframe = driver.findElement(By.xpath("/html/frameset/frameset[2]/frameset[2]/frame[2]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//*[@id='r4']/td[3]/a/img")).click();
        driver.switchTo().parentFrame();
    }

    @And("El usuario ingresa documento {string} y moneda")
    public void inputDocumento(String documento) throws Throwable {
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

        driver.manage().window().maximize();
        driver.findElement(By.id("fieldName:CUSTOMER:1")).sendKeys(documento);
        driver.findElement(By.id("fieldName:CURRENCY")).sendKeys("PEN");

    
    }

    @And("El usuario prevalida")
    public void prevalidar() throws Throwable {
        driver.findElement(By.xpath("//img[@alt='Validate a deal']")).click();
    }

    @And("El usuario ingresa datos de cliente {string}, marca virtual y personal")
    public void inputDatos(String ejecutivo) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("fieldName:PRIMARY.OFFICER")));
        driver.findElement(By.id("fieldName:PRIMARY.OFFICER")).sendKeys(ejecutivo);
        driver.findElement(By.xpath("/html/body/div[5]/fieldset[4]/div/div/form[1]/div[3]/table/tbody/tr[3]/td/table[1]/tbody/tr[11]/td[3]/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[5]/fieldset[4]/div/div/form[1]/div[3]/table/tbody/tr[3]/td/table[1]/tbody/tr[12]/td[3]/table/tbody/tr/td[2]/input")).click();

    }

    @And("El usuario captura la cuenta")
    public void capturarCuenta() throws Throwable {
        String cod = driver.findElement(By.id("disabled_ACCOUNT.REFERENCE")).getText();
        System.out.println("CUENTA : " +cod); 
        arreglo = driver.findElement(By.id("disabled_ARRANGEMENT")).getText();
        System.out.println("ARREGLO : " +arreglo); 
        
    }

    @And("El usuario hace commit")
    public void commit() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Commit the deal']")));
        driver.findElement(By.xpath("//img[@alt='Commit the deal']")).click();
    }

    @And("El usuario marca recibido y acepta {string}")
    public void accpet(String documento) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("warningChooser:Ha recibido Account Opening Agreement/AAA*212 de "+documento+"")));
        Select selectProducto = new Select(driver.findElement(By.id("warningChooser:Ha recibido Account Opening Agreement/AAA*212 de "+documento+"")));
		selectProducto.selectByVisibleText("RECEIVED");
        driver.findElement(By.id("errorImg")).click();

    }

    @Then("Se muestra el codigo de transacción alta cuenta")
    public void assertAlta() throws Throwable {
       //String cod = driver.findElement(By.id("transactionId")).getCssValue("value");
       String cod = driver.findElement(By.xpath("//*[@id='messages']/tbody/tr[2]/td[2]/table[2]/tbody/tr/td")).getText();
       String cod2 = "ALTA CUENTA";
       System.out.println("este es : " +cod); 
        String[] datoWrite = {cod,cod2}; 
        //Escriba los datos a llenar 
        objExcelFile.writeToExcel(datoWrite);
    }









    @And("El usuario da click en Buscar Cuenta")
    public void buscarCliente() throws Throwable {
    	driver.findElement(By.xpath("//a[contains(text(),'Buscar Cuenta ')]")).click();
        driver.switchTo().parentFrame();
    }
    @And("El usuario ingresa el arreglo a buscar {string}")
    public void inputDni(String arr) throws Throwable {
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("value:1:1:1")));
        driver.findElement(By.id("value:1:1:1")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("value:2:1:1")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("value:2:1:1")).sendKeys(arr);
    	driver.findElement(By.xpath("//a[@alt='Run Selection']")).click();
    }
    @Then("Se validan los datos de cuenta")
    public void validarDatos() throws Throwable {
    	driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/div[3]/div/form/div/table/tbody/tr[2]/td[2]/div[2]/div/table[1]/tbody/tr[1]/td[7]/a/img")).click();
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

        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'Authorised')]")));
        Thread.sleep(6000);
    }

    @After
	public void tearDown() throws Exception 
    {
		if(driver != null){
            driver.quit();
        }
	}
}
