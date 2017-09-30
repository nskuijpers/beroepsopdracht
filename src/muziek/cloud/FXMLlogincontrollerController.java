package muziek.cloud;

import execute.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Nick Kuijpers
 */
public class FXMLlogincontrollerController implements Initializable {
    
    Database DB = new Database();
    @FXML private TextField username = new TextField();
    @FXML private TextField password = new TextField();
    @FXML private Label message = new Label();
    
    public boolean login (String username, String password) throws Exception {
        try {
            if (DB.insert("select username, password from user where username='"+ username + "' and password='" + password + "'")) {
                return true;
                
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {      
        
        if(login(username.getText(), password.getText())) {
            
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLhomepage.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } else {
            
            message.setText("Error Username or Password is wrong.");
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
