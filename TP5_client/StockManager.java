import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StockManager extends Remote {
    List<Product> getAllProducts() throws RemoteException;
    int getQuantity(String productName) throws RemoteException;
    void addProduct(String productName, int quantity) throws RemoteException;
    void removeProduct(String productName, int quantity) throws RemoteException;
    void registerClient(Client client) throws RemoteException;
}
