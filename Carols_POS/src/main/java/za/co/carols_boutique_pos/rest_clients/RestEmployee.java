package za.co.carols_boutique_pos.rest_clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique_pos.service.EmployeeS;

/**
 *
 * @author Jomar
 */
public class RestEmployee implements EmployeeS {

	private Client client;
	private String uri;

	public RestEmployee() {
		client = ClientBuilder.newClient();
		uri = "http://localhost:8080/Carols_Boutique_API/pos/employee/";
	}

	@Override
	public Employee login(Employee employee) {
		String url = uri + "login";

		WebTarget webTarget = client.target(url);
		Response response = null;

		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(employee)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestEmployee.class.getName()).log(Level.SEVERE, null, ex);
		}

		return response.readEntity(Employee.class);
	}

	@Override
	public String register(Employee employee) {

		String url = uri + "register";

		WebTarget webTarget = client.target(url);
		Response response = null;

		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(employee)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestEmployee.class.getName()).log(Level.SEVERE, null, ex);
		}

		return response.readEntity(String.class);
	}

	@Override
	public String promoteToManager(String employeeID) {
		String url = uri + "promoteToManager";

		WebTarget webTarget = client.target(url);
		Response response = null;

		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(employeeID)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestEmployee.class.getName()).log(Level.SEVERE, null, ex);
		}

		return response.readEntity(String.class);
	}

	@Override
	public String updateEmployee(Employee employee) {
		String url = uri + "updateEmployee";

		WebTarget webTarget = client.target(url);
		Response response = null;

		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(employee)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestEmployee.class.getName()).log(Level.SEVERE, null, ex);
		}

		return response.readEntity(String.class);
	}

	@Override
	public String deleteEmployee(String employeeID) {
		String url = uri + "deleteEmployee";

		WebTarget webTarget = client.target(url);
		Response response = null;

		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(employeeID)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestEmployee.class.getName()).log(Level.SEVERE, null, ex);
		}

		return response.readEntity(String.class);
	}

	private String Stringify(Object o) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(o);
	}

}
