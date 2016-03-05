package testes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManagerFactory emf;
	
	public static void criarConexão(){		
			emf = Persistence.createEntityManagerFactory("Webservice");
	}
	
	public static EntityManager criarEntityManager (){
		return emf.createEntityManager();
	}
	
	public static void fechaConexao(){
		emf.close();
	}
	

}
