package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sucursales")
public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private long cuil;
    private String logo;


    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();


}
