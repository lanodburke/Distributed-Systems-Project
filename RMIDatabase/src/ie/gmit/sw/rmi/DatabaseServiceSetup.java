package ie.gmit.sw.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DatabaseServiceSetup {
	public static void main(String[] args) throws Exception {
		
		DatabaseService databaseService = new DatabaseServiceImpl();

		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("databaseService", databaseService);
		
		System.out.println("Server ready.");
	}
}
