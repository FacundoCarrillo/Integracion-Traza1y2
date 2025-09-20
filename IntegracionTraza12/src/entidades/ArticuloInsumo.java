package entidades;

import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class ArticuloInsumo extends Articulo {
    private Long id;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;

    public <E> ArticuloInsumo(long l, String harina, double v, Categoria insumos, UnidadMedida kg, HashSet<E> es, double v1, int i, int i1, boolean b) {
    }

}
