/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex;

import java.io.*;
import static pokedex.PokeDex.dex;

/**
 *
 * @author Pablo
 */
public class Main 
{
    
    public static void main(String[] args) throws Exception
    {
        PokeDex P = new PokeDex("PokeDex");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Escriba el pokemon a ver: ");
        String linea = in.readLine();
        int numero = Integer.parseInt(linea);
        dex.imprimir(numero);
        System.out.print("Buscar Pokemon por nombre: ");
        linea = in.readLine();
        Pokemon resultado = dex.ver(linea);
        if(resultado==null)
        {
            System.out.println("No existe");
        }
        else
        {
            System.out.println(resultado.numero);
        }   
    }
    
}
