package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Auditoria")

@NamedQueries({
	@NamedQuery(name = Auditoria.AUDITORIA, query = "SELECT a FROM Auditoria a"),
	@NamedQuery(name = Auditoria.POR_AUDITORIA, query = "SELECT a FROM Auditoria a where a.nombre=?1"),
//	@NamedQuery(name = Auditoria.POR_AUDITORIA_FECHA, query = "SELECT a FROM Auditoria a where a.fecha BETWEEN ?1 AND ?2")
	@NamedQuery(name = Auditoria.POR_AUDITORIA_FECHA, query = "SELECT a FROM Auditoria a where a.fecha>=?1")

})
public class Auditoria implements Serializable {

	public static final String AUDITORIA= "Auditoria.listAud";

	public static final String POR_AUDITORIA = "Auditoria.listAudtoria";	
	
	public static final String POR_AUDITORIA_FECHA = "Auditoria.listAudtoriaFecha";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDITORIA_SEQ")
	@SequenceGenerator(sequenceName = "AUDIT_SEQ", allocationSize = 1, name = "AUDITORIA_SEQ")
	@Column(name = "id")
	private Integer idAuditoria;

	@Column(name = "nombre",nullable = false)
	private String nombre;

	@Column(name = "accion",nullable = false)
	private String accion;

	@Column(name = "origen",nullable = false)
	private String origen;

	@Column(name = "navegador",nullable = false)
	private String navegador;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha",nullable = false)
	private Date fecha;

	@Column(name = "usuarioSe",nullable = false)
	private String usuarioSe;
	
	@Column(name = "usuarioAfectado",nullable = false)
	private String usuarioAfectado;

	public Auditoria() {
		super();
	}

	public Auditoria(Integer idAuditoria, String nombre, String accion, String origen, String navegador, Date fecha,
			String usuarioSe, String usuarioAfectado) {
		super();
		this.idAuditoria = idAuditoria;
		this.nombre = nombre;
		this.accion = accion;
		this.origen = origen;
		this.navegador = navegador;
		this.fecha = fecha;
		this.usuarioSe = usuarioSe;
		this.usuarioAfectado = usuarioAfectado;
	}

	public Integer getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuarioSe() {
		return usuarioSe;
	}

	public void setUsuarioSe(String usuarioSe) {
		this.usuarioSe = usuarioSe;
	}

	public String getUsuarioAfectado() {
		return usuarioAfectado;
	}

	public void setUsuarioAfectado(String usuarioAfectado) {
		this.usuarioAfectado = usuarioAfectado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAuditoria == null) ? 0 : idAuditoria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditoria other = (Auditoria) obj;
		if (idAuditoria == null) {
			if (other.idAuditoria != null)
				return false;
		} else if (!idAuditoria.equals(other.idAuditoria))
			return false;
		return true;
	}

}
