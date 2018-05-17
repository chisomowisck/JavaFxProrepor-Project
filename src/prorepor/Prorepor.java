
package prorepor;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Prorepor extends Application {
    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private static AnchorPane mainLayout2;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("PROREPOR");
        ShowStartPage();
        
    }
    public static void ShowProreporView() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/ProreporView.fxml"));
    mainLayout=loader.load();
    Scene scene=new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    public void ShowStartPage() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/ProreporStart.fxml"));
    mainLayout2=loader.load();
    Scene scene=new Scene(mainLayout2);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    
    
    public static void ShowProreporItems() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/ProreporItems.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    
    
    public static void ShowAddScene() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/AddStudents.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    
    
    public static void ShowScoresHome() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/ScoresHome.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    
    public static void ShowViewStudents() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("home/ViewStudents.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
//Form 1
    public static void ShowForm1Class1() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("form1/Class1.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    public static void ShowForm1Class2() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("form1/Class2.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    public static void ShowForm1Class3() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("form1/Class3.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
     public static void ShowForm2Scene() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("form2/Form2Page.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    public static void ShowAddStudentScene() throws IOException{
    FXMLLoader loader= new FXMLLoader();
    loader.setLocation(Prorepor.class.getResource("addstudent/AddStudents.fxml"));
    AnchorPane mainItems=loader.load();
    mainLayout.setCenter(mainItems);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
