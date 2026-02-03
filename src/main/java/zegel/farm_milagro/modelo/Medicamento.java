package zegel.farm_milagro.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Evita que Jackson intente serializar propiedades internas agregadas por Hibernate
 * ("hibernateLazyInitializer" y "handler") que son usadas para la carga perezosa (lazy loading).
 * Estas propiedades no forman parte del modelo real y provocan errores como
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMedicamento;
    String  nombreComercial;
    String nombreGenerico;
    String laboratorio;
    Double precioUnitario;
    Integer stockActual;
    String urlImg;
    String modoUso;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaracteristicaMedicamento> caracteristicas;
}
