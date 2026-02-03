package zegel.farm_milagro.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CaracteristicaMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCaracteristica;
    String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    @JsonBackReference
    private Medicamento medicamento;

    // Este método hará que el campo "idMedicamento" aparezca en el JSON
    @JsonProperty("idMedicamento")
    public Integer getIdMedicamento() {
        if (medicamento != null) return medicamento.getIdMedicamento();
        return null;
    }
}
