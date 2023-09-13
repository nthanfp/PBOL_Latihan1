package penjualan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class FXML_DisplayCustomerController implements Initializable {

    @FXML
    private TableView<CustomerModel> tbvcust;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnFirst;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnLast;

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

    @FXML
    private void btnAddAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_inputnilai.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        btnFirstAct(event);
    }

    @FXML
    private void btnRemoveAct(ActionEvent event) {
        CustomerModel s = new CustomerModel();
        s = tbvcust.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtcust.delete(s.getId())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            btnFirstAct(event);
        }

    }

    @FXML
    private void btnUpdateAct(ActionEvent event) {
        CustomerModel s = new CustomerModel();
        s = tbvcust.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_inputnilai.fxml"));
            Parent root = (Parent) loader.load();
//        FXML_inputnilaiController isidt=(FXML_inputnilaiController)loader.getController();
//            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        btnPrevAct(event);
    }

    @FXML
    private void btnFirstAct(ActionEvent event) {
        tbvcust.getSelectionModel().selectFirst();
        tbvcust.requestFocus();
    }

    @FXML
    private void btnPrevAct(ActionEvent event) {
        tbvcust.getSelectionModel().selectAboveCell();
        tbvcust.requestFocus();
    }

    @FXML
    private void btnNextAct(ActionEvent event) {
        tbvcust.getSelectionModel().selectBelowCell();
        tbvcust.requestFocus();
    }

    @FXML
    private void btnLastAct(ActionEvent event) {
    }

}
