package pdev.com.agendaapi.domain.entities.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;
import pdev.com.agendaapi.domain.entities.Agenda;
import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.domain.entities.repository.AgendaRepository;
import pdev.com.agendaapi.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {
    @InjectMocks
    AgendaService agendaService;

    @Mock
    PacienteService pacienteService;

    @Mock
    AgendaRepository agendaRepository;

    @Captor
    ArgumentCaptor<Agenda> agendaCaptor;

    @Test
    @DisplayName("Deve salvar o agendamento com sucesso")
    void salvarComSucesso() {

        // arrange(montagem do codigo)
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Paciente");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.buscarPorId(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
        Mockito.when(agendaRepository.findByHorario(now)).thenReturn(Optional.empty());

        // Action(o que ele vai fazer)
        agendaService.salvar(agenda);

        // Assertions
        Mockito.verify(pacienteService).buscarPorId(agenda.getPaciente().getId());
        Mockito.verify(agendaRepository).findByHorario(now);
        Mockito.verify(agendaRepository).save(agendaCaptor.capture());
        Agenda agendaSalva = agendaCaptor.getValue();

        Assertions.assertThat(agendaSalva.getPaciente()).isNotNull();
        Assertions.assertThat(agendaSalva.getDataCriacao()).isNotNull();

    }
    @Test
    @DisplayName("Não deve salvar agendamento sem paciente")
    void salvarErrorPacienteNaoEncontrado(){

        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Paciente");

        agenda.setPaciente(paciente);


        Mockito.when(pacienteService.buscarPorId(ArgumentMatchers.any())).thenReturn(Optional.empty());
        Mockito.when(agendaRepository.findByHorario(ArgumentMatchers.any())).thenReturn(Optional.empty());


        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            agendaService.salvar(agenda);
        });

        Assertions.assertThat(businessException.getMessage()).isEqualTo("Paciente não encontrado");
        Assertions.assertThat(businessException.getMessage()).isEqualTo("Já existe um agendamento referente a esse horário");

    }

}