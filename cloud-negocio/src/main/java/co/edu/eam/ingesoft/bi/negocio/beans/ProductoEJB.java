package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;

@Stateless
@LocalBean
public class ProductoEJB {

	@PersistenceContext
	private EntityManager em;
	
	public void crearProducto (Producto producto) {
		Producto pr = buscarProducto(producto.getId());
		
		if(pr== null ) {
			em.persist(producto);
		}else {
			throw new co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio("El quierofano ya se encuentra registrado");
		}
	}
	
	/**
	 * permite la busqueda de un producto en el sistema
	 * @param id que representa el parametro de busqueda del productp
	 * @return el producto encontrado 
	 */
	public Producto buscarProducto(Integer id) {
		return em.find(Producto.class, id);
	}
	
	public void editar(Producto producto) {
		Producto pro = buscarProducto(producto.getId());
		if(pro != null) {
			em.merge(producto);
		}else {
			throw new co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio("El Producto no se encuentra en el sistema");

		}
	}
	
	public void eliminarProducto(Producto producto) {
		Producto pro = buscarProducto(producto.getId());
		if(pro != null) {
			em.remove(producto);
		}else {
			throw new co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio("El Producto no se encuentra en el sistema");

		}
	}
}