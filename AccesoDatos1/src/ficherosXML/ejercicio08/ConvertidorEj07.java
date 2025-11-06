package ficherosXML.ejercicio08;

import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ConvertidorEj07 {
	public static void main(String[] args) throws IOException, TransformerConfigurationException {
		String hojaEstilos = "ejercicio08.xsl";
		String datosAl = "AlumnosDomObjetos.xml";
		File pgHTML = new File("pgHTML.html");
		
		try(FileOutputStream os = new FileOutputStream(pgHTML)){
			Source estilos = new StreamSource(hojaEstilos);
			Source datos = new StreamSource(datosAl);
			
			Result result = new StreamResult(os);
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
			transformer.transform(datos, result);
			
			System.out.println("HTML creado");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
