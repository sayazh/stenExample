package Tests;

import Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

public class CaymanNew extends TestBase {
    @Test
    public void buyPorshce() throws InterruptedException {
//                1. Open browser
//        2. Go to url “https://www.porsche.com/usa/modelstart/”
        driver.get("https://www.porsche.com/usa/modelstart/");


////        3. Select model 718
        driver.findElement(By.xpath("//a//div//span")).click();
//        4. Remember the price of 718 Cayman Model S
        String expectPrice = driver.findElement(By.xpath("(//div[contains(text(),'69')])[2]")).getText();//From $ 69,300.00*

//
        expectPrice=expectPrice.substring(7,13).replace(",","");
        int ExpecPrice = Integer.valueOf(expectPrice);

//        5. Click on 718 Cayman S
        driver.findElement(By.xpath("(//div[contains(text(),'69')])[2]")).click();

      //  6. Verify that Base price displayed on the page is same as the price from step 4
        String parent = driver.getWindowHandle();
//
        Thread.sleep(5000);
//
        Set<String> all = driver.getWindowHandles();
        for(String my : all){
            if(! my.equals(parent)){
                driver.switchTo().window(my);
                break;
            }
        }
        int basePrice = getPrice(driver.findElement(By.xpath("(//div[.='$69,300'])[2]")).getText());//69,300
//
        Assert.assertEquals(basePrice,ExpecPrice);
//

////        7. Verify that Price for Equipment is 0
        String equipment = driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText();//0
//
        Assert.assertTrue(equipment.contains("0"),"0");
////        8. Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
        int fee = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[7]")).getText());//1,250
        int total = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText());//70.500
        Assert.assertEquals(total,basePrice+fee);
////        9. Select color “Miami Blue”
        driver.findElement(By.id("s_exterieur_x_FJ5")).click();
//
//
////        10.Verify that Price for Equipment is Equal to Miami Blue price
        int bluePrice = getPrice(driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price"));
        int equipmentBlue = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText());//2,580
        Assert.assertEquals(bluePrice,equipmentBlue);
////        11.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        int totalBlue = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText()); //73.130
        Assert.assertEquals(totalBlue,fee+equipmentBlue+basePrice);
        Thread.sleep(1000);
//
////        12.Select 20" Carrera Sport Wheels
        driver.findElement(By.id("scrollIndicator")).click();
        Thread.sleep(2000);
//
        WebElement element = driver.findElement(By.xpath("(//span[@class='img-element'])[11]")); //20 wheels
        Thread.sleep(2000);
//
//
        actions.moveToElement(element).click(element);
        actions.perform();
        driver.findElement(By.id("s_exterieur_x_MXRD")).click();
//
//
////        13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
        int wheels = getPrice(driver.findElement(By.id("s_exterieur_x_MXRD")).getAttribute("data-price"));
        int eqWheels= getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText());
        Assert.assertEquals(eqWheels,wheels+bluePrice);
//
//
//
////        14.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
       int totalWheels = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText());
       Assert.assertEquals(totalWheels,basePrice+fee+eqWheels);
//
////   //    15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(1000);
       actions.sendKeys(Keys.PAGE_DOWN).perform();
       Thread.sleep(1000);
//
       driver.findElement(By.id("s_interieur_x_PP06")).click();
////        16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package
        int eqSeats = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText());
        int seats = getPrice(driver.findElement(By.xpath("(//*[@class='pBox'])[8]")).getText());
        Assert.assertEquals(eqSeats,seats+bluePrice+wheels);
////        17.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Thread.sleep(2000);
        int totalSeats = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText());
        Assert.assertEquals(totalSeats,basePrice+fee+eqSeats);
////        18.Click on Interior Carbon Fiber
         actions.sendKeys(Keys.PAGE_DOWN).perform();
          Thread.sleep(1000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(1000);
//
          driver.findElement(By.id("IIC_subHdl")).click();
        Thread.sleep(800);
////        19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
        driver.findElement(By.id("vs_table_IIC_x_PEKH_x_c01_PEKH")).click();
//
////        20.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior
//
          Thread.sleep(1000);
          int eqCarbon = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText());
          int Carbon = getPrice(driver.findElement(By.xpath("(//*[@class='pBox'])[117]")).getText());
          Assert.assertEquals(eqCarbon,Carbon+bluePrice+seats+wheels);
////        21.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
          Thread.sleep(1000);
          int totalCarbon = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText());
          Assert.assertEquals(totalCarbon,basePrice+fee+eqCarbon);
////        22.Click on Performance
//
        actions.sendKeys(Keys.PAGE_UP).perform();
         Thread.sleep(1000);
//
//
          driver.findElement(By.id("IMG_subHdl")).click();
         Thread.sleep(800);
////        23.Select 7-speed Porsche Doppelkupplung (PDK)
         driver.findElement(By.id("vs_table_IMG_x_M250_x_c11_M250")).click();
         int transmission = getPrice(driver.findElement(By.xpath("(//div[.='$3,210'])[2]")).getText());
///
//
////                24.Select Porsche Ceramic Composite Brakes (PCCB)
          actions.sendKeys(Keys.PAGE_DOWN).perform();
          Thread.sleep(1000);
          driver.findElement(By.id("vs_table_IMG_x_M450_x_c81_M450")).click();
          int breaks = getPrice(driver.findElement(By.xpath("(//div[.='$7,410'])[2]")).getText());
//
////        25.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
           int eqFinal = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[6]")).getText());
           Assert.assertEquals(eqFinal,transmission+breaks+bluePrice+wheels+seats+Carbon);
////        26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
           int totalFinal = getPrice(driver.findElement(By.xpath("(//*[@class='ccaPrice'])[8]")).getText());
           Assert.assertEquals(totalFinal,basePrice+fee+eqFinal);

    }

    public int getPrice(String word){
        word= word.substring(1).replace(",","");//$7,536  7536
        int result = Integer.valueOf(word);
        return result;
    }

}