package controllers;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.ReportFactory;
import models.Reporte;
import models.User;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import play.db.DB;
import play.mvc.Result;
import play.mvc.Controller;


public class ControllerReportes extends Controller{
	
	static String REPORT_DEFINITION_PATH = "./app/reports/";
	
	public static Result generarReportePDF(int criteriaReporte){
		
		Reporte reporte = ReportFactory.getReport(criteriaReporte);
		try {
			String printFileName = reporte.generarReporteCompilado();
			
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName, reporte.getFileName() + ".pdf");
				
				return ok(new File(reporte.getFileName() + ".pdf"));
			}
			
			return null;
		} 
		catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Result generarReporteParamPDF(int criteriaReporte, Long idRecorrido){
		
		Reporte reporte = ReportFactory.getReportWithParams(criteriaReporte, idRecorrido);
		try {
			String printFileName = reporte.generarReporteCompilado();
			
			if (printFileName != null) {
				JasperExportManager.exportReportToPdfFile(printFileName, reporte.getFileName() + ".pdf");
				
				return ok(new File(reporte.getFileName() + ".pdf"));
			}
			
			return null;
		} 
		catch (JRException e) {
			e.printStackTrace();
			return null;
		}
	}	
}
