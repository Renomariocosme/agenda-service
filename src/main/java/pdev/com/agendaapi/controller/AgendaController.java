package pdev.com.agendaapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agendaapi.domain.entities.Agenda;
import pdev.com.agendaapi.domain.entities.service.AgendaService;
import pdev.com.agendaapi.mapper.AgendaMapper;
import pdev.com.agendaapi.request.AgendaRequest;
import pdev.com.agendaapi.response.AgendaResponse;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos(){
        List<Agenda> agendas = service.buscarTodos();
        List<AgendaResponse> agendaResponses = mapper.toAgendaResponseList(agendas);

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id){
        Optional<Agenda> optAgenda = service.buscarPorId(id);

        if (optAgenda.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(optAgenda.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@RequestBody AgendaRequest request){
        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaSalva = service.salvar(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }
}