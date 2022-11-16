package pdev.com.agendaapi.domain.entities.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdev.com.agendaapi.domain.entities.Usuario;
import pdev.com.agendaapi.domain.entities.repository.UsuarioRepository;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = repository.findByUsuario(usuario);

        if (optUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        Usuario user = optUsuario.get();
        return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());

    }

    public List<Usuario> listarTodos(){
        return repository.findAll();
    }
    public Usuario salvar(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
}
