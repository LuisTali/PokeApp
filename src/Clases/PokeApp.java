package Clases;

import java.io.IOException;
import java.net.HttpURLConnection; //Para hacer getRequest de una URL.
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.lang.*;

//File->projectStructure->libreriaAñadida y aqui se importa.
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import javax.swing.*;

public class PokeApp {

    ArrayList<Pokemon> lastCharged = new ArrayList<>(); //Guarda los ultimos pokemones cargados.
    String lastType = "";
    HashSet<String> types = new HashSet<>(); //HashSet permite guardar objetos pero no mantiene su orden.

    public Iterator pokemonsByTypeIterator(String type){
        //ArrayList<Pokemon> pkArray = new ArrayList<>();
        if(lastType.equals(type)){
            return lastCharged.iterator();
        }
        else {
            try {
                System.out.println("pls wait");
                StringBuilder info = getPokemonsByType(type);
                JSONObject dataO = new JSONObject(String.valueOf(info));
                JSONArray array = dataO.getJSONArray("pokemon");
                //for(int i = 0; i < array.length(); i++){ //En caso de querer todos, descomentar esta linea.
                if(array.length()>0) {
                    lastType = type; //Se setea la variable de la App para que corrobore la proxima vez que se filtra por Type.
                    lastCharged = new ArrayList<>();
                    for (int i = 0; i < 20; i++) { //Carga y muestra los primeros 20 para que no tarde tanto en cargarlos.
                        String name = array.getJSONObject(i).getJSONObject("pokemon").getString("name");
                        Pokemon pk = getInfoPokemonByName(name);
                        lastCharged.add(pk);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No hay pokemones de ese tipo");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return lastCharged.iterator();
        }
    }

    public StringBuilder getPokemonsByType(String type){
        StringBuilder infoString = new StringBuilder();
        try {
            URL url = new URL("https://pokeapi.co/api/v2/type/" + type);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCod = con.getResponseCode();
            if(responseCod!=200){
                System.out.println("ERROR " + responseCod);
            }else{
                Scanner sc = new Scanner(url.openStream()); //openStream abre una conexion con esa URL y retorna lo leido de esa conexion.
                while (sc.hasNext()){
                    infoString.append(sc.nextLine());
                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infoString;
    }

    public StringBuilder getTypes(){ //Retorna los distintos tipos de Pokemon desde la API.
        StringBuilder infoString = new StringBuilder();
        try {
            URL url = new URL("https://pokeapi.co/api/v2/type/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCod = con.getResponseCode();
            if(responseCod != 200){
                System.out.println("ERROR " + responseCod);
            }else{
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()){
                    infoString.append(sc.nextLine());
                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infoString;
    }

    public Object[] typesToArray(){ //Se usa para llenar el comboBox con los distintos tipos de Pokemon.
        HashSet<String> set = new HashSet<>();
        try {
            JSONObject dataO = new JSONObject(String.valueOf(getTypes()));
            JSONArray arrayJ = dataO.getJSONArray("results");
            for(int i = 0; i < arrayJ.length(); i++){
                set.add(arrayJ.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return set.toArray();
    }

    public Pokemon getInfoPokemonByName(String pokeName){

        Pokemon pk = new Pokemon();

        try {
            StringBuilder info = getPokemonByName(pokeName);
            JSONObject dataO = new JSONObject(String.valueOf(info));

            String n = dataO.getJSONArray("forms").getJSONObject(0).getString("name");
            String t = dataO.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");
            String m1 = dataO.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name");
            String m2 = dataO.getJSONArray("moves").getJSONObject(1).getJSONObject("move").getString("name");
            String m3 = dataO.getJSONArray("moves").getJSONObject(2).getJSONObject("move").getString("name");
            int hp = dataO.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attack = dataO.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            int defense = dataO.getJSONArray("stats").getJSONObject(2).getInt("base_stat");
            int speed = dataO.getJSONArray("stats").getJSONObject(5).getInt("base_stat");
            String urlImage = dataO.getJSONObject("sprites").getString("front_default");

            String name = n.substring(0,1).toUpperCase() + n.substring(1).toLowerCase();
            String type = t.substring(0,1).toUpperCase() + t.substring(1).toLowerCase();
            String move1 = m1.substring(0,1).toUpperCase() + m1.substring(1).toLowerCase();
            String move2 = m2.substring(0,1).toUpperCase() + m2.substring(1).toLowerCase();
            String move3 = m3.substring(0,1).toUpperCase() + m3.substring(1).toLowerCase();

            pk.setName(name);
            pk.setType(type);
            pk.setMove1(move1);
            pk.setMove2(move2);
            pk.setMove3(move3);
            pk.setHp(hp);
            pk.setAttack(attack);
            pk.setDefense(defense);
            pk.setSpeed(speed);
            pk.setUrlImage(urlImage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pk;
    }

    public StringBuilder getPokemonByName(String pokeName){
        StringBuilder infoString = new StringBuilder();
        try {
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + pokeName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCod = con.getResponseCode();
            if(responseCod!=200){
                System.out.println("ERROr" + responseCod);
            }else{
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext()){
                    infoString.append(sc.nextLine());
                }
                sc.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return infoString;
    }



}
