package repositorios;

import jakarta.persistence.*;
import java.util.List;

import dominio.usuario.Usuario;

public enum RepositorioUsuarios {
    INSTANCE;

    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager;

    public void init(EntityManagerFactory factory) {
        if (this.entityManagerFactory != null) {
        	System.err.println("El RepositorioUsuarios ya ha sido inicializado");
        	return;
        }
    	this.entityManagerFactory = factory;
        this.entityManager = factory.createEntityManager();
    }
    
    // Método para agregar un nuevo usuario
    public void agregarUsuario(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        //entityManager.flush();
        entityManager.getTransaction().commit();
    }

    // Método para encontrar un usuario por nombre
    public Usuario encontrarUsuarioPorNombre(String nombre) {
        try {
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                                .setParameter("nombre", nombre)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    // Método para encontrar un usuario por correo
    public Usuario encontrarUsuarioPorCorreo(String correo) {
        try {
            return entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                                .setParameter("correo", correo)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.remove(usuario);
        entityManager.getTransaction().commit();
    }

    // Cerrar el EntityManager y EntityManagerFactory al finalizar el uso
    public void cerrar() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
