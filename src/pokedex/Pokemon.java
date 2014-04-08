/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex;

/**
 *
 * @author Pablo
 */
public class Pokemon 
{
    int numero;
    String nombre, descripcion, type1, type2;
    int[] stats;
    
    Pokemon()
    {
        nombre = "Missingno";
        stats = null;
        descripcion = "Falla";
    }
    
    Pokemon(int num, String nom, int hp, int atk, int def, int spAtk, int spDef, int speed, String desc, String tipo1, String tipo2)
    {
        numero = num;
        nombre = nom;
        stats = new int[6];
        stats[0] = hp;
        stats[1] = atk;
        stats[2] = def;
        stats[3] = spAtk;
        stats[4] = spDef;
        stats[5] = speed;
        descripcion = desc;
        type1 = tipo1;
        if(tipo2.equals(""))
        {
            type2 = "";
        }
        else
        {
            type2 = tipo2;
        }
    }
    
    void imprimir()
    {
        System.out.print(numero+" "+nombre+" "+type1);
        if(!type2.equals(""))
        {
            System.out.print(" "+type2);
        }
        System.out.println();
    }
    
}
