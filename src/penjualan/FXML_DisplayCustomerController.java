package penjualan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class FXML_DisplayCustomerController implements Initializable {

    @FXML
    private TableView<CustomerModel> tbvcust;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata() {
        ObservableList<CustomerModel> data = FXMLDocumentController.dtcust.Load();
        if (data != null) {
            tbvcust.getColumns().clear();
            tbvcust.getItems().clear();
            TableColumn col = new TableColumn("idmember");
            col.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("idmember"));
            tbvcust.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("nama"));
            tbvcust.getColumns().addAll(col);
            col = new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("alamat"));
            tbvcust.getColumns().addAll(col);
            col = new TableColumn("total");
            col.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("total"));

            tbvcust.getColumns().addAll(col);
            tbvcust.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvcust.getScene().getWindow().hide();;
        }
    }

}
