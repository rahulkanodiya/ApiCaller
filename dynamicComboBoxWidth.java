import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinalMergedExample extends Application {

    private TextField cIdField;
    private TextField wsIdField;
    private TextField eIdField;
    private TextField urlField;
    private TextArea responseArea;
    private TextField eceIdField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Merged Example");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);

        // Create components
        cIdField = new TextField();
        wsIdField = new TextField();
        eIdField = new TextField();
        urlField = new TextField();
        Button submitButton = new Button("Submit");
        responseArea = new TextArea();
        eceIdField = new TextField();
        eceIdField.setEditable(false);

        // Set up layout
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        // Top section with C_ID, WS_ID, and E_ID fields
        HBox topSection = new HBox(10);
        topSection.getChildren().addAll(
                new Label("C_ID:"), cIdField,
                new Label("WS_ID:"), wsIdField,
                new Label("E_ID:"), eIdField
        );
        layout.setTop(topSection);

        // Middle section with API URL and Submit button
        HBox middleSection = new HBox(10);
        middleSection.getChildren().addAll(
                new Label("API URL:"), urlField,
                submitButton
        );
        HBox.setMargin(cIdField, new Insets(0, 10, 0, 0));
        HBox.setMargin(wsIdField, new Insets(0, 10, 0, 0));
        HBox.setMargin(eIdField, new Insets(0, 10, 0, 0));
        HBox.setMargin(urlField, new Insets(0, 10, 0, 0));
        HBox.setMargin(submitButton, new Insets(0, 0, 0, 10));
        layout.setCenter(middleSection);

        // Bottom section with response area and ECE_ID field
        responseArea.setWrapText(true);
        responseArea.setPrefHeight(150);
        layout.setBottom(responseArea);

        // VBox for dynamic ComboBox
        VBox dynamicUIContainer = new VBox(10); // Set spacing between items
        dynamicUIContainer.setPadding(new Insets(10));

        // Create ComboBox and add it to the VBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Short", "A longer item", "The longest item in the ComboBox");

        dynamicUIContainer.getChildren().add(comboBox);

        // Set the preferred width based on the length of the longest item
        double longestItemWidth = computeLongestItemWidth(comboBox);
        comboBox.setPrefWidth(longestItemWidth);

        layout.setLeft(dynamicUIContainer);

        // Add action listener to the submit button
        submitButton.setOnAction(e -> onApiSubmit());

        // Set up the scene and show the stage
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onApiSubmit() {
        // Your existing onApiSubmit logic here
    }

    private double computeLongestItemWidth(ComboBox<String> comboBox) {
        double maxWidth = 0;

        for (String item : comboBox.getItems()) {
            double textWidth = computeTextWidth(item, comboBox.getFont());
            maxWidth = Math.max(maxWidth, textWidth);
        }

        // Add some padding to the width (adjust as needed)
        double padding = 20;
        return maxWidth + padding;
    }

    private double computeTextWidth(String text, javafx.scene.text.Font font) {
        javafx.scene.text.Text helper = new javafx.scene.text.Text();
        helper.setFont(font);
        helper.setText(text);
        return helper.getLayoutBounds().getWidth();
    }

    public static void main(String[] args) {
        launch(args);
    }
}