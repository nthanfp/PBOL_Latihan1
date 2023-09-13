package penjualan;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nathan
 */
public class DBCustomer {

    private CustomerModel dt = new CustomerModel();

    public CustomerModel getCustModel() {
        return (dt);
    }

    public void setCustModel(CustomerModel s) {
        dt = s;
    }

    public ObservableList<CustomerModel> Load() {
        try {
            ObservableList<CustomerModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select idmember, nama, alamat, total from customer");
            int i = 1;
            while (rs.next()) {
                CustomerModel d = new CustomerModel();
                d.setId(rs.getString("idmember"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setTotal(rs.getDouble("total"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
