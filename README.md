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
5. Looping as much data.
6. Get all parameters, like URL, Method.
7. Get all parameters by go to sheet for Parameter Body, Expected Body, Expected Response.
8. Parameter body will be made request body.
9. Expected body to compare from response body and expected body.
10. Expected Response to compare from response and expected response, like Response code, Error message, and etc.
11. That all parameters will be accommodated in HashMap.
12. Method(post,get,etc) will be one of the conditions to determine ApiTest.
13. All HashMap will be parameters into function ApiTest.
14. Init RestAssured for hit the API.
15. Make a request body use JSONObject, and hit it. 
16. The responses will be compare with Expected body, and Expected Response.
17. Flush the extentReport.
18. On the extentReport, there will be a Screenshots(base64 img), actually I use AutoHotKey for the screenshot. 

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
   ![image](https://user-images.githubusercontent.com/12596747/170889097-a68aeff7-7e0e-432e-8bd4-04ee5ac7cf34.png)

2. I think, this framework it's not for performance testing because this is take much time, the purpose for this framework for the users(who don't know about Code) can use the automation testing easily.
