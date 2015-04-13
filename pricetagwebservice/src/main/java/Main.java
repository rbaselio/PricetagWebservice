import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import com.google.gson.Gson;

import placesObjects.GooglePlace;
import placesObjects.MyPlacesJson;
import utils.GsonPlaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


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
    Connection connection = null;
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
    }
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
