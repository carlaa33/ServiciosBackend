package mx.edu.ittepic.appbackend;

/**
 * Created by carla on 14/04/18.
 */

public class Alumno {
    int noctrl;
    String nombre;

    public Alumno(){

    }

    public Alumno(int noctrl,String nombre){
        this.noctrl=noctrl;
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public int getNoctrl(){
        return noctrl;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setNoctrl(int noctrl){
        this.noctrl=noctrl;
    }
}
