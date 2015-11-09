import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.gson.Gson;

import placesObjects.GooglePlace;
import placesObjects.MyPlacesJson;
import testes.Cliente;
import testes.ClienteService;
import testes.EntityManagerUtil;
import utils.GsonPlaces;


public class Main extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
		
		
		req.getParameterMap();
		
		Connection connection = null;
	    try {
	      connection = getConnection();

	      Statement stmt = connection.createStatement();
	      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
	      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	      
	    } catch (Exception e) {
	     
	    } finally {
	      if (connection != null) try{connection.close();} catch(SQLException e){}
	    
		}
  }
	
	
	

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getRequestURI().endsWith("/db")) {
      showDatabase(req,resp);
    }
    else if (req.getRequestURI().endsWith("/json")) {
    	showJson(req,resp);
	} 
    else {
      showHome(req,resp);
    }
  }
  
	
	  

	private void showHome(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		  resp.getWriter().print("Pricetag");
	}


  private void showJson(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  
	  
	  
	  double lat = Double.parseDouble(req.getParameter("lat"));
	  double lng = Double.parseDouble(req.getParameter("lng"));
	  //int radius = Integer.parseInt(req.getParameter("radius"));
	  
	  String coordenadas = lat + "," + lng;
	  
	  GooglePlace place;	  
	  GsonPlaces json = new GsonPlaces();	  
	  place = json.getAllPlaces(coordenadas);
	  place.setDistances(coordenadas);
	  
	  MyPlacesJson myplaces = new MyPlacesJson();
	  myplaces.setMyplacesList(place.getResult());
	  
	  
	  Gson g = new Gson();
	  resp.setContentType("application/json; charset=UTF-8");           
      resp.setHeader("Cache-Control", "no-cache");
      resp.getWriter().write(g.toJson(myplaces));
      
  }
  

  private void showDatabase(HttpServletRequest req, HttpServletResponse resp)   throws ServletException, IOException {
	  Random gerador = new Random();
		EntityManagerUtil.criarConexão();
		
		ClienteService clienteService = new ClienteService();
		
		Cliente cliente = null;
		
		for (int i = 0; i < 10; i++) {
			cliente = new Cliente();
			cliente.setEndereco("Rua " + gerador.nextInt());
			cliente.setNome("Usuario " + gerador.nextInt());
			cliente.setNasc(new Date(Math.abs(System.currentTimeMillis() - gerador.nextInt())));
			
			clienteService.persist(cliente);
		}
		
		List<Cliente> todosClientes = clienteService.findName("Usuario", true);
		
		StringBuffer out = new StringBuffer(); 
		
		for (Cliente pessoa : todosClientes) {
			out.append("Read from DB: ");
			out.append(pessoa);
			out.append("\n");

		}
		resp.getWriter().print(out);
		
		
		EntityManagerUtil.fechaConexao();
	  
	  
	  /*Connection connection = null;
    try {
      connection = getConnection();

      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      String out = "Banco OK!\n";
      while (rs.next()) {
          out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
      }

      resp.getWriter().print(out);
    } catch (Exception e) {
      resp.getWriter().print("There was an error: " + e.getMessage());
    } finally {
      if (connection != null) try{connection.close();} catch(SQLException e){}
    }*/
  }

  private Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    int port = dbUri.getPort();

    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, username, password);
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new Main()),"/*");
    server.start();
    server.join();
  }
}
