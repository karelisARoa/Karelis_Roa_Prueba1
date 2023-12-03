package cps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;


import java.util.concurrent.TimeUnit;


public class CasosDePruebaTests {
    //Atributos
    WebDriver driver;

    @BeforeEach
    public void preCondiciones() {
        //Instanciar objetos para emular el browser
        driver = new ChromeDriver();

        //Crear el ejecutor de javascript para el scrolling via evento
        driver.get("https://apimarket.bci.cl/es/home");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void postCondiciones() {
        driver.quit();
    }


    @Test
    public void CP001_Login_Fallido() throws InterruptedException {

        By localizadorBtnRegistrarse = By.xpath("//a[contains(text(),'Iniciar Sesión')]");
        //Elemento Web (Botón, txt, dropDownList, label, link, etc)
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-name']")).sendKeys("Karelis");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys("1234");
        Thread.sleep(2000);

        By localizadorBtnIniciar = By.xpath("//button[@type=\"submit\"]");
        WebElement btnIniciar = driver.findElement(localizadorBtnIniciar);
        btnIniciar.click();
        Thread.sleep(2000);

        String resultadoMSG = driver.findElement(By.xpath("//*[@id=\"page\"]/div[1]/aside/div[2]/div")).getText();
        String[] salida = resultadoMSG.split("\\n");
        System.out.println(salida[2]);
        String resultadoEsperado = "Usuario o contraseña no reconocidos. ¿Olvidaste tu contraseña?";
        Assertions.assertEquals(salida[2], resultadoEsperado);
        Thread.sleep(6000);
    }

    @Test
    public void CP002_Login_Exitoso() throws InterruptedException {
        By localizadorBtnRegistrarse = By.xpath("//a[contains(text(),'Iniciar Sesión')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-name']")).sendKeys("KarelisProd2");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys("Bci2022.");
        Thread.sleep(2000);

        By localizadorBtnIniciar = By.xpath("//button[@type=\"submit\"]");
        WebElement btnIniciar = driver.findElement(localizadorBtnIniciar);
        btnIniciar.click();
        Thread.sleep(2000);

        String resutaldoActual = driver.getCurrentUrl();
        String resultadoEsperado = "https://apimarket.bci.cl/es/user/2328/apps";

        Assertions.assertEquals(resutaldoActual, resultadoEsperado);

        Thread.sleep(6000);

    }

    @Test
    public void CP003_Cambiar_Calve() throws InterruptedException {
        By localizadorBtnRegistrarse = By.xpath("//a[contains(text(),'Iniciar Sesión')]");
        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrarse);
        btnRegistrarse.click();

        By localizadorbtnCambiarContrasena = By.xpath("//a[contains(text(),'Cambiar Contraseña')]");
        WebElement btnCambiarContrasena = driver.findElement(localizadorbtnCambiarContrasena);
        btnCambiarContrasena.click();

        driver.findElement(By.xpath("//input[@id='edit-name']")).sendKeys("Karelis.a.roa@gmail.com");
        Thread.sleep(2000);

        By localizadorbtnEnviar = By.xpath("//a[contains(text(),'Cambiar Contraseña')]");
        WebElement btnEnviar = driver.findElement(localizadorbtnEnviar);
        btnEnviar.click();
        Thread.sleep(3000);
        String resutaldoActual2 = driver.getCurrentUrl();
        String resultadoEsperado2 = "https://apimarket.bci.cl/es/user/password";

        Assertions.assertEquals(resutaldoActual2, resultadoEsperado2);

        Thread.sleep(6000);

    }

    @Test
    public void CP004_Guia_Inicio() throws InterruptedException {

        By localizadorbtnGuiaInicio = By.xpath("//a[contains(text(),'Guia de inicio')]");
        WebElement btnGuiaInicio = driver.findElement(localizadorbtnGuiaInicio);
        btnGuiaInicio.click();


        String resutaldoActualGuia = driver.getCurrentUrl();
        String resultadoEsperadoGuia = "https://apimarket.bci.cl/es/guia-inicio";

        Assertions.assertEquals(resutaldoActualGuia, resultadoEsperadoGuia);

        Thread.sleep(6000);


    }

    @Test
    public void CP005_Cambiar_Idioma_Pagina() throws InterruptedException {

        By localizadorbtnIdioma = By.xpath("//a[contains(text(),'ENG')]");
        WebElement btnIdioma = driver.findElement(localizadorbtnIdioma);
        btnIdioma.click();


        String resutaldoActual3 = driver.getCurrentUrl();
        String resultadoEsperado3 = "https://apimarket.bci.cl/en";
        //https://apimarket.bci.cl/en


        Assertions.assertEquals(resutaldoActual3,resultadoEsperado3);
        Thread.sleep(6000);

    }

}
