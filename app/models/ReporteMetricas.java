package models;

import java.util.Date;

import play.mvc.Controller;
import controllers.Application;

public class ReporteMetricas extends Reporte{

	@Override
	public String generarConsulta() {

		String consulta = "select MIN(mr.fecha) AS fecha, " +  
						  " re.id_recorrido, " +
						  " ru.lugar_inicio,  " +
					      " ru.lugar_fin, " +
					      " me.distancia_real, " +
					      " me.distancia_estimada, " +
					      " me.tiempo_real, " +
					      " me.tiempo_estimado, " +
					      " me.velocidad_media, " +
					      " me.clima_temperatura_origen, " +
					      " me.clima_temperatura_destino, " +
					      " u.name, " +
					      " re.nombre " +
					" from ruta ru,  " +
					    " recorrido re, " +
					    " usuario_x_recorrido ur, " +
					    " metricas_x_recorridos mr, " +
					    " users u, " +
					    " (SELECT * FROM CROSSTAB ('select mr.recorrido_id_recorrido, " +
					      " me.nombre_metrica, " +
					      " mr.valor_metrica " +
					" from ruta ru,  " +
					    " recorrido re, " +
					    " metricas_x_recorridos mr, " +
					    " metrica me " +
					" where ru.recorrido_id_recorrido = re.id_recorrido " +
					    " and re.id_recorrido = mr.recorrido_id_recorrido " +
					    " and mr.metrica_id_metrica = me.id_metrica " +
					    " and mr.usuario_id = ' || $P{p_usuario} || ' " +
					    " order by mr.recorrido_id_recorrido, me.id_metrica') " +
					 " AS ct(\"recorrido_id_recorrido\" bigint, \"distancia_real\" double precision, \"tiempo_real\" double precision, \"velocidad_media\" double precision, \"distancia_estimada\" double precision, \"tiempo_estimado\" double precision, \"clima_temperatura_origen\" double precision, \"clima_temperatura_destino\" double precision, \"clima_humedad_origen\" double precision, \"clima_humedad_destino\" double precision, \"clima_nubosidad_origen\" double precision, \"clima_nubosidad_destino\" double precision, \"clima_viento_origen\" double precision, \"clima_viento_destino\" double precision)) AS me " +
					" where ru.recorrido_id_recorrido = re.id_recorrido " +
					    " and re.id_recorrido = me.recorrido_id_recorrido " +
					    " and re.id_recorrido = ur.recorrido_id_recorrido " +
					    " and re.id_recorrido = mr.recorrido_id_recorrido " +
					    " and mr.usuario_id = $P{p_usuario} " +
					    " and ur.usuario_id = $P{p_usuario} " +
					    " and ur.usuario_id = u.id " +
					" group by re.id_recorrido, " +
					     " ru.lugar_inicio,  " +
					     " ru.lugar_fin, " +
					     " me.distancia_real, " +
					     " me.distancia_estimada, " +
					     " me.tiempo_real, " +
					     " me.tiempo_estimado, " +
					     " me.velocidad_media, " +
					     " me.clima_temperatura_origen, " +
					     " me.clima_temperatura_destino, " +
					     " u.name, " + 
					     " re.nombre";
		
		return consulta;
	}

	@Override
	public void asignarParametros() {
		
		this.setFileName("./app/reports/ReporteMetricas");
		
		User usuario = Application.getLocalUser(Controller.session());
		
		this.parameters.put("p_usuario", usuario.id);
		this.parameters.put("p_fecha", new Date());
		this.parameters.put("p_consulta", generarConsulta());
	}

}
