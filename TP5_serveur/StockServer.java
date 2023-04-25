import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StockServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            StockManager stockManager = new StockManagerImpl();
            registry.rebind("StockManager", stockManager);
            System.out.println("StockManager bound");
        } catch (RemoteException e) {
            System.err.println("StockManagerImpl exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
