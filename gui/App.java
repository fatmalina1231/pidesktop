
package gui;
import gui.CaptureCameraController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CaptureCamera.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        stage.setTitle("WebcamFX");
        stage.show();
    }

  
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void stop() {
        CaptureCameraController.isCapture = true; // Kill the thread of camera
    }
    
}
