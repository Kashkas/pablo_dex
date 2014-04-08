/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex;
import java.io.*;

/**
 *
 * @author Pablo
 */
public class Dex 
{
    Pokemon[] pokeId;
    Pokemon[] pokeAlf;
    
    Dex() 
    {
        pokeId  = new Pokemon[649];
        pokeAlf = new Pokemon[649];
    }
    
    void cargar()
    {
        try
        {
            String workingDir = System.getProperty("user.dir");
            
            File dex = new File(workingDir+"/pokeDexNac.txt");
            BufferedReader pokeDex = new BufferedReader(new FileReader(dex));
            File stats = new File(workingDir+"/pokeStats.txt");
            BufferedReader pokeStats = new BufferedReader(new FileReader(stats));
            File pokAbc = new File(workingDir+"/pokeDexAbc.txt");
            BufferedReader pokeAbc = new BufferedReader(new FileReader(pokAbc));
            pokeDex.readLine();
            pokeStats.readLine();
            pokeAbc.readLine();
            
            
            while(pokeDex.ready() && pokeStats.ready())
            {   
                String statString[], abc[];
                abc = pokeDex.readLine().split("\t");
                statString = pokeStats.readLine().split("\t");
            
                String numeroString, nombre, tipo1, tipo2, desc;

                numeroString = abc[2].trim();
                nombre = abc[3].trim();
                tipo1 = abc[4].trim();
                try
                {
                    tipo2 = abc[5];
                }catch(ArrayIndexOutOfBoundsException e)
                {
                    tipo2 = "";
                }
                int numero = Integer.parseInt(numeroString);
                int hp = Integer.parseInt(statString[2].trim());
                int atk = Integer.parseInt(statString[3].trim());
                int def = Integer.parseInt(statString[4].trim());
                int spAtk = Integer.parseInt(statString[5].trim());
                int spDef = Integer.parseInt(statString[6].trim());
                int speed = Integer.parseInt(statString[7].trim()); 
                desc = "";
                
                pokeId[numero-1] = new Pokemon(numero, nombre, hp, atk, def, spAtk, spDef, speed, desc, tipo1, tipo2);
            }
        
                
            String[] temp = pokeAbc.readLine().split("\t");
                
            for(int i = 0; pokeAbc.ready(); i++)
            {
                temp = pokeAbc.readLine().split("\t");
                int numero = Integer.parseInt(temp[1].trim());
                pokeAlf[i] = pokeId[numero-1];
            }
            
        
        }
        catch(IOException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
 
    }
 
    void imprimir(int id)
    {
        pokeId[id-1].imprimir();
    }
    
    Pokemon ver(int id)
    {
        return pokeId[id-1];
    }
    
    Pokemon ver(String nombre)
    {
        int bajo, medio, alto;
        bajo = 0;
        alto = 648;
    
        while(bajo < alto)
        {
            medio = (alto + bajo)/2;
            if(pokeAlf[medio].nombre.compareToIgnoreCase(nombre)<0)
            {
                bajo = medio+1;
            }
            if(pokeAlf[medio].nombre.compareToIgnoreCase(nombre)>0)
            {
                alto = medio;
            }
            if(pokeAlf[medio].nombre.compareToIgnoreCase(nombre)==0)
            {
                return pokeAlf[medio];
            }

        }
        if(pokeAlf[bajo].nombre.compareToIgnoreCase(nombre)==0)
        {
            return pokeAlf[bajo];
        }
        
        return null;
    }
    
}
