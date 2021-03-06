package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empresa;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.SeguridadEJB;

@Named("sessionController")
@SessionScoped
public class SessionController implements Serializable {

	private String usuario;
	private String contrasena;
	private List<Object> accesos;
	private int bd;
	private Usuario use;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Usuario getUse() {
		return use;
	}

	public void setUse(Usuario use) {
		this.use = use;
	}

	public List<Object> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Object> accesos) {
		this.accesos = accesos;
	}
	
	public int getBd() {
		return bd;
	}

	public void setBd(int bd) {
		this.bd = bd;
	}



	@EJB
	private SeguridadEJB seguridadEjb;

	@EJB
	private AuditoriaEJB auditoriaEJB;

	/**
	 * Login de usuario
	 */
	public String login() {

		Usuario useBuscar = seguridadEjb.buscar(usuario,1);

		if (useBuscar != null) {

			if (useBuscar.getContrasenia().equals(contrasena)) {
				use = useBuscar;
				if (use.isEstado() == true) {
					Faces.setSessionAttribute("usuario", use);
					accesos = seguridadEjb.listaAcc(usuario);
					bd = seguridadEjb.buscarEmpresa().getBd();
					Faces.setSessionAttribute("bd", bd);//Le asigno el valor de la bd que este configurada
					// Messages.addGlobalInfo("Usuario existe");
					registrarAuditoria("Inicio session", true, usuario);
					return "/paginas/seguro/gesusuarios.xhtml?faces-redirect=true";

				} else {
					Messages.addFlashGlobalError("Usuario no activado CONTACTE AL ADMINISTRADOR");
					registrarAuditoria("Inicio session", false, usuario);
				}
			} else {
				Messages.addFlashGlobalError("Usuario o contrasena incorrecta");
				registrarAuditoria("Inicio session", false, usuario);
			}

		} else {
			Messages.addFlashGlobalError("El usuario no existe");
		}
		return null;

	}

	/**
	 * Cerrar session
	 * 
	 * @return
	 */
	public String cerrarSession() {
		usuario = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/login.xhtml?faces-redirect=true";
		/**
		 * con q no entendipor que se logio dos veces solo fue una de ahi captura el id
		 * de la cita?sii vea ps le esplico
		 */
	}

	public boolean isSesion() {
		return use != null;
	}

	public String registrar() {
		return "/paginas/publico/registroNuevos.xhtml?faces-redirect=true";
	}

	/**
	 * Metodo para registrar las auditorias generales
	 * 
	 * @param accion
	 *            Crear, Editar, Eliminar o Actualizar
	 * @param nombreReg
	 *            modulo que se esta trabajando
	 */
	public void registrarAuditoria(String accion, boolean ingreso, String us) {
		try {
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			auditoriaEJB.crearAuditoriaSession(accion, browserDetails, us, ingreso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
