package entidades;

import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class ArticuloManufacturado extends Articulo {
    private Long id;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;

    private Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();


}
