import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressBarExample extends Application {

    private ProgressBar progressBar;
    private Label progressLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Progress Bar Example");

        // Create components
        progressBar = new ProgressBar(0);
        progressLabel = new Label("0%");

        Button startButton = new Button("Start Process");

        // Set up layout
        HBox layout = new HBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(progressBar, progressLabel, startButton);

        // Add action listener to the button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Simulate a time-consuming process using a Service
                MyService myService = new MyService();
                progressBar.progressProperty().bind(myService.progressProperty());

                myService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        progressLabel.setText("100%");
                    }
                });

                new Thread(myService).start();
            }
        });

        // Set up the scene and show the stage
        Scene scene = new Scene(layout, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Custom Service class to simulate a time-consuming process
    private class MyService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        updateProgress(i, 100);
                        updateMessage(i + "%");
                    }
                    return null;
                }
            };
        }
    }
}