package com.aws.example.Utils;
import org.json.JSONObject;

import com.aws.example.cliente.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Utils {
	
	public static String URL_DATABASE = "https://test-tec-e993e-default-rtdb.firebaseio.com/usuarios.json"; 
	public static String[] calcularDesviacion(List<Double> rowList) {
		double[] target = new double[rowList.size()];
		 for (int i = 0; i < target.length; i++) {
	
		    target[i] = rowList.get(i); 
		 }
			System.out.println("target: "+ target);
		 
		double [] values = target;   //change input values here
		double sum=0;
		double finalsum = 0;
		double average = 0;
		
		for( double i : values) {
		finalsum = (sum += i);
		}
		
		average = finalsum/(values.length);
		System.out.println("Average: "+ average);
		
		double sumX=0;
		double finalsumX=0;
		double[] x1_average = new double[2000];
		for (int i = 0; i<values.length; i++){
		double fvalue = (Math.pow((values[i] - average), 2));
		x1_average[i]= fvalue;
		System.out.println("test: "+ fvalue);
		}
		
		for(double i : x1_average) {
		finalsumX = (sumX += i);
		}
		
		Double AverageX = finalsumX/(values.length);
		System.out.println("E(X1-x1_average)^2/AverageX: "+ AverageX);
		double SquareRoot = Math.sqrt(AverageX);
		System.out.println("Standard Deviation: "+ SquareRoot);
		String[] valores = new String[2];
		valores[0] =  String.valueOf(average);
		valores[1] = String.valueOf(SquareRoot);
		return valores;
	}
	public static List<Double> getDataFromJSON(String answer, String path) {
		
		List<Double> rowList = new ArrayList<Double>();

		JSONObject data = new JSONObject(answer);
		data.keySet().forEach(keyStr ->{
			Object keyvalue = data.get(keyStr);
			JSONObject objeto = new JSONObject(keyvalue.toString());
	        rowList.add(Double.parseDouble(objeto.get(path).toString()));
		});
		return rowList;
	}
	
	public static String getRespuestaKPIS(String[] valores) {
		
		JSONObject respuesta = new JSONObject();
		respuesta.put("status", true);
		respuesta.put("media", valores[0]);
		respuesta.put("desv", valores[1]);
		return respuesta.toString();
	}
	public static String getRespuestaCrearCliente(String answer) {
		
		JSONObject data = new JSONObject(answer);
		JSONObject respuesta = new JSONObject();
		respuesta.put("status", true);
		respuesta.put("id", data.get("name"));
		return respuesta.toString();
	}
	
	public static List<Cliente> getClientes(String json)  {
		List<Cliente> empList = new ArrayList<Cliente>();
		JSONObject data = new JSONObject(json);
		data.keySet().forEach(keyStr ->{
			Cliente cliente = new Cliente();
			Object keyvalue = data.get(keyStr);
			JSONObject objeto = new JSONObject(keyvalue.toString());
			cliente.setId(keyStr);
			cliente.setApellidos(objeto.get("apellidos").toString());
			cliente.setNombres(objeto.get("nombres").toString());
			cliente.setEdad(Integer.parseInt(objeto.get("edad").toString()));
			cliente.setFecha_nacimiento(objeto.get("fecha_nacimiento").toString());

		       DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        LocalDate d = LocalDate.parse(objeto.get("fecha_nacimiento").toString(), fmt);
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        d= d.plusYears(80);
		        cliente.setFecha_probable(  d.format(formatter));

			
			empList.add(cliente);
		});
		return empList;
	}

}
