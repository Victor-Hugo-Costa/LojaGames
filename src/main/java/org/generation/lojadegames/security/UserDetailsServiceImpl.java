package org.generation.lojadegames.security;

import java.util.Optional;
import org.generation.lojadegames.model.Usuario;
import org.generation.lojadegames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Este usuário não foi encontrado"));
		return usuario.map(UserDetailsImpl::new).get();
	}
}
