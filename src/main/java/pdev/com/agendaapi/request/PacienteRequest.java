package pdev.com.agendaapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotBlank(message = "Nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatório")
    private String sobrenome;

    private String email;

    @NotBlank(message = "CPF do paciente é obrigatório")
    private String cpf;
}
