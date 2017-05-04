/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageAdministrator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import configuration.Configuration;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import message.Message;

/**
 *
 * @author emanuel
 */
public class Sender {

    private final String URL = Configuration.getInstance().getProperty(Configuration.SERVER_PUBLISH);

    public Sender() {

    }

    
    public void sendMessage(Message msg) {
        try {
            Gson gson = new Gson();
            String jsonObject = gson.toJson(msg);            
            
            URL obj = new URL(URL);
            
            HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect();

//            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
//            wr.writeBytes(jsonObject.toString());
//            wr.flush();
//            wr.close();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            // Print response
            System.out.println("Response " + response.toString());

        } catch (MalformedURLException ex) {
            System.err.println("La URL esta mal formada " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("No se pudo enviar el requerimiento HTTP " + ex.getMessage());
        }
    }

}
