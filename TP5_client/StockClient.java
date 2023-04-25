import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StockClient {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost" , 1099 );
            StockManager stockManager = (StockManager) registry.lookup("StockManager");

            // Create a client object and export it as a remote object
            Client client = new ClientImpl();
           

            // Register the client with the server
            stockManager.registerClient(client);

            // Query the stock and perform operations
            System.out.println("All products: " + stockManager.getAllProducts());
            System.out.println("Quantity of product A: " + stockManager.getQuantity("A"));
            stockManager.addProduct("A", 10);
            System.out.println("Quantity of product A: " + stockManager.getQuantity("A"));
            stockManager.removeProduct("A", 5);
            System.out.println("Quantity of product A: " + stockManager.getQuantity("A"));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
