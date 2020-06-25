package Listeners;

import org.openqa.selenium.remote.Augmenter;

public class Utils {
  package com.mint.atf.core;

import io.qameta.allure.Attachment;
import io.qameta.allure.Link;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.mint.atf.core.DriverMaster.Ip;

  public class Utils {
    private Utils() {}

    /**
     * Make screenshot for Allure report
     *
     * @return byte array of file
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] addScreenshot() {
      return ((TakesScreenshot) new Augmenter()
              .augment(DriverMaster.getInstance()
                      .getDriverInstance())).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Attach video to Allure report
     *
     * @return byte array of file
     */
   /* @Attachment(value = "Video", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] addVideo(String sessionId) {
      byte[] array = new byte[0];
      try {
        URL url = new URL(Ip + "/video/"
                + sessionId
                + ".mp4");
        DriverMaster.getInstance().stopDriver();

        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        while (currentTime < (startTime + 300000L)) {
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setDoOutput(true);
          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
          connection.setRequestMethod("GET");
          connection.connect();

          if (connection.getResponseCode() == 200) {


            InputStream in = connection.getInputStream();
            ByteArrayOutputStream byteStr = new ByteArrayOutputStream();
            byte[] buf = new byte[512];
            while (true) {
              int len = in.read(buf);
              if (len == -1) {
                break;
              }
              byteStr.write(buf, 0, len);
            }
            in.close();
            byteStr.close();

            array = byteStr.toByteArray();

            connection.connect();

            connection.disconnect();
            break;
          }
          currentTime = System.currentTimeMillis();
          connection.disconnect();
        }
        deleteSelenoidVideo(sessionId);
        return array;
      } catch (IOException e) {
        Reporter.log("Fail to upload Selenoid Video " + e);
        return array;
      }
    }

    *//**
     * Add string value to Allure report
     *
     * @return value of that string
     *//*
    @Link(value = "{0}", url = "{1}")
    @Attachment(value = "{0}", type = "text/html", fileExtension = "html")
    public static String addString(String stringName, String value) {
      return value;
    }

    public static String createPieChart(int successI, int failedI, int skippedI, String path) {

      String failed = "Failed: " + failedI + " (" + (failedI * 100 / (failedI + successI + skippedI)) + "%)";
      String success = "Success: " + successI + " (" + (successI * 100 / (failedI + successI + skippedI)) + "%)";
      String skipped = "Skipped: " + skippedI + " (" + (skippedI * 100 / (failedI + successI + skippedI)) + "%)";

      DefaultPieDataset defaultCategoryDataset = new DefaultPieDataset();

      defaultCategoryDataset.setValue(failed, failedI);
      defaultCategoryDataset.setValue(success, successI);
      defaultCategoryDataset.setValue(skipped, skippedI);

      JFreeChart jFreeChart = ChartFactory.createPieChart3D(
              "Test statistic",  //pie chart title
              defaultCategoryDataset, //pie chart dataset
              false, false, false);  //pie chart> legend, tooltips and urls
      PiePlot3D plot = ((PiePlot3D) jFreeChart.getPlot());
      plot.setSectionPaint(failed, ChartColor.VERY_DARK_RED);
      plot.setSectionPaint(success, ChartColor.VERY_DARK_GREEN);
      plot.setSectionPaint(skipped, ChartColor.DARK_YELLOW);
      try {
        ChartUtils.saveChartAsJPEG(new File(path), jFreeChart, 600, 400);
      } catch (IOException e) {
        Logger.getLogger(Utils.class).error(e);
        Reporter.log("IOException while creating pie chart");
      }
      return path;
    }

    public static String getSessionId() {
      RemoteWebDriver driver = DriverMaster.getInstance().getDriverInstance();
      return driver.getSessionId().toString();
    }

    public static void deleteSelenoidVideo(String sessionId) {
      try {
        URL url = new URL(Ip + "/video/"
                + sessionId
                + ".mp4");
        DriverMaster.getInstance().stopDriver();
        long startTime = System.currentTimeMillis() + 3000L;
        long currentTime = System.currentTimeMillis();
        while (currentTime < startTime) {
          HttpURLConnection deleteConn = (HttpURLConnection) url.openConnection();
          deleteConn.setDoOutput(true);
          deleteConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
          deleteConn.setRequestMethod("DELETE");
          deleteConn.connect();
          if (deleteConn.getResponseCode() == 200) {
            deleteConn.disconnect();
            break;
          }
          currentTime = System.currentTimeMillis();
          deleteConn.disconnect();
        }
      } catch (IOException e) {
        Reporter.log("Fail to delete Selenoid Video");
      }
    }


  }
  de() == 200) {
*//*					System.out.println("deleteSelenoidVideo: " + url);
					System.out.println(deleteConn.getResponseCode() + " " + deleteConn.getResponseMessage());*//*
    deleteConn.disconnect();
    break;
  }
  currentTime = System.currentTimeMillis();
				deleteConn.disconnect();
}
		} catch (IOException e) {
            System.out.println("Fail to delete Selenoid Video");
            }
            }


            }
*/

            }
