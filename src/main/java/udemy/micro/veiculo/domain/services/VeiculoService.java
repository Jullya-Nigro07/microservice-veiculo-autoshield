package udemy.micro.veiculo.domain.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import udemy.micro.veiculo.adapters.output.entity.Veiculo;
import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;
import udemy.micro.veiculo.domain.dtos.VeiculoUpdateRequest;
import udemy.micro.veiculo.domain.exceptions.DataNotFound;
import udemy.micro.veiculo.domain.exceptions.DuplicateException;
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
            throw new DuplicateException("Esse CHASSI já está cadastrado!");
        }

        Veiculo veiculo = new Veiculo(veiculoRequest.placa(),
                veiculoRequest.chassi(),
                veiculoRequest.marca(),
                veiculoRequest.modelo(),
                veiculoRequest.ano(),
                veiculoRequest.cor(),
                veiculoRequest.quilometragem());

        Veiculo veiculoSave = veiculoOutputPort.save(veiculo);

        return VeiculoResponse.builder()
                .placa(veiculoSave.getPlaca())
                .chassi(veiculoSave.getChassi())
                .modelo(veiculoSave.getModelo())
                .marca(veiculoSave.getMarca())
                .ano(veiculoSave.getAno())
                .build();
    }

    @Override
    public VeiculoResponse searchVeiculo(String chassi) {
        Veiculo veiculo = veiculoOutputPort.findVeiculoByChassi(chassi).orElseThrow(
                () -> new DataNotFound("O Veiculo '" + chassi + "', não foi encontrado!"));

        return VeiculoResponse.builder()
                .placa(veiculo.getPlaca())
                .chassi(veiculo.getChassi())
                .modelo(veiculo.getModelo())
                .marca(veiculo.getMarca())
                .ano(veiculo.getAno())
                .build();
    }

    @Override
    @Transactional
    public VeiculoResponse updateVeiculo(VeiculoUpdateRequest dados, String chassi){
        Veiculo veiculo = veiculoOutputPort.findVeiculoByChassi(chassi).orElseThrow(
                () -> new DataNotFound("Não foi encontrado o veiculo que pertence a esse chassi: '" + chassi + "'"));

        if (dados.placa() != null) veiculo.setPlaca(dados.placa());
        if (dados.marca() != null) veiculo.setMarca(dados.marca());
        if (dados.modelo() != null) veiculo.setModelo(dados.modelo());
        if (dados.cor() != null) veiculo.setCor(dados.cor());
        if (dados.ano() != null) veiculo.setAno(dados.ano());
        if (dados.quilometragem() != null) veiculo.setQuilometragem(dados.quilometragem());

        Veiculo veiculoResponse = veiculoOutputPort.save(veiculo);

        return VeiculoResponse.builder()
                .placa(veiculoResponse.getPlaca())
                .chassi(veiculoResponse.getChassi())
                .modelo(veiculoResponse.getModelo())
                .marca(veiculoResponse.getMarca())
                .ano(veiculoResponse.getAno())
                .build();
    }

    @Override
    @Transactional
    public VeiculoResponse deleteVeiculo(String chassi){
        Veiculo veiculo = veiculoOutputPort.findVeiculoByChassi(chassi).orElseThrow(
                () -> new DataNotFound("Não foi encontrado o veiculo que pertence a esse chassi: '" + chassi + "'"));

        Veiculo veiculoBuscado = veiculoOutputPort.deleteVeiculoByChassi(veiculo.getChassi());

        return VeiculoResponse.builder()
                .placa(veiculoBuscado.getPlaca())
                .chassi(veiculoBuscado.getChassi())
                .modelo(veiculoBuscado.getModelo())
                .marca(veiculoBuscado.getMarca())
                .ano(veiculoBuscado.getAno())
                .build();
    }
}
