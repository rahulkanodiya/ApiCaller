import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PutRequestWithJsonExample {

    public static void main(String[] args) {
        try {
            String apiUrl = "https://www.abcz.com";
            String jsonBody = "{\"id\": \"123\", \"cvg\": \"someValue\"}";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            // Set the Content-Type header to indicate JSON data
            connection.setRequestProperty("Content-Type", "application/json");

            // Write the JSON data to the request body
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Process the successful response
            } else {
                // Handle errors
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}