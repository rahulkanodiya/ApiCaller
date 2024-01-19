private void onApiSubmit() {
    try {
        // Retrieve the API URL from the text field
        String apiUrl = urlField.getText();

        // Trust all certificates (for testing purposes only)
        trustAllCertificates();

        // Make the API call and display the response
        String apiResponse = makeApiCall(apiUrl);
        responseArea.setText(apiResponse);

        // Parse the JSON response
        JSONObject jsonResponse = new JSONObject(apiResponse);
        JSONArray detailsDataArray = jsonResponse.getJSONArray("detailsdata");

        // Clear existing dynamic UI elements
        dynamicUIContainer.getChildren().clear();

        // Create buttons based on detailsdata
        for (int i = 0; i < detailsDataArray.length(); i++) {
            JSONObject detailsData = detailsDataArray.getJSONObject(i);
            JSONObject pt = detailsData.getJSONObject("pt");
            JSONObject p = detailsData.getJSONObject("p");
            JSONObject t = detailsData.getJSONObject("t");

            String buttonText = pt.getJSONObject("ptn").getString("name") +
                    p.getJSONObject("pn").getString("name") +
                    t.getJSONObject("tn").getString("name");

            Button dynamicButton = new Button(buttonText);
            dynamicUIContainer.getChildren().add(dynamicButton);

            // Set action for the dynamic button if needed
            dynamicButton.setOnAction(e -> handleDynamicButtonClick(detailsData));
        }

    } catch (Exception ex) {
        responseArea.setText("Error occurred: " + ex.getMessage());
    }
}