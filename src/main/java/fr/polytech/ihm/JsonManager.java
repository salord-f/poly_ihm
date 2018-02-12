package fr.polytech.ihm;

import fr.polytech.ihm.model.Incident;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonManager {

    File filePath= new File ("./jsonFile.json");

    public JsonManager ()
    {

    }

    public void writeJson(Incident incident)
    {
        if(filePath.exists() && !filePath.isDirectory()) {
            modifyJson(incident);
        }
        else
        {
            createJson(incident);
        }
    }

    void modifyJson(Incident incident)
    {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray list = (JSONArray) jsonObject.get("messages");

            list.add(getParameters(incident));

            JSONObject obj2 = new JSONObject();
            obj2.put("messages", list);


            try (FileWriter file = new FileWriter(filePath)) {
                file.write(obj2.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void createJson(Incident incident)
    {
        JSONArray list = new JSONArray();
        //JSONArray list2 = new JSONArray();

        list.add(getParameters(incident));

        //list.add(list2);

        JSONObject obj = new JSONObject();
        obj.put("messages", list);


        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray getParameters(Incident incident)
    {
        JSONArray list2 = new JSONArray();
        list2.add(incident.getCategorie());
        list2.add(incident.getTitre());
        list2.add(incident.getDescrpition());
        list2.add(incident.getImage());
        list2.add(incident.getLocalisation());
        //list2.add(incident.getlocalizationDetailConvert);
        list2.add(incident.getUrgence());
        list2.add(incident.getEmail());
        list2.add(incident.getEmailDomaine());
        list2.add(incident.getDate());

        return list2;
    }
}
