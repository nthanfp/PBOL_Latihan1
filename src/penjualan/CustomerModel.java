package penjualan;

/**
 *
 * @author Nathan
 */
public class CustomerModel {

    private String idmember, nama, alamat;
    private double total;

    public String getId() {
        return idmember;
    }

    public void setId(String idmember) {
        this.idmember = idmember;
    }

    public String getIdmember() {
        return idmember;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public double getTotal() {
        return total;
    }

    public void setIdmember(String idmember) {
        this.idmember = idmember;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
