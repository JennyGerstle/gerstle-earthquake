package gerstle.geojson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeoJSonApplication extends Application
{
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GeoJSon_Application.fxml"));

        Parent parent = loader.load();
        Scene scene = new Scene(parent, 600, 800);

        stage.setTitle("Earthquakes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
