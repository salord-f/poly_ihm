package fr.polytech.ihm;

import fr.polytech.ihm.model.Incident;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    private File jsonFile;

    public JsonManager ()
    {
        jsonFile = new File("./jsonFile.json");
    }

    public void writeJson(Incident incident)
    {
        if(jsonFile.exists() && !jsonFile.isDirectory()) {
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

            Object obj = parser.parse(new FileReader(jsonFile));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray list = (JSONArray) jsonObject.get("messages");

            list.add(getParameters(incident));

            JSONObject obj2 = new JSONObject();
            obj2.put("messages", list);


            try (FileWriter file = new FileWriter(jsonFile)) {
                file.write(obj2.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    void createJson(Incident incident)
    {
        JSONArray list = new JSONArray();

        list.add(getParameters(incident));

        JSONObject obj = new JSONObject();
        obj.put("messages", list);

        try (FileWriter file = new FileWriter(jsonFile)) {
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
        list2.add(incident.getDescription());
        list2.add(incident.getImage());
        list2.add(incident.getLocalisation());
        //list2.add(incident.getlocalizationDetailConvert);
        list2.add(incident.getUrgence());
        list2.add(incident.getEmail());
        list2.add(incident.getEmailDomaine());
        list2.add(incident.getDate());

        return list2;
    }

    public List<Incident> getIncidents() {

        JSONParser parser = new JSONParser();
        List<Incident> incidents = new ArrayList<>();

        try {

            JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(jsonFile));

            JSONArray list = (JSONArray) jsonObject.get("messages");

            list.forEach((Object jsonArrayAsObject) -> {
                JSONArray jsonArray = (JSONArray)jsonArrayAsObject;
                incidents.add(new Incident((String)jsonArray.get(0), (String)jsonArray.get(1), (String)jsonArray.get(2), (String)jsonArray.get(3), (String)jsonArray.get(4), Integer.parseInt(jsonArray.get(5).toString()), (String)jsonArray.get(6), (String)jsonArray.get(7), (String)jsonArray.get(8)));
            });

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return incidents;
    }
}
