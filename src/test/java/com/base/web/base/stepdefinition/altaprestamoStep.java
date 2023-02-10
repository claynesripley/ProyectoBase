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

public class altaprestamoStep {
    
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

    @And("El usuario ingresa a consumo banco ripley")
    public void ingresarConsumo() throws Throwable {
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
        driver.findElement(By.id("treestop9")).click();
        driver.findElement(By.xpath("//*[@id='r9']/td[4]/a/img")).click();
        driver.switchTo().parentFrame();
    }

    @And("El usuario crea un prestamo ya")
    public void creaCuentaSimple() throws Throwable {
        WebElement iframe = driver.findElement(By.xpath("/html/frameset/frameset[2]/frameset[2]/frame[2]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//*[@id='r2']/td[3]/a/img")).click();
        driver.switchTo().parentFrame();
    }

    @And("El usuario ingresa datos de cliente {string}, marca virtual y personal para prestamo")
    public void inputDatos(String ejecutivo) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("fieldName:PRIMARY.OFFICER")));
        driver.findElement(By.id("fieldName:PRIMARY.OFFICER")).sendKeys(ejecutivo);
        driver.findElement(By.xpath("/html/body/div[5]/fieldset[3]/div/div/form[1]/div[3]/table/tbody/tr[2]/td/table/tbody/tr[8]/td[3]/table/tbody/tr/td[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[5]/fieldset[3]/div/div/form[1]/div[3]/table/tbody/tr[2]/td/table/tbody/tr[9]/td[3]/table/tbody/tr/td[2]/input")).click();

    }

    @And("El usuario captura la cuenta prestamo")
    public void capturarCuenta() throws Throwable {
        String cod = driver.findElement(By.id("disabled_ACCOUNT.REFERENCE")).getText();
        System.out.println("CUENTA : " +cod); 
        String arreglo = driver.findElement(By.id("disabled_ARRANGEMENT")).getText();
        System.out.println("ARREGLO : " +arreglo); 
    }

    @And("El usuario ingresa monto {string}")
    public void inoutMonto(String monto) throws Throwable {
        driver.findElement(By.id("fieldName:AMOUNT")).sendKeys(monto);
    }

    @And("El usuario ingresa fechamaduracion {string}")
    public void inputFechaMaduracion(String fechamaduracion) throws Throwable {
        driver.findElement(By.id("fieldName:MATURITY.DATE")).sendKeys(fechamaduracion);
    }

    @And("El usuario ingresa tasas {string}")
    public void inputtarifa1(String tarifa1) throws Throwable {
        driver.findElement(By.id("fieldName:FIXED.RATE:1")).sendKeys(tarifa1);
        driver.findElement(By.xpath("/html/body/div[5]/fieldset[7]/div/div[3]/div/div/form[1]/div[3]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[3]/input")).sendKeys(tarifa1);

    }

    @And("El usuario ingresa fechastart {string}")
    public void inputfechastart(String fechastart) throws Throwable {
        driver.findElement(By.id("fieldName:START.DATE:2:1")).sendKeys(fechastart);
    }

    @And("El usuario marca recibido y acepta prestamo {string}")
    public void accpet(String documento) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("warningChooser:Ha recibido Contrato de Préstamo/AAA*203 de "+documento+"")));
        Select selectProducto = new Select(driver.findElement(By.id("warningChooser:Ha recibido Contrato de Préstamo/AAA*203 de "+documento+"")));
		selectProducto.selectByVisibleText("RECEIVED");
    }

    @Then("Se muestra el codigo de transacción de alta prestamo")
    public void assertAlta() throws Throwable {
       String cod = driver.findElement(By.xpath("//*[@id='messages']/tbody/tr[2]/td[2]/table[2]/tbody/tr/td")).getText();

       System.out.println("este es : " +cod); 
       String cod2 = "ALTA PRESTAMO";
        String[] datoWrite = {cod,cod2}; 
        //Escriba los datos a llenar 
        objExcelFile.writeToExcel(datoWrite);
    }





    @And("El usuario da click en Buscar Prestamo")
    public void buscarCliente() throws Throwable {
    	driver.findElement(By.xpath("//a[contains(text(),'Buscar Préstamo ')]")).click();
        driver.switchTo().parentFrame();
    }

    @Then("Se validan los datos de prestamo")
    public void validarDatos() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//th[contains(text(),'Estado')]")));
        Thread.sleep(2000);
    }







    @After
	public void tearDown() throws Exception 
    {
		if(driver != null){
            driver.quit();
        }
	}

}
