package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.mvc.Controller;
import controllers.Application;

public class ReporteHistorico extends Reporte {
	
	@Override
	public String generarConsulta() {
		
		String consulta = "select current_time";
		
		return consulta;
	}

	@Override
	public void asignarParametros() {
		this.setFileName("./app/reports/ReporteHistoricos");
		
		List<String> subReports = new ArrayList<String>();
		subReports.add("./app/reports/SubreporteDistanciaAcumulada");
		subReports.add("./app/reports/SubreporteDistanciaTiempo");
		subReports.add("./app/reports/SubreporteRecorridosTiempo");
		subReports.add("./app/reports/SubreporteTiempoAcumulado");
		subReports.add("./app/reports/SubreporteTiempoTiempo");
		subReports.add("./app/reports/SubreporteVelocidadTiempo");
		this.setSubReports(subReports);
		
		User usuario = Application.getLocalUser(Controller.session());
		
		this.parameters.put("p_usuario", usuario.id);
		this.parameters.put("p_fecha", new Date());
		this.parameters.put("p_consulta", generarConsulta());
		
	}

}
