package zegel.farm_milagro.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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
    String  nombre;
    Double precioUnitario;
    Integer stockActual;
    String urlImg;
    String tipoEnvase;

    // Usando @Lob para textos largos
    @Lob
    @Column(columnDefinition = "TEXT")
    private String precauciones;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String composicion;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String recomendaciones;

    // Campo para volumen
    private String volumen; // "400ml"

    // Relación oculta
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonIgnore
    private Categoria categoria;

    // Categoría serializada
    @JsonProperty("categoria")
    public Map<String, Object> getCategoriaJson() {
        if (categoria == null) return null;

        return Map.of(
                "id", categoria.getIdCategoria(),
                "nombre", categoria.getNombreCategoria()
        );
    }
}
