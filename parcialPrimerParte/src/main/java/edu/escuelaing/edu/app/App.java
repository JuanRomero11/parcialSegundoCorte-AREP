package edu.escuelaing.edu.app;
import static spark.Spark.*;


import org.json.JSONObject;

import edu.escuelaing.edu.app.servicesOperation.Operations;
import edu.escuelaing.edu.app.servicesOperationImpl.Atan;
import edu.escuelaing.edu.app.servicesOperationImpl.Exp;
import spark.Request;
import spark.Response;

public class App {

    public static void main(String args[]){
        port(getPort());
        get("/atan",(req,res) -> atan(req,res));
		get("/exp",(req,res) -> exp(req,res));
        
    }
	private static JSONObject atan(Request req, Response res) {
		String listaNormal= req.queryParams("value");
	     double numero = Double.parseDouble(listaNormal);
		double resultadoOperacion = 0;
		Operations calculador;
		JSONObject objetoJson=new JSONObject();
		
		if (listaNormal.equals("")) {
			objetoJson.put("Input","null");
			objetoJson.put("No ingreso ningun valor","null");
		}else{
			calculador =new Atan();
			resultadoOperacion=calculador.getResult(numero);
			
		}
		
		objetoJson.put("Output",resultadoOperacion);
		objetoJson.put("Input",listaNormal);
		objetoJson.put("Operacion","atan");
        return objetoJson;
    }

	public static JSONObject  exp(Request req, Response res) {
	    String listaNormal= req.queryParams("value");
	     double numero = Double.parseDouble(listaNormal);
		double resultadoOperacion = 0;
		Operations calculador;
		JSONObject objetoJson=new JSONObject();
		
		
		if (listaNormal.equals("")) {
			objetoJson.put("Input","null");
			objetoJson.put("Output","null");
		}else{

			 calculador =new Exp();
			 
			resultadoOperacion=calculador.getResult(numero);
		}
	
		objetoJson.put("Output",resultadoOperacion);
		objetoJson.put("Input",listaNormal);
		objetoJson.put("Operacion","exp");
		return objetoJson;
	}


	static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }




}
