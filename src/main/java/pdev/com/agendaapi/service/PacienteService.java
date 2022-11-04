package pdev.com.agendaapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.exception.BusinessException;
import pdev.com.agendaapi.repository.PacienteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        boolean existeCpf = false;

        Optional<Paciente> optPaciente = repository.findByCpf(paciente.getCpf());

        if (optPaciente.isPresent()){
            if (!optPaciente.get().getId().equals(paciente.getCpf())){
                existeCpf = true;
            }
        }

        if (existeCpf){
            throw new BusinessException("Cpf já se encontrada cadastrado");
        }
        return repository.save(paciente);

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
