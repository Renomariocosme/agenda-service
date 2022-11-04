package pdev.com.agendaapi.mapper;

import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.request.PacienteRequest;
import pdev.com.agendaapi.response.PacienteResponse;

public class PacienteMapper {

    public static Paciente toPaciente(PacienteRequest request){
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setSobrenome(request.getSobrenome());
        paciente.setEmail(request.getEmail());
        paciente.setCpf(request.getCpf());
        return paciente;
    }
    public static PacienteResponse toPacienteResponse(Paciente paciente){
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        paciente.setNome(paciente.getNome());
        paciente.setSobrenome(paciente.getSobrenome());
        paciente.setEmail(paciente.getEmail());
        paciente.setCpf(paciente.getCpf());
        return response;
    }
}
