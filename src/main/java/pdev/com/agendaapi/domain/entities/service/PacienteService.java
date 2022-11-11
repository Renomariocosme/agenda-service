package pdev.com.agendaapi.domain.entities.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.exception.BusinessException;
import pdev.com.agendaapi.domain.entities.repository.PacienteRepository;

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
            if (!optPaciente.get().getId().equals(paciente.getId())){
                existeCpf = true;
            }
        }

        if (existeCpf){
            throw new BusinessException("Cpf já se encontrada cadastrado");
        }
        return repository.save(paciente);

    }

    public Paciente alterar(Long id, Paciente paciente){
        Optional<Paciente> optionalPaciente = this.buscarPorId(id);

        if (optionalPaciente.isEmpty()){
            throw new BusinessException("Paciente não encontrado no sistema");
        }
        paciente.setId(id);

        return salvar(paciente);
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
