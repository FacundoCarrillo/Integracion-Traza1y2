package entidades;

import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "empresa")
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private boolean esCasaMatriz;
    private Domicilio domicilio;// Relación 1 a 1 con Domicilio
    private Set<Articulo> articulos = new HashSet<>();// pueden ser insumos o manufacturados

    private Empresa empresa;
}
