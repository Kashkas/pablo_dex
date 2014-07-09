/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedex.clases;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import pokedex.clases.Pokemon;

/**
 *
 * @author Pablo
 */
public class PokeDex 
{
    Pokemon[] pokeId;
    Pokemon[] pokeAlf;
    
    public PokeDex() 
    {
        pokeId  = new Pokemon[649];
        pokeAlf = new Pokemon[649];
    }
    
    public void cargar()
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
                int avg = Integer.parseInt(statString[8].trim());
                desc = "";
                
                pokeId[numero-1] = new Pokemon(numero, nombre, hp, atk, def, spAtk, spDef, speed, avg, desc, tipo1, tipo2);
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
 
    public void imprimir(int id)
    {
        pokeId[id-1].imprimir();
    }
    
    public Pokemon ver(int id)
    {
        return pokeId[id-1];
    }
    
    public Pokemon ver(String nombre)
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
    
    public void guardarSQLite(){
        Connection con = null;
        Statement state = null;
        try{
            Class.forName("org.sqlite.JDBC");
            String dir = System.getProperty("user.dir");
            con = DriverManager.getConnection("jdbc:sqlite:"+dir+"pokedex.db");
            con.setAutoCommit(false);
            state = con.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS POKEMON" +
                    "(ID    INT     PRIMARY KEY     NOT NULL," +
                    "NAME TEXT    NOT NULL," +
                    "TYPE1  TEXT    NOT NULL," +
                    "TYPE2  TEXT," +
                    "HP     INT     NOT NULL," +
                    "ATK    INT     NOT NULL," +
                    "DEF    INT     NOT NULL," +
                    "SPATK  INT     NOT NULL," +
                    "SPDEF  INT     NOT NULL," +
                    "SPEED  INT     NOT NULL," +
                    "AVG    INT     NOT NULL," +
                    "DESCR  TEXT);"; 
            state = con.createStatement();
            state.executeUpdate(sql);
            
            for(int i = 0; i<649; i++){
                String sql1 = "INSERT INTO POKEMON (ID, NAME, TYPE1, TYPE2, HP, ATK, DEF, SPATK, SPDEF, SPEED, AVG) " +
                         "VALUES (" +
                        pokeId[i].numero+", " +
                        pokeId[i].nombre+", " +
                        pokeId[i].type1+", " +
                        pokeId[i].type2+", " +
                        pokeId[i].stats[0]+", " +
                        pokeId[i].stats[1]+", " +
                        pokeId[i].stats[2]+", " +
                        pokeId[i].stats[3]+", " +
                        pokeId[i].stats[4]+", " +
                        pokeId[i].stats[5]+", " +
                        pokeId[i].stats[6]+", " +
                        pokeId[i].descripcion+", " +
                        ");";
            }
            
        }catch(Exception e){
            
        }
    }
    
}
