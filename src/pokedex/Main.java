/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex;

import pokedex.clases.Pokemon;
import pokedex.clases.PokeDexGuiTest;
import pokedex.clases.PokeDex;
import java.io.*;


/**
 *
 * @author Pablo
 */
public class Main 
{
    
    public static void main(String[] args) throws Exception
    {
        //PokeDexGuiTest P = new PokeDexGuiTest("PokeDex");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PokeDex dex = new PokeDex();
        dex.cargar();
        System.out.print("Escriba el pokemon a ver: ");
        String linea = in.readLine();
        int numero = Integer.parseInt(linea);
        //P.dex.imprimir(numero);
        System.out.print("Buscar Pokemon por nombre: ");
        linea = in.readLine();
        Pokemon resultado = dex.ver(linea);//P.dex.ver(linea);
        if(resultado==null)
        {
            System.out.println("No existe");
        }
        else
        {
            resultado.imprimir();
        }
        dex.guardarSQLite();
    }
    
}
