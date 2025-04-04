package dominio.usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum RepositorioUsuarios {
	INSTANCE;
	//private FactoriaDAO factoria;

	//private HashMap<Integer, Usuario> usuariosPorID;
	private HashMap<String, Usuario> usuariosPorNombre;

	private RepositorioUsuarios (){
		//usuariosPorID = new HashMap<Integer, Usuario>();
		usuariosPorNombre = new HashMap<String, Usuario>();
		
		/*
		try {
			factoria = FactoriaDAO.getInstancia();
			
			@SuppressWarnings("unchecked")
			List<Usuario> listaUsuarios = (List<Usuario>) factoria.crearObjetoDAO("usuario").getAll();
			for (Usuario usuario : listaUsuarios) {
				//usuariosPorID.put(usuario.getId(), usuario);
				usuariosPorMovil.put(usuario.getMovil(), usuario);
				usuariosPorNombre.put(usuario.getNombre(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
		*/
	}
	
	/*public List<Usuario> findUsuarios() /throws DAOException/ {
		return new ArrayList<Usuario>(usuariosPorMovil.values());
	}*/

	/*public Usuario findUsuarioPorId(int id) {
		return usuariosPorID.get(id);
	}*/

	public Usuario findUsuarioPorNombre(String nombre) {
		return usuariosPorNombre.get(nombre);
	}
	
	public void addUsuario(Usuario usuario) {
		usuariosPorNombre.put(usuario.getNombre(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosPorNombre.remove(usuario.getNombre());
	}
	
	public void updateUsuario(Usuario usuario) {
		usuariosPorNombre.put(usuario.getNombre(), usuario);
	}
}
