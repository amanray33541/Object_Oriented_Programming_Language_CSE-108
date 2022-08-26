
package javafxfilechooser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FXMLDocumentController implements Initializable {
   
    
    @FXML
    private Label label;
    
    @FXML 
    private AnchorPane anchorpane;  
    
    @FXML
    private Button button1;
    
    @FXML
    private Button button2;
   
    @FXML
    private TextField textfield;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn  col1;
    
    @FXML
    private TableColumn  col2;
   
         
    ObservableList<String> list = FXCollections.observableArrayList();
   ObservableList<String> lists = FXCollections.observableArrayList();
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {       
       
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose any file");
        Stage stage =  (Stage)anchorpane.getScene().getWindow();  
        filechooser.setInitialDirectory(new File(System.getProperty("user.home")));       
        
        File file = filechooser.showOpenDialog(stage);
        
        if(file !=null)
        {
          Desktop desktop = Desktop.getDesktop();
          //desktop.open(file);
         
        System.out.println(file.getPath());
        textfield.setText(file.getPath());
 
        }
     
  Scanner input = new Scanner(file); 

  
 String inputs =""; 
while(input.hasNext()) { 
String word = input.next(); 

inputs = inputs + word + " ";

}
        
 
        String[] keys = inputs.split(" ");
        
        String[] uniqueKeys;
        int count = 0;
        System.out.println(inputs);
        uniqueKeys = getUniqueKeys(keys);
        for(String key: uniqueKeys)
        {
            if( key == null )
            {
                break;
            }           
            for(String s : keys)
            {
                if(key.equals(s))
                {
                    count++;
                }               
            }
           //System.out.println("frequency of " +key+ "is " +count);

            String dx = count + "";
            count=0;
           
          list.add(key);
         lists.add(dx);
  
        }

    }
    
    int cnn=-1;
    
    @FXML
    private void handleButtonAction2(ActionEvent event) throws IOException {       
       
        String [][] staffArray;
        staffArray = new String[list.size()][4];
        
        for(int q=0; q<list.size(); q++)
        {
            staffArray[q][0] = list.get(q);
            staffArray[q][1] = lists.get(q);
        }
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
     
        
        System.out.println("length " + staffArray[0].length);
        
       
            col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[0]));
                }
             });
            
            col2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[1]));
                }
             });
     
        table.setItems(data);
        for(int i=0; i<3; i++)
           System.out.println("list " + list.get(i));
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
     
    }
    
    private static String[] getUniqueKeys(String[] keys)
    {
        String[] uniqueKeys = new String[keys.length];
        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;
        for(int i=1; i<keys.length ; i++)
        {
            for(int j=0; j<=uniqueKeyIndex; j++)
            {
                if(keys[i].equals(uniqueKeys[j]))
                {
                    keyAlreadyExists = true;
                }
            }           
            if(!keyAlreadyExists)
            {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;               
            }
            keyAlreadyExists = false;
        }       
        return uniqueKeys;
    }

    
}
