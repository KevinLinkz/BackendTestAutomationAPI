# BackendTestAutomationAPI
My Second Framework for test 1
Beside my explanation with my answer on the email, I'll explained this framework more details.
This framework use Microsoft excel for the datas and it'll make a report using Extent Report.
The purpose of this framework is for Manual Testing actually.
The reason a lot of screenshots, because it will be evidence for internal audits.

Steps:
1. Init GUI(Trigger Start, Stop, and show the logs) for Automation testing.
2. Make a folder Results.
3. Init class Extend Report, Read Datatable, and API.
4. Read Microsoft Excel sheet "Datatable", and check how much data will be run.
5. Get all parameters, like URL, Method.
6. Get all parameters by go to sheet for Parameter Body, Expected Body, Expected Response
7. Parameter body will be made request body.
8. Expected body to compare from response body and expected body.
9. Expected Response to compare from response and expected response, like Response code, Error message, and etc.
10. That all parameters will be accommodated in HashMap.
11. Method(post,get,etc) will be one of the conditions to determine ApiTest.
12. All HashMap will be parameters into function ApiTest.
13. Init RestAssured for hit the API.
14. Make a request body use JSONObject, and hit it. 
15. The responses will be compare with Expected body, and Expected Response.
16. Flush the extentReport.
17. On the extentReport, there will be a Screenshots(base64 img), actually I use AutoHotKey for the screenshot. 

Dependencies : 
1. Apache POI.
2. ExtentAPI (Extend Report)
3. JSON in java
4. Rest Assured
5. testNG

I make this framework for dynamic datas and flow. 
So the users, can put any URL API, any Scenarios, and any Expected Results into the Micorosft Excel.

Improvements would be needed:
1. This framework not cover the depth of JSON array for the Request Body or Response Body. It takes time to make perfect.
   Ex: 
   {
      "data": {        <--- this depth
          "id": 2
      },
      "support": {
          "url": "https://reqres.in/#support-heading"
      }
    } 
2. I think, this framework it's not for performance testing because this is take much time, the purpose for this framework for the users(who don't know about Code) can use the automation testing easily.
