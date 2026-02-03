package zegel.farm_milagro.modelo;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarritoCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false, updatable = false) // No se puede cambiar cliente
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medicamento", nullable = false)
    @JsonIgnoreProperties({"precioUnitario", "stockActual", "caracteristicas"})
    private Medicamento medicamento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // Asignado automáticamente por la BD
    @Column(name = "fecha_agregado", insertable = false, updatable = false)
    private LocalDateTime fechaAgregado;

    @Enumerated(EnumType.STRING)
    private EstadoCarrito estado = EstadoCarrito.activo;

    public enum EstadoCarrito {
        activo, pendiente, procesado
    }
}
