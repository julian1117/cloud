package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Producto")
public class Producto implements Serializable{
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;		
	
	@Column(name="descripcion")
	private String descirpcion;	
	
	@Column(name="codigoLote")
	private Integer codigoLote;
	
	@Column(name="peso")
	private String peso;
	
	@Column(name="dimensiones")
	private String dimensiones;
	
	@Column(name="valor")
	private double valor;

	public Producto() {
		super();
	}

	public Producto(Integer id, String nombre, String descirpcion, Integer codigoLote, String peso, String dimensiones,
			double valor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descirpcion = descirpcion;
		this.codigoLote = codigoLote;
		this.peso = peso;
		this.dimensiones = dimensiones;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescirpcion() {
		return descirpcion;
	}

	public void setDescirpcion(String descirpcion) {
		this.descirpcion = descirpcion;
	}

	public Integer getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(Integer codigoLote) {
		this.codigoLote = codigoLote;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	

}
