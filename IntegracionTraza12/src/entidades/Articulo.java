package entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public abstract class Articulo {
    protected Long id;
    protected String denominacion;
    protected Double precioVenta;

    private Categoria categoria;
    private UnidadMedida unidadMedida;
    private Set<Imagen> imagenes = new HashSet<>();
}
