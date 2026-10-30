package udemy.micro.veiculo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import udemy.micro.veiculo.adapters.output.entity.Veiculo;
import udemy.micro.veiculo.domain.dtos.VeiculoRequest;
import udemy.micro.veiculo.domain.dtos.VeiculoResponse;
import udemy.micro.veiculo.domain.dtos.VeiculoUpdateRequest;
import udemy.micro.veiculo.domain.exceptions.DataNotFound;
import udemy.micro.veiculo.domain.exceptions.DuplicateException;
import udemy.micro.veiculo.domain.services.VeiculoService;
import udemy.micro.veiculo.port.output.VeiculoOutputPort;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {
    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoOutputPort veiculoOutputPort;

    @Test
    void deveCriarVeiculo(){
        VeiculoRequest veiculo = new VeiculoRequest("testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2026,
                "testCor",
                13102026);

        Veiculo veiculoEntity = new Veiculo("testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2026,
                "testCor",
                13102026);

        Mockito.when(veiculoOutputPort.save(Mockito.any())).thenReturn(veiculoEntity);
        VeiculoResponse response = veiculoService.registerVeiculo(veiculo);

        assertNotNull(response);
        assertEquals("testChassi", response.chassi());
        Mockito.verify(veiculoOutputPort).save(Mockito.any());
    }

    @Test
    void deveImpedirChassiDuplicado(){
        VeiculoRequest veiculo = new VeiculoRequest("testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2026,
                "testCor",
                13102026);

        Mockito.when(veiculoOutputPort.findVeiculoByChassi(veiculo.chassi()))
                .thenReturn(Optional.of(new Veiculo()));

        assertThrows(DuplicateException.class, () -> {
            veiculoService.registerVeiculo(veiculo);
        });

    }

    @Test
    void deveBuscarVeiculoExistente(){
        VeiculoRequest veiculo = new VeiculoRequest("testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2026,
                "testCor",
                13102026);

        Mockito.when(veiculoOutputPort.findVeiculoByChassi(veiculo.chassi()))
                .thenReturn(Optional.of(new Veiculo()));
        VeiculoResponse response = veiculoService.searchVeiculo(veiculo.chassi());

        assertNotNull(response);
        Mockito.verify(veiculoOutputPort).findVeiculoByChassi(Mockito.any());

    }

    @Test
    void deveAtualizarVeiculoExistente() {
        VeiculoUpdateRequest dados = VeiculoUpdateRequest.builder()
                .placa("novaPlaca")
                .cor("novaCor")
                .quilometragem(15000)
                .build();

        Veiculo veiculo = new Veiculo(
                "testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2025,
                "testCor",
                13102026
        );

        Mockito.when(veiculoOutputPort.findVeiculoByChassi(veiculo.getChassi()))
                .thenReturn(Optional.of(veiculo));

        Mockito.when(veiculoOutputPort.save(Mockito.any(Veiculo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        VeiculoResponse response = veiculoService.updateVeiculo(dados, veiculo.getChassi());

        assertNotNull(response);
        assertEquals("novaPlaca", response.placa());
        assertEquals("novaCor", veiculo.getCor());
        assertEquals(2025, response.ano());

        Mockito.verify(veiculoOutputPort).findVeiculoByChassi(veiculo.getChassi());
        Mockito.verify(veiculoOutputPort).save(Mockito.any(Veiculo.class));
    }

    @Test
    void deveNaoAtualizarVeiculoInexistente(){
        String chassi = "chassiInexistente";
        Mockito.when(veiculoOutputPort.findVeiculoByChassi(chassi))
                .thenReturn(Optional.empty());

        assertThrows(DataNotFound.class, () -> {
            veiculoService.updateVeiculo(null, chassi);
        });

        Mockito.verify(veiculoOutputPort).findVeiculoByChassi(chassi);
    }

    @Test
    void deveDeletarVeiculo() {
        Veiculo veiculo = new Veiculo(
                "testePlaca",
                "testChassi",
                "testMarca",
                "testModelo",
                2026,
                "testCor",
                13102026
        );

        Mockito.when(veiculoOutputPort.findVeiculoByChassi(veiculo.getChassi()))
                .thenReturn(Optional.of(veiculo));
        Mockito.when(veiculoOutputPort.deleteVeiculoByChassi(veiculo.getChassi()))
                .thenReturn(veiculo);

        VeiculoResponse response = veiculoService.deleteVeiculo(veiculo.getChassi());

        assertNotNull(response);
        assertEquals(veiculo.getChassi(), response.chassi());
        Mockito.verify(veiculoOutputPort).findVeiculoByChassi(veiculo.getChassi());
        Mockito.verify(veiculoOutputPort).deleteVeiculoByChassi(veiculo.getChassi());
    }
}
