package pdev.com.agendaapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.repository.PacienteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente obj){

        //valida CPF se já existe ou email
        if (obj.getEmail().){

        }


        return repository.save(obj);
    }
    public List<Paciente> buscarTodos(){
        return repository.findAll();
    }
    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }
    public void deletar(Long id){
        repository.deleteById(id);
    }
}
