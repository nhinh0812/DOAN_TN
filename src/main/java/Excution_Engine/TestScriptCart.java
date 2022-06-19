package Excution_Engine;

import static Utilities.ExtentManager.getExtentReports;

import org.junit.Test;

import com.aventstack.extentreports.Status;

import Utilities.Log;

import Utilities.ActionKeywords;
import Utilities.ExcelUtils;
import Utilities.ExtentTestManager;

public class TestScriptCart {
	@Test
	public void excute_TestCaseCart() throws Exception {
		Log.info("============Chạy testcase giỏ hàng==========");
		String sPath = System.getProperty("user.dir") + "//src//main//java//Data_Engine//Data_Cart.xlsx";
		ExcelUtils.setExcelFile(sPath, "Cart");
		int CasePass = 0;
		int CaseFail = 0;
		int CaseSkip = 0;
		for (int iRow = 1; iRow <= ExcelUtils.getRowCount("Cart"); iRow++) {
			
				System.out.println(iRow);
				String TestCaseId = ExcelUtils.getCellData(iRow, 0);//lấy id
	            String description = ExcelUtils.getCellData(iRow, 2);
				String sActionKeyword = ExcelUtils.getCellData(iRow, 3);
				String locatorType = ExcelUtils.getCellData(iRow, 6);

				String locatorValue = ExcelUtils.getCellData(iRow, 7);
				String testData = ExcelUtils.getCellData(iRow, 8);

				if (TestCaseId != null && TestCaseId != "") {
	                Log.info("------------ Thực thi Test Case ID:" + TestCaseId + "----------");
	                ExtentTestManager.saveToReport(TestCaseId, description);
	            }
				switch (sActionKeyword) {
				case "openBrowser":
					ActionKeywords.openBrowser(testData);
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "move":
					ActionKeywords.ElementPerfom(locatorValue);
					break;
				case "navigate":
					ActionKeywords.navigate(testData);
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "scroll":
					ActionKeywords.scroll();
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "clickSP":
					ActionKeywords.clickElement(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "clickAddtocart":
					ActionKeywords.clickElement(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "clickCart":
					ActionKeywords.clickElement(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				case "Refresh":
					ActionKeywords.Refresh();
					break;
				case "verifyLabel":
					if (ActionKeywords.verifyLabel(locatorType, locatorValue, testData)) {
						Log.info("=========================");
						Log.info("Same result ---> pass");
						Log.info("=========================");
						ExtentTestManager.logMessage(Status.INFO, description);
						CasePass++;
					} else {
						Log.info("=========================");
						Log.info("Different result ---> Fail");
						Log.info("=========================");
						ExtentTestManager.logMessage(Status.FAIL, description);
						CaseFail++;
					}
					break;
				case "quitBrowser":
					ActionKeywords.quitDriver();
					ExtentTestManager.logMessage(Status.INFO, description);
					break;
				default:
					Log.error("|Keyword Not Found| " + sActionKeyword);
				}
			}
		getExtentReports().flush();
		java.util.Date date = new java.util.Date();
		Log.info("==========================================================");
		Log.info("-----------" + date + "--------------");
		Log.info("Total number of Testcases run: " + (CasePass + CaseFail + CaseSkip));
		Log.info("Total number of passed Testcases: " + CasePass);
		Log.info("Total number of failed Testcases: " + CaseFail);
		Log.info("Total number of skip Testcases: " + CaseSkip);
		Log.info("==========================================================");
		}
}
