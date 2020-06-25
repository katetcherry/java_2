package Listeners;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestListener;
import org.testng.ITestResult;

public interface AllureAndPDFListener {
  package com.mint.atf.core.testng;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.mint.atf.core.DriverMaster;
import com.mint.atf.core.Utils;
import io.qameta.allure.listener.TestLifecycleListener;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
new datetime now
import static com.mint.atf.core.DriverMaster.Ip;
import static com.mint.atf.core.Utils.*;
import static com.mint.atf.internal.DateTimeOperations.getCurrentTimeAsString;

  /**
   * AllureAndPDFListener
   *
   * @author janaudy at jyperion dot org
   */
  public class AllureAndPDFListener implements ITestListener, IReporter, TestLifecycleListener {


    private static HashMap<String, String> sessions = new HashMap<>();

    public AllureAndPDFListener() {}

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] addScreenshot() {
      return ((TakesScreenshot) new Augmenter()
              .augment(DriverMaster.getInstance()
                      .getDriverInstance())).getScreenshotAs(OutputType.BYTES);

    @Override
    public void onTestFailure(ITestResult result) {
      String header = Utils.getSessionId();

      addScreenshot();



      String key = result.getMethod().getTestClass().getRealClass().getSimpleName()
              + "."
              + result.getMethod().getMethodName();

      sessions.put(key, header);

      Reporter.log("Finished running: " + key + ". FAIL"
              + "\nReason : " + result.getThrowable().getMessage()
              + "\nVideo: " + Ip + "/video/" + header + ".mp4", true);
    }

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> suites, String outputDirectory) {

      Table successTable = null, skippedTable = null, failTable = null, imageTable;

      HashMap<Integer, Throwable> throwableMap = new HashMap<>();
      int nbExceptions = 0;

      String filePathReport = "target/surefire-reports/pdf/";
      File tempFile = new File(filePathReport + "report.pdf");
      PdfWriter pdfWriter = null;
      tempFile.getParentFile().mkdirs();
      try {
        pdfWriter = new PdfWriter(new FileOutputStream(tempFile.getPath()));
      } catch (Exception e) {
        e.printStackTrace();
      }

      assert pdfWriter != null;
      Document document = new Document(new PdfDocument(pdfWriter), PageSize.A2.rotate());

      for (XmlSuite suite : list) {
        System.out.println(suite.toString());
      }

      for (ISuite iSuite : suites) {
        System.out.println(iSuite.toString());
      }

      Paragraph p;
      try {
        p = new Paragraph("TESTING RESULTS")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                .setFontSize(20)
                .setFontColor(new DeviceRgb(65, 105, 225));
        document.add(p);
        document.add(new Paragraph(
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                .setFontSize(14));
      } catch (IOException ignored) {
      }

      imageTable = new Table(new float[]{1f})
              .setWidth(UnitValue.createPercentValue(100))
              .setTextAlignment(TextAlignment.CENTER)
              .setHorizontalAlignment(HorizontalAlignment.CENTER)
              .setMarginBottom(20f);

      Image img = null;

      int scs = 0;
      int fld = 0;
      int sk = 0;

      IResultMap passedTests;
      IResultMap failTests;
      IResultMap skippedTests;

      for (ISuite suite : suites) {

        //Getting the results for the said suite
        Map<String, ISuiteResult> suiteResults = suite.getResults();
        for (ISuiteResult sr : suiteResults.values()) {
          ITestContext tc = sr.getTestContext();
          passedTests = tc.getPassedTests();
          scs = scs + passedTests.getAllResults().size();
          //success table
          if (scs > 0) {
            for (ITestResult result : passedTests.getAllResults()) {

              if (successTable == null) {
                successTable = new Table(new float[]{3f, 3f, 0.1f, 9f});
                successTable.setWidth(UnitValue.createPercentValue(100))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setMarginBottom(20f);
                p = null;
                try {
                  p = new Paragraph("PASSED TESTS")
                          .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
                          .setFontSize(16)
                          .setTextAlignment(TextAlignment.LEFT);
                } catch (IOException ignored) {
                }
                Cell cell = new Cell(1, 4).add(p);
                cell.setBackgroundColor(new DeviceRgb(34, 139, 34));
                successTable.addHeaderCell(cell);

                cell = new Cell().add(new Paragraph("Class"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                successTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Method"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                successTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Time (ms)"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                successTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Description"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                successTable.addHeaderCell(cell);
              }

              Cell cell = new Cell().add(new Paragraph(result.getMethod().getTestClass().getRealClass().getSimpleName()));
              successTable.addCell(cell);
              cell = new Cell().add(new Paragraph(result.getMethod().getMethodName()));
              successTable.addCell(cell);
              cell = new Cell().add(new Paragraph("" + (result.getEndMillis() - result.getStartMillis())));
              successTable.addCell(cell);
              String description = result.getMethod().getDescription();
              if (description == null) {
                description = "";
              }

              cell = new Cell().add(new Paragraph(description));
              successTable.addCell(cell);
            }
          }
          failTests = tc.getFailedTests();
          fld = fld + failTests.getAllResults().size();
          //fail table
          if (fld > 0) {
            for (ITestResult result : failTests.getAllResults()) {

              if (failTable == null) {
                failTable = new Table(new float[]{3f, 3f, 0.1f, 9f, 6f, 1f});
                failTable.setWidth(UnitValue.createPercentValue(100))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setMarginBottom(20f);
                p = null;
                try {
                  p = new Paragraph("FAILED TESTS")
                          .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
                          .setFontSize(16)
                          .setTextAlignment(TextAlignment.LEFT);
                } catch (IOException ignored) {
                }
                Cell cell = new Cell(1, 6).add(p);
                cell.setBackgroundColor(new DeviceRgb(220, 20, 60));
                failTable.addHeaderCell(cell);

                cell = new Cell().add(new Paragraph("Class"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Method"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Time (ms)"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Exception"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Description"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Video"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                failTable.addHeaderCell(cell);
              }

              Cell cell = new Cell().add(new Paragraph(result.getMethod().getTestClass().getRealClass().getSimpleName()));
              failTable.addCell(cell);
              cell = new Cell().add(new Paragraph(result.getMethod().getMethodName()));
              failTable.addCell(cell);
              cell = new Cell().add(new Paragraph("" + (result.getEndMillis() - result.getStartMillis())));
              failTable.addCell(cell);

              Throwable throwable = result.getThrowable();
              if (throwable != null) {
                throwableMap.put(throwable.hashCode(), throwable);
                nbExceptions++;

                if (throwable.getMessage() != null) {
                  String s = throwable.getMessage();
                  if (s.length() > 256) {
                    s = s.substring(0, 256);
                  }
                  Paragraph exception = new Paragraph(
                          new Link(s, PdfAction.createGoTo("1" + throwable.hashCode())));
                  exception.setProperty(Property.DESTINATION, "0" + throwable.hashCode());
                  cell = new Cell().add(exception);
                  failTable.addCell(cell);
                } else {
                  failTable.addCell(new Cell().add(new Paragraph("")));
                }
              } else {
                failTable.addCell(new Cell().add(new Paragraph("")));
              }

              String description = result.getMethod().getDescription();
              if (description == null) {
                description = "";
              }

              cell = new Cell().add(new Paragraph(description));
              failTable.addCell(cell);

              String key = result.getMethod().getTestClass().getRealClass().getSimpleName()
                      + "."
                      + result.getMethod().getMethodName();

              cell = new Cell().add(new Paragraph(
                      new Link("Video", PdfAction
                              .createURI(Ip + "/video/" + sessions.get(key) + ".mp4"))));
              failTable.addCell(cell);
            }
          }
          skippedTests = tc.getSkippedTests();
          sk = sk + skippedTests.getAllResults().size();
          //skipped table
          if (sk > 0) {
            for (ITestResult result : skippedTests.getAllResults()) {
              if (skippedTable == null) {
                skippedTable = new Table(new float[]{3f, 3f, 6f, 6f});
                skippedTable.setWidth(UnitValue.createPercentValue(100))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setMarginBottom(20f);
                p = null;
                try {
                  p = new Paragraph("SKIPPED TESTS")
                          .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
                          .setFontSize(16)
                          .setTextAlignment(TextAlignment.LEFT);
                } catch (IOException ignored) {
                }
                Cell cell = new Cell(1, 4).add(p);
                cell.setBackgroundColor(new DeviceRgb(204, 204, 0));
                skippedTable.addHeaderCell(cell);

                cell = new Cell().add(new Paragraph("Class"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                skippedTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Method"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                skippedTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Reason"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                skippedTable.addHeaderCell(cell);
                cell = new Cell().add(new Paragraph("Description"));
                cell.setBackgroundColor(new DeviceRgb(220, 220, 220));
                skippedTable.addHeaderCell(cell);
              }

              Cell cell = new Cell().add(new Paragraph(result.getMethod().getTestClass().getRealClass().getSimpleName()));
              skippedTable.addCell(cell);
              cell = new Cell().add(new Paragraph(result.getMethod().getMethodName()));
              skippedTable.addCell(cell);
              Throwable throwable = result.getThrowable();
              if (throwable != null) {
                String s = result.getThrowable()
                        .getMessage();
                s = s.replaceAll("\\[.*]", "")
                        .replaceAll("Build info:((.|\\n)*)", "");
                if (s.length() > 300) {
                  s = s.substring(0, 300);
                }
                cell = new Cell().add(new Paragraph(s));
              } else {
                cell = new Cell().add(new Paragraph(""));
              }
              skippedTable.addCell(cell);
              String description = result.getMethod().getDescription();
              if (description == null) {
                description = "";
              }

              cell = new Cell().add(new Paragraph(description));
              skippedTable.addCell(cell);
            }
          }
        }
      }

      try {
        img = new Image(ImageDataFactory
                .create(Utils
                        .createPieChart(scs, fld, sk, "surefire-reports/pdf/graph.jpg")))
                .setAutoScale(true);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }

      imageTable.addCell(new Cell()
              .setHorizontalAlignment(HorizontalAlignment.CENTER)
              .setBorder(Border.NO_BORDER)
              .add(img));

      document.add(imageTable);

      if (failTable != null) {
        p = null;
        try {
          p = new Paragraph("Video links valid only for two days!")
                  .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC))
                  .setFontSize(21)
                  .setFontColor(new DeviceRgb(255, 0, 0))
                  .setTextAlignment(TextAlignment.CENTER);
        } catch (IOException ignored) {
        }
        document.add(p);
        document.add(failTable);
      }

      if (skippedTable != null) {
        document.add(skippedTable);
      }

      if (successTable != null) {
        document.add(successTable);
      }

      if (failTable != null) {
        p = null;
        try {
          p = new Paragraph("EXCEPTIONS SUMMARY")
                  .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                  .setFontSize(14)
                  .setFontColor(new DeviceRgb(255, 0, 0));
        } catch (IOException ignored) {
        }
        document.add(p);

        Set<Integer> keys = throwableMap.keySet();

        assert keys.size() == nbExceptions;

        for (Integer key : keys) {
          Throwable throwable = throwableMap.get(key);

          Link chunk = new Link(throwable.toString(), PdfAction.createGoTo("0" + throwable.hashCode()));
          Paragraph throwTitlePara = new Paragraph(chunk);
          throwTitlePara.setProperty(Property.DESTINATION, "1" + throwable.hashCode());
          try {
            throwTitlePara.setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD));
          } catch (IOException ignored) {
          }
          document.add(throwTitlePara);
          StackTraceElement[] trace = throwable.getStackTrace();
          for (StackTraceElement ste : trace) {
            Paragraph throwParagraph = new Paragraph(ste.toString());
            document.add(throwParagraph);
          }
        }
      }

      document.close();

      Path copied = Paths.get(filePathReport + DriverMaster.BROWSER_NAME + "_report.pdf");
      Path originalPath = tempFile.toPath();
      try {
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(originalPath);
      } catch (IOException ignored) {
      }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }
  }

}
