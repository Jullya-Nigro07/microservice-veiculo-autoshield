package udemy.micro.veiculo.domain.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import udemy.micro.veiculo.adapters.output.entity.Veiculo;
import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;
import udemy.micro.veiculo.port.input.VeiculoInputPort;
import udemy.micro.veiculo.port.output.VeiculoOutputPort;

@Service
public class VeiculoService implements VeiculoInputPort {
    private final VeiculoOutputPort veiculoOutputPort;
    public VeiculoService(VeiculoOutputPort veiculoOutputPort){
        this.veiculoOutputPort = veiculoOutputPort;
    }

    @Override
    @Transactional
    public VeiculoResponse registerVeiculo(VeiculoRequest veiculoRequest) {
        if (veiculoOutputPort.findVeiculoByChassi(veiculoRequest.chassi()).isPresent()) {
            throw new RuntimeException("CHASSI já cadastrado nesse sistema!");
        }

        Veiculo veiculo = new Veiculo(veiculoRequest.placa(),
                veiculoRequest.chassi(),
                veiculoRequest.marca(),
                veiculoRequest.modelo(),
                veiculoRequest.ano(),
                veiculoRequest.cor(),
                veiculoRequest.quilometragem());

        Veiculo veiculoSave = veiculoOutputPort.save(veiculo);
        VeiculoResponse veiculoResponse = new VeiculoResponse(veiculoSave.getPlaca(),
                veiculoSave.getModelo(),
                veiculoSave.getAno(),
                veiculoSave.getCor());

        return veiculoResponse;
    }

    @Override
    public VeiculoResponse searchVeiculo(String chassi) {
        Veiculo veiculo = veiculoOutputPort.findVeiculoByChassi(chassi).orElseThrow(() -> new RuntimeException("Não encontrado!"));
        VeiculoResponse veiculoResponse = new VeiculoResponse(veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getCor());

        return veiculoResponse;

    }
}
