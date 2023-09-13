package penjualan;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from customer where idmember = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer (idmember,nama, alamat, totalbelanja) values (?,?,?,?)");
            con.preparedStatement.setString(1, getCustModel().getId());           
            con.preparedStatement.setString(2, getCustModel().getNama());
            con.preparedStatement.setString(3, getCustModel().getAlamat());           
            con.preparedStatement.setDouble(4, getCustModel().getTotal());        
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customer where idmember  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customer set nama = ?, alamat = ?, totalbelanja = ?  where  idmember = ? ");
            con.preparedStatement.setString(1, getCustModel().getNama());
            con.preparedStatement.setString(2, getCustModel().getAlamat());
            con.preparedStatement.setDouble(3, getCustModel().getTotal());
            con.preparedStatement.setString(4, getCustModel().getId());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }
}
