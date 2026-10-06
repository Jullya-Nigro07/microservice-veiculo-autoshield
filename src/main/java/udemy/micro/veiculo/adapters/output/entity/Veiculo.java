package udemy.micro.veiculo.adapters.output.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String placa;

    @Column
    private String chassi;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private Integer ano;

    @Column
    private String cor;

    @Column
    private Integer quilometragem;

    public Veiculo(String placa, String chassi, String marca, String modelo, Integer ano, String cor, Integer quilometragem) {
        this.placa = placa;
        this.chassi = chassi;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.quilometragem = quilometragem;
    }
}