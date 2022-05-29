package com.automationTest.utilites.API;

import com.automationTest.utilites.IO.IOService;
import com.automationTest.utilites.IO.ScreenshotService;
import com.automationTest.utilites.reports.ExtentReportService;
import com.automationTest.utilites.reports.LogsService;
import com.aventstack.extentreports.Status;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class API {
    String strResultJSON = IOService.getPathFromResource("Autohotkey/ResultAPI.txt");

    //Simulation API
    public void hitAPIProcessOrder(Map<String, Object> mapInfoTesting,
                                   Map<String, Object> mapParametersBody,
                                   Map<String, Object> mapExpectedResponse,
                                   Map<String, Object> mapExpectedBody,
                                   ExtentReportService extentReportService){

        //Create request hit API
        LogsService.appendLog(Status.INFO.toString(), "Create Rest Assure", extentReportService, "");
        RequestSpecification requestSpecification = RestAssured.given();
        JSONObject requestParams = new JSONObject(mapParametersBody);
        requestParams.put("last_updated_timestamp",System.currentTimeMillis() / 1000L);

        IOService.createTextFile(strResultJSON, prettyJSON(requestParams));
        LogsService.appendLog(Status.INFO.toString(), "Request Body :\n " + prettyJSON(requestParams), extentReportService, ScreenshotService.screenShotNotepadAHK());

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(prettyJSON(requestParams));
        Response response = requestSpecification.when().post(mapInfoTesting.get("URL").toString());

        //Put to txt for Screenshot
        IOService.createTextFile(strResultJSON, response.asPrettyString());
        LogsService.appendLog(Status.INFO.toString(), "Responses : \n" + response.getBody().asPrettyString(), extentReportService, ScreenshotService.screenShotNotepadAHK());

        testResponseCode(response, mapExpectedResponse, extentReportService);
        testCompareBody(response, mapExpectedBody, mapExpectedResponse, extentReportService);

    }
    public void hitAPIGet(Map<String, Object> mapInfoTesting,
                          Map<String, Object> mapExpectedResponse,
                          Map<String, Object> mapExpectedBody,
                          ExtentReportService extentReportService){

        //Create request hit API
        LogsService.appendLog(Status.INFO.toString(), "Create Rest Assure", extentReportService, "");
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.when().get(mapInfoTesting.get("URL").toString());

        //Put to txt for Screenshot
        IOService.createTextFile(strResultJSON, response.asPrettyString());
        LogsService.appendLog(Status.INFO.toString(), "Responses : \n" + response.getBody().asPrettyString(), extentReportService, ScreenshotService.screenShotNotepadAHK());

        testResponseCode(response, mapExpectedResponse, extentReportService);
        testCompareBody(response, mapExpectedBody, mapExpectedResponse, extentReportService);
    }
    public void hitAPIPost(Map<String, Object> mapInfoTesting,
                           Map<String, Object> mapParametersBody,
                           Map<String, Object> mapExpectedResponse,
                           Map<String, Object> mapExpectedBody,
                           ExtentReportService extentReportService) {

        //Create request hit API
        LogsService.appendLog(Status.INFO.toString(), "Create Rest Assure", extentReportService, "");
        RequestSpecification requestSpecification = RestAssured.given();
        JSONObject requestParams = new JSONObject(mapParametersBody);

        IOService.createTextFile(strResultJSON, prettyJSON(requestParams));
        LogsService.appendLog(Status.INFO.toString(), "Request Body :\n " + prettyJSON(requestParams), extentReportService, ScreenshotService.screenShotNotepadAHK());

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(prettyJSON(requestParams));
        Response response = requestSpecification.when().post(mapInfoTesting.get("URL").toString());

        //Put to txt for Screenshot
        IOService.createTextFile(strResultJSON, response.asPrettyString());
        LogsService.appendLog(Status.INFO.toString(), "Responses : \n" + response.getBody().asPrettyString(), extentReportService, ScreenshotService.screenShotNotepadAHK());

        testResponseCode(response, mapExpectedResponse, extentReportService);
        testCompareBody(response, mapExpectedBody, mapExpectedResponse, extentReportService);

    }



    private void testResponseCode(Response response, Map<String, Object> mapExpectedRespones, ExtentReportService extentReportService) {
        if (response.getStatusCode() == Integer.parseInt(mapExpectedRespones.get("responseCode").toString()))
            LogsService.appendLog(Status.PASS.toString(), "Status Code Actual: " + response.getStatusCode() + " Status Code Expected:" + mapExpectedRespones.get("responseCode") + ". ", extentReportService, ScreenshotService.screenShotNotepadAHK());
        else
            LogsService.appendLog(Status.FAIL.toString(), "Status Code Actual: " + response.getStatusCode() + " Status Code Expected:" + mapExpectedRespones.get("responseCode") + ". ", extentReportService, ScreenshotService.screenShotNotepadAHK());
    }

    private void testCompareBody(Response response, Map<String, Object> mapExpectedBody, Map<String, Object> mapResponse, ExtentReportService extentReportService) {
        try {
            JSONObject requestExpected = new JSONObject(mapExpectedBody);
            JSONObject requestResponse =  new JSONObject(response.getBody().asPrettyString());

            if (prettyJSON(requestResponse).equalsIgnoreCase(prettyJSON(requestExpected))) {
                LogsService.appendLog(Status.PASS.toString(), "Response is the same as expectation", extentReportService, ScreenshotService.screenShotNotepadAHK());
            } else {
                requestExpected = new JSONObject();
                requestExpected.put("error", mapResponse.get("error"));

                IOService.createTextFile(strResultJSON, prettyJSON(requestExpected));
                if (prettyJSON(requestResponse).equalsIgnoreCase(prettyJSON(requestExpected)))
                    LogsService.appendLog(Status.PASS.toString(), "Response error is the same as expectation", extentReportService, ScreenshotService.screenShotNotepadAHK());
                else
                    LogsService.appendLog(Status.FAIL.toString(), "Response didn't same expected", extentReportService, ScreenshotService.screenShotNotepadAHK());
            }
        }catch (JSONException e){
            LogsService.appendLog(Status.FAIL.toString(), "Response Failed", extentReportService, ScreenshotService.screenShotNotepadAHK());

        }

    }

    private String prettyJSON(JSONObject json){
        return new GsonBuilder().setPrettyPrinting().create().toJson(json.toMap());
    }



}
