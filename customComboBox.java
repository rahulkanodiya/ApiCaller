import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboBoxWithID extends Application {

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
        primaryStage.setTitle("ComboBox with ID");

        // Create ComboBox with custom items
        ComboBox<CustomItem> comboBox = new ComboBox<>();
        ObservableList<CustomItem> items = FXCollections.observableArrayList(
                new CustomItem("Item 1", "id1"),
                new CustomItem("Item 2", "id2"),
                new CustomItem("Item 3", "id3")
        );
        comboBox.setItems(items);

        // Event handler for selection change
        comboBox.setOnAction(event -> {
            CustomItem selected = comboBox.getValue();
            if (selected != null) {
                System.out.println("Selected ID: " + selected.getId());
            }
        });

        // Set up the layout
        VBox root = new VBox(comboBox);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}