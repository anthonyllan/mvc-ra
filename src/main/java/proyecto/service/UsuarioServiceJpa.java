package proyecto.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto.dto.UsuarioRegistroDto;
import proyecto.model.Rol;
import proyecto.model.Usuario;
import proyecto.repository.UsuarioRepository;

@Service
@Primary
public class UsuarioServiceJpa implements UsuarioService{
	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UsuarioServiceJpa(UsuarioRepository ur) {
		super();
		this.ur = ur;
	}

	@Override
	public Usuario guardarUsuario(UsuarioRegistroDto registroDto) {
		Usuario usuario = new Usuario(
				registroDto.getNombre(),
				registroDto.getApellido(),
				registroDto.getEmail(),
				passwordEncoder.encode(registroDto.getPassword()),
				Arrays.asList(new Rol("GERENTE")));
		return ur.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = ur.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Email o Password invalidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> buscarTodos() {
		return ur.findAll();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> optional = ur.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void borrar(Long id) {
		ur.deleteById(id);
	}

	@Override
	public void Editar(Usuario us) {
		if (ur.existsById(us.getId())) { // Verifica si el producto existe
	        ur.save(us); // Guarda los cambios
	    }
	}

}
