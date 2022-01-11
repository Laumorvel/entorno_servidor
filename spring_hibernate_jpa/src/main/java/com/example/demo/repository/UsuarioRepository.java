package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String telefono);

	@Query("select e from usuarios e where lower(e.nombre) like %?1% or lower(e.email) like %?1% or lower(e.telefono) like %?1%") 
	List<Usuario> encuentraPorNombreEmailOTelefono(String cadena);

}
