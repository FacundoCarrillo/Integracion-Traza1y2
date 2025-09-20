import entidades.*;
import repositorios.InMemoryRepository;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // ======================= REPOSITORIOS =======================
        InMemoryRepository<Empresa> empresaRepo = new InMemoryRepository<>();
        InMemoryRepository<Articulo> articuloRepo = new InMemoryRepository<>();

        System.out.println(" ----------- INICIO DEL SISTEMA ----------");

        // ======================= PAÍS, PROVINCIAS Y LOCALIDADES =======================
        Pais argentina = Pais.builder().nombre("Argentina").build();

        Provincia buenosAires = Provincia.builder().id(1L).nombre("Buenos Aires").pais(argentina).build();
        Provincia cordoba = Provincia.builder().id(2L).nombre("Córdoba").pais(argentina).build();

        Localidad caba = Localidad.builder().id(1L).nombre("CABA").provincia(buenosAires).build();
        Localidad laPlata = Localidad.builder().id(2L).nombre("La Plata").provincia(buenosAires).build();
        Localidad cordobaCapital = Localidad.builder().id(3L).nombre("Córdoba Capital").provincia(cordoba).build();
        Localidad villaCarlosPaz = Localidad.builder().id(4L).nombre("Villa Carlos Paz").provincia(cordoba).build();

        // ======================= DOMICILIOS =======================
        Domicilio domicilio1 = Domicilio.builder().id(1L).calle("Av. Siempre Viva").numero(123).cp(1000).localidad(caba).build();
        Domicilio domicilio2 = Domicilio.builder().id(2L).calle("Calle 50").numero(200).cp(1900).localidad(laPlata).build();
        Domicilio domicilio3 = Domicilio.builder().id(3L).calle("San Martín").numero(300).cp(5000).localidad(cordobaCapital).build();
        Domicilio domicilio4 = Domicilio.builder().id(4L).calle("General Paz").numero(400).cp(5152).localidad(villaCarlosPaz).build();

        // ======================= SUCURSALES =======================
        Sucursal sucursal1 = Sucursal.builder()
                .id(1L).nombre("Sucursal CABA")
                .horaApertura(LocalTime.of(9, 0))
                .horaCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true)
                .domicilio(domicilio1)
                .articulos(new HashSet<>())
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .id(2L).nombre("Sucursal La Plata")
                .horaApertura(LocalTime.of(10, 0))
                .horaCierre(LocalTime.of(19, 0))
                .esCasaMatriz(false)
                .domicilio(domicilio2)
                .articulos(new HashSet<>())
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .id(3L).nombre("Sucursal Córdoba Capital")
                .horaApertura(LocalTime.of(8, 30))
                .horaCierre(LocalTime.of(17, 30))
                .esCasaMatriz(true)
                .domicilio(domicilio3)
                .articulos(new HashSet<>())
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .id(4L).nombre("Sucursal Villa Carlos Paz")
                .horaApertura(LocalTime.of(9, 30))
                .horaCierre(LocalTime.of(20, 0))
                .esCasaMatriz(false)
                .domicilio(domicilio4)
                .articulos(new HashSet<>())
                .build();

        // ======================= EMPRESAS =======================
        Empresa empresa1 = Empresa.builder()
                .id(1L).nombre("Empresa 1").razonSocial("Razon Social 1").cuil(12345678901L)
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2)))
                .build();

        Empresa empresa2 = Empresa.builder()
                .id(2L).nombre("Empresa 2").razonSocial("Razon Social 2").cuil(22225678901L)
                .sucursales(new HashSet<>(Set.of(sucursal3, sucursal4)))
                .build();

        // Vincular empresas en sucursales
        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        empresaRepo.save(empresa1);
        empresaRepo.save(empresa2);

        // ======================= ARTÍCULOS =======================
        ArticuloInsumo queso = ArticuloInsumo.builder()
                .id(1L).denominacion("Queso Muzarella")
                .precioCompra(1200.0).precioVenta(1800.0)
                .stockActual(80).stockMaximo(200).esParaElaborar(true).build();

        ArticuloInsumo harina = ArticuloInsumo.builder()
                .id(2L)
                .denominacion("Harina 000")
                .precioCompra(500.0).precioVenta(900.0)
                .stockActual(150).stockMaximo(500).esParaElaborar(true).build();

        ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder()
                .id(3L)
                .denominacion("Pizza Napolitana")
                .descripcion("Pizza con salsa de tomate, queso y rodajas de tomate fresco")
                .tiempoEstimadoMinutos(30)
                .preparacion("Preparar masa, cubrir con salsa, queso y tomate, luego hornear.")
                .precioVenta(2500.0)
                .build();

        ArticuloManufacturado empanadaCarne = ArticuloManufacturado.builder()
                .id(4L)
                .denominacion("Empanada de Carne")
                .descripcion("Empanada frita o al horno con relleno de carne picada")
                .tiempoEstimadoMinutos(15)
                .preparacion("Preparar relleno, armar las empanadas y hornear o freír.")
                .precioVenta(700.0)
                .build();


        articuloRepo.save(queso);
        articuloRepo.save(harina);
        articuloRepo.save(pizzaNapolitana);
        articuloRepo.save(empanadaCarne);

        // Asignar artículos a sucursales
        sucursal1.getArticulos().addAll(Set.of(queso, pizzaNapolitana));
        sucursal2.getArticulos().addAll(Set.of(harina, empanadaCarne));
        sucursal3.getArticulos().addAll(Set.of(queso, harina, empanadaCarne));
        sucursal4.getArticulos().addAll(Set.of(pizzaNapolitana));

        // ======================= MOSTRAR RESULTADOS =======================
        System.out.println("\nTodas las empresas registradas:");
        empresaRepo.findAll().forEach(System.out::println);

        System.out.println("\n--- Menú de cada sucursal ---");
        empresaRepo.findAll().forEach(emp -> {
            System.out.println("\nEmpresa: " + emp.getNombre());
            emp.getSucursales().forEach(suc -> {
                System.out.println("Sucursal: " + suc.getNombre());
                suc.getArticulos().forEach(art -> System.out.println("   - " + art.getDenominacion() + " ($" + art.getPrecioVenta() + ")"));
            });
        });
    }
}
