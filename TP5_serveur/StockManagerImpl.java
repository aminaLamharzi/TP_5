import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StockManagerImpl extends UnicastRemoteObject implements StockManager {
    private List<Product> products = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public StockManagerImpl() throws RemoteException {
        super();
    }

    public List<Product> getAllProducts() throws RemoteException {
        return products;
    }

    public int getQuantity(String productName) throws RemoteException {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product.getQuantity();
            }
        }
        return 0;
    }

    public void addProduct(String productName, int quantity) throws RemoteException {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setQuantity(product.getQuantity() + quantity);
                notifyClients(product);
                return;
            }
        }
        products.add(new Product(productName, quantity));
        notifyClients(new Product(productName, quantity));
    }

    public void removeProduct(String productName, int quantity) throws RemoteException {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                int newQuantity = product.getQuantity() - quantity;
                if (newQuantity < 0) {
                    newQuantity = 0;
                }
                product.setQuantity(newQuantity);
                notifyClients(product);
                return;
            }
        }
    }

    public void registerClient(Client client) throws RemoteException {
        clients.add(client);
    }

    private void notifyClients(Product product) {
        for (Client client : clients) {
            try {
                client.notify(product);
            } catch (RemoteException e) {
                // remove the client from the list if it's not responding
                clients.remove(client);
            }
        }
    }
}
