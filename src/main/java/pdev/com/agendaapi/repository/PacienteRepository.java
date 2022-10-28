package pdev.com.agendaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdev.com.agendaapi.domain.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
