package pdev.com.agendaapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agendaapi.domain.entities.Paciente;
import pdev.com.agendaapi.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacienteService.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos(){
       List<Paciente> pacientes = pacienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

}
