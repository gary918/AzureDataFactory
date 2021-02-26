package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.StorageAccount;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.OutputBinding;

import java.util.Optional;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    @StorageAccount("Storage_Account_Connection_String")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @BlobOutput(
                name = "target", 
                path = "data/function.json")
                OutputBinding<String> outputItem,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        //final String query = request.getQueryParameters().get("name");
        //final String name = request.getBody().orElse(query);

        // Call REST API
        System.setProperty("java.net.preferIPv4Stack", "true");
        String path = System.getenv("Rest_API_Path");
        String responseStr ="";
        boolean success = true;
        String errMsg = "";
        try{
            //URL url = new URL(null, path, new sun.net.www.protocol.https.Handler());
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Ocp-Apim-Subscription-Key", "xxxx");
            //connection.setDoOutput(true);

            StringBuilder response = new StringBuilder ();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
            response.append(line);
            }
            in.close();

            responseStr = response.toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
            success = false;
            errMsg = ex.toString();
        }

        // Save the json file into Blob Storage
        if (success){
            outputItem.setValue(responseStr);
            responseStr = "{\"status\":\"OK\"}";
        }
        else{
            responseStr = "{\"status\":\"fail:"+errMsg+"\"}";
        }

        // Return JSON object
        //return request.createResponseBuilder(HttpStatus.OK).body(responseStr).build();
        return request.createResponseBuilder(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(responseStr)
                        .build();
    }
}
