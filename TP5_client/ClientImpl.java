import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    public ClientImpl() throws RemoteException {
        super();
    }

    @Override
    public void notify(Product product) throws RemoteException {
        System.out.println("Product " + product.getName() + " quantity changed to " + product.getQuantity());
    }
}
