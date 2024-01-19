import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApiCallerJavaFX extends Application {

    private TextField urlField;
    private TextArea responseArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the primary stage
        primaryStage.setTitle("API Caller");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);

        // Create components
        urlField = new TextField();
        Button submitButton = new Button("Submit");
        responseArea = new TextArea();

        // Set up layout
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setTop(new Label("API URL: "));
        layout.setCenter(urlField);
        layout.setRight(submitButton);
        layout.setBottom(responseArea);

        // Add action listener to the submit button
        submitButton.setOnAction(e -> onApiSubmit());

        // Set up the scene and show the stage
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onApiSubmit() {
        try {
            // Retrieve the API URL from the text field
            String apiUrl = urlField.getText();

            // Trust all certificates (for testing purposes only)
            trustAllCertificates();

            // Make the API call and display the response
            String apiResponse = makeApiCall(apiUrl);
            responseArea.setText(apiResponse);
        } catch (Exception ex) {
            responseArea.setText("Error occurred: " + ex.getMessage());
        }
    }

    private void trustAllCertificates() throws Exception {
        // Trust all certificates (for testing purposes only)
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new SecureRandom());

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }

    private String makeApiCall(String apiUrl) {
        try {
            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Close the connection
            connection.disconnect();

            // Display the response in the text area
            return "Response Code: " + responseCode + "\n\n" + response.toString();
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}