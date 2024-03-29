package EjerciciosClasesEmpresa;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static EjerciciosClasesEmpresa.MenuJefe.menuJefe;
import static EjerciciosClasesEmpresa.MenuTrabajador.menuTrabajador;
import static EjerciciosClasesEmpresa.MenuEncargado.menuEncargado;
import static EjerciciosClasesEmpresa.TrabajadoresEjemplo.TrabajadoresdeEjemplo;
import static EjerciciosClasesEmpresa.EncargadosdeEjemplo.encargadosejemplo;

public class Empresa {

    public static void main(String[] args) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            char letra = '0';
            Trabajador[] trabajadores = TrabajadoresdeEjemplo();
            Encargado[] encargados = encargadosejemplo();
            Jefe jefe = new Jefe("12345678X", "abracadabra", new Date(50, 1, 1));
            int inventario = 0;

            while(letra != 'c') {

                boolean entrada = false;
                String dni = "";
                String contrasena;

                while (!entrada) {

                    boolean dniinvalido = true;
                    boolean dnilongitud;
                    boolean dniletra;

                    while (dniinvalido) {

                        dnilongitud = true;
                        dniletra = true;

                        System.out.println("DNI:");
                        dni = buffer.readLine();

                        if (dni.length() != 9) {
                            dnilongitud = false;
                            System.out.println("La longitud es incorrecta");
                        }

                        if ((!dni.substring(8).matches("[A-Z]*"))) {
                            dniletra = false;
                            System.out.println("El ultimo caracter no es una letra o no es mayuscula");
                        }

                        if (dnilongitud && dniletra) {
                            dniinvalido = false;
                        }
                    }

                    System.out.println("Contraseña:");
                    contrasena = buffer.readLine();

                        for (int i = 0; i < trabajadores.length; i++) {
                            if(trabajadores[i] == null);
                            else if (Objects.equals(trabajadores[i].getDNI(), dni)) {
                                if (Objects.equals(trabajadores[i].getContrasena(), contrasena)) {
                                    entrada = true;
                                } else {
                                    System.out.println("Usuario o contraseña incorrecta");
                                }
                            }
                        }

                        for (int i = 0; i < encargados.length; i++) {
                            if (encargados[i] == null);
                            else if (Objects.equals(encargados[i].getDNI(), dni)) {
                                if (Objects.equals(encargados[i].getContrasena(), contrasena)) {
                                    entrada = true;
                                } else {
                                    System.out.println("Usuario o contraseña incorrecta");
                                }
                            }
                        }

                    if(Objects.equals(jefe.getDNI(), dni)){
                        if(Objects.equals(jefe.getContrasena(), contrasena)){
                            entrada = true;
                        }
                        else{
                            System.out.println("Usuario o contraseña incorrecta");
                        }
                    }
                    else if (!entrada){
                        System.out.println("Usuario o contraseña incorrecta");
                    }

                }

                for(int i = 0; i < trabajadores.length; i++){
                    if(trabajadores[i] == null){}
                    else if(Objects.equals(trabajadores[i].getDNI(), dni)){
                        menuTrabajador(jefe, trabajadores[i].getEncargado(), inventario, encargados, trabajadores[i], letra);
                    }
                }
                for(int i = 0; i < encargados.length; i++){
                    if(encargados[i] == null){}
                    else if(Objects.equals(encargados[i].getDNI(), dni)){
                        menuEncargado(jefe, encargados[i], encargados, inventario, trabajadores, letra);
                    }
                }
                if(Objects.equals(jefe.getDNI(), dni)){
                    menuJefe(jefe, trabajadores, encargados, letra);
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    protected static float valorventa(){
        float venta = (float) (Math.random()*1001);
        return venta;
    }

    protected static boolean random50(){
        int moneda = (int) (Math.random()*2);
        return moneda == 1;
    }
    protected static boolean random20(){
        int dado = (int) (Math.random()*5);
        return dado == 0;
    }
    protected static Trabajador[] aquiendespedir20(Trabajador[] trabajador){

        Trabajador[] nuevalista;

        nuevalista = trabajador;

        Arrays.sort(nuevalista);

        int contador = 1;
        int clientesarepartir = 0;

        for(int i = 0; i < nuevalista.length ; i++){
            if(contador <= 2 && nuevalista[i] != null){
                clientesarepartir = clientesarepartir+nuevalista[i].getClientes();
                System.out.println(nuevalista[i].getDNI() + " despedido, salario = " + nuevalista[i].getSalario() + ", clientes = " + nuevalista[i].getClientes());
                nuevalista[i] = null;
                contador++;
            }
        }

        while(clientesarepartir >0) {
            for (int i = 2; i < nuevalista.length; i++) {
                if(nuevalista[i] == null){}
                else{
                    nuevalista[i].setClientes(nuevalista[i].getClientes()+1);
                    clientesarepartir--;
                }
            }
        }

        nuevalista[0] = null;
        nuevalista[1] = null;




        return nuevalista;

    }

}

