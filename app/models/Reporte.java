package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.db.DB;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;

public abstract class Reporte {
	
	public static final int METRICAS = 1;
	public static final int HISTORICO = 2;
	public static final int RECORRIDO = 3;
	
	private String fileName = "";
	private List<String> subReports = new ArrayList<String>();

	Map<String, Object> parameters = new HashMap<String, Object>();
	
	public abstract String generarConsulta();
	
	public abstract void asignarParametros();
	
	public String generarReporteCompilado() throws JRException{	
		asignarParametros();
		
		for(String subreporte : subReports){
			System.out.println(subreporte);
			JasperCompileManager.compileReportToFile(subreporte + ".jrxml");
		}
		
		JasperCompileManager.compileReportToFile(fileName + ".jrxml");
		
		return JasperFillManager.fillReportToFile(fileName + ".jasper" , parameters, DB.getConnection());
	
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List<String> getSubReports() {
		return subReports;
	}

	public void setSubReports(List<String> subReports) {
		this.subReports = subReports;
	}
}

