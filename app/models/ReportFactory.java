package models;

public class ReportFactory {
	
	public static Reporte getReport(int criteria){

 
		if(criteria == Reporte.METRICAS){
			return new ReporteMetricas();
		}
 
		if(criteria == Reporte.HISTORICO){
			return new ReporteHistorico();
		}
 
		if(criteria == Reporte.RECORRIDO){
			return new ReporteRecorrido(null);
		}
		
		return null;
	}
	
	public static Reporte getReportWithParams(int criteria, Long param){
		if(criteria == Reporte.RECORRIDO){
			return new ReporteRecorrido(param);
		}
		
		return null;
	}

}
