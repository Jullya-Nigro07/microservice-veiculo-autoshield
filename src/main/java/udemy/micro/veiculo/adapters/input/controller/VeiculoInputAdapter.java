package udemy.micro.veiculo.adapters.input.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;
import udemy.micro.veiculo.port.input.VeiculoInputPort;

@RestController
@RequestMapping("/veiculos")
public class VeiculoInputAdapter {
    private final VeiculoInputPort veiculoInputPort;

    public VeiculoInputAdapter(VeiculoInputPort veiculoInputPort){
        this.veiculoInputPort = veiculoInputPort;
    }

    @PostMapping("/register")
    public VeiculoResponse registerVeiculo(@Valid @RequestBody VeiculoRequest veiculoRequest){
        return veiculoInputPort.registerVeiculo(veiculoRequest);
    }

    @GetMapping("/search/{chassi}")
    public VeiculoResponse searchVeiculo(@PathVariable String chassi){
        return veiculoInputPort.searchVeiculo(chassi);
    }
}
