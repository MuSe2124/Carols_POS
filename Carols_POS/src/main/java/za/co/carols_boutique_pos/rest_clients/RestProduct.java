/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique_pos.rest_clients;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique_pos.models.Category;
import za.co.carols_boutique_pos.models.Exchange;
import za.co.carols_boutique_pos.models.ProdCat;
import za.co.carols_boutique_pos.models.Product;
import za.co.carols_boutique_pos.models.Refund;
import za.co.carols_boutique_pos.models.Stock;
import za.co.carols_boutique_pos.service.ProductS;

/**
 *
 * @author muaad
 */
public class RestProduct implements ProductS{

	private Client client;
	private String url;

	public RestProduct(){
		client = ClientBuilder.newClient();
		url = "http://localhost:8080/Carols_Boutique_API/pos/product/";
	}
	
    @Override
    public Product getProduct(String productID,String size) {
       String url = "url"+"getProduct/"+productID+"/"+size;

        WebTarget webTarget = client.target(url);
        Response response = null;

		response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		
        return response.readEntity(Product.class);
	}

    @Override
    public String addProductToInventory(Stock stock) {
        String url = "url"+"addProductToInventory";

        WebTarget webTarget = client.target(url);
        Response response = null;
       
		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(stock)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
            
        return response.readEntity(String.class); 
	}

    @Override
    public String addNewProduct(ProdCat prodCat) {
        String url = "url"+"addNewProduct";

        WebTarget webTarget = client.target(url);
        Response response = null;
       
		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(prodCat)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
            
        return response.readEntity(String.class); 
	}

    @Override
    public String removeProductFromInventory(Stock stock) {
        String url = "url"+"removeProductFromInventory";

        WebTarget webTarget = client.target(url);
        Response response = null;
       
		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(stock)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
            
        return response.readEntity(String.class); 
	}

    @Override
    public String deleteProduct(String productID, String catID) {
     String url = "url"+"deleteProduct/"+productID+"/"+catID;

        WebTarget webTarget = client.target(url);
        Response response = null;
       
		response = webTarget.request(MediaType.APPLICATION_JSON).delete(Response.class);
   
        return response.readEntity(String.class);     
	}

    @Override
    public String refund(Refund refund) {
    String url = "url"+"refund";

        WebTarget webTarget = client.target(url);
        Response response = null;
       
		try {
			response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(Stringify(refund)));
		} catch (JsonProcessingException ex) {
			Logger.getLogger(RestProduct.class.getName()).log(Level.SEVERE, null, ex);
		}
            
        return response.readEntity(String.class);     
	}
	
	@Override
	public ArrayList<Category> getCategories(){
		String url = "url"+"refund";

        WebTarget webTarget = client.target(url);
        Response response = null;
		response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);

        return response.readEntity(ArrayList.class);   
	}


	private String Stringify(Object o) throws JsonProcessingException{   
        return new ObjectMapper().writeValueAsString(o);
    }
	
}
