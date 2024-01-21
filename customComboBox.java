import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class ComboBoxWithDialog extends Application {

    public static class CustomItem {
        private final String displayText;
        private final String id;

        public CustomItem(String displayText, String id) {
            this.displayText = displayText;
            this.id = id;
        }

        public String getDisplayText() {
            return displayText;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return displayText;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ComboBox with Dialog");

        // Create ComboBox with custom items
        ComboBox<CustomItem> comboBox = new ComboBox<>();
        ObservableList<CustomItem> items = FXCollections.observableArrayList(
                new CustomItem("Item 1", "id1"),
                new CustomItem("Item 2", "id2"),
                new CustomItem("Item 3", "id3")
        );
        comboBox.setItems(items);

        // Set a default value
        comboBox.setValue(items.get(0));

        // Event handler for selection change
        comboBox.setOnAction(event -> {
            CustomItem selected = comboBox.getValue();
            if (selected != null) {
                openDialog(selected);
            }
        });

        // Set up the layout
        VBox root = new VBox(comboBox);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void openDialog(CustomItem selectedItem) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Custom Dialog");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField coverageAmountField = new TextField();
        TextField dependent1Field = new TextField();

        grid.add(new Label("Coverage Amount:"), 0, 0);
        grid.add(coverageAmountField, 1, 0);
        grid.add(new Label("Dependent 1:"), 0, 1);
        grid.add(dependent1Field, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Add Save button
        ButtonType saveButtonType = new ButtonType("Save");
        dialog.getButtonTypes().addAll(ButtonType.CANCEL, saveButtonType);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                // Handle Save button click
                System.out.println("Coverage Amount: " + coverageAmountField.getText());
                System.out.println("Dependent 1: " + dependent1Field.getText());
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}