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

            JSONArray list = (JSONArray) obj;

            list.add(getParameters(incident));

            try (FileWriter file = new FileWriter(jsonFile)) {
                file.write(list.toJSONString());
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

        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(list.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getParameters(Incident incident)
    {
        JSONObject obj = new JSONObject();
        obj.put("Category",incident.getCategory().getName());
        obj.put("Title",incident.getTitle());
        obj.put("Description",incident.getDescription());
        obj.put("Image",incident.getImage());
        obj.put("Location",incident.getLocation().getName());
        obj.put("LocationDetail",incident.getLocationDetail());
        obj.put("Emergency",incident.getEmergency().ordinal());
        obj.put("Email",incident.getEmail());
        obj.put("EmailDomain",incident.getEmailDomain());
        obj.put("Date",incident.getDate());

        return obj;
    }

    public List<Incident> getIncidents() {

        JSONParser parser = new JSONParser();
        List<Incident> incidents = new ArrayList<>();

        if (jsonFile.exists() && !jsonFile.isDirectory()) {
            try {

                JSONArray list= (JSONArray) parser.parse(new FileReader(jsonFile));

                list.forEach((Object obj) -> {
                    JSONObject jsonObject=(JSONObject) obj;
                    incidents.add(new Incident((String)jsonObject.get("Category"),
                            (String)jsonObject.get("Title"),
                            (String)jsonObject.get("Description"),
                            (String)jsonObject.get("Image"),
                            (String)jsonObject.get("Location"),
                            (String)jsonObject.get("LocationDetail"),
                            Integer.parseInt(jsonObject.get("Emergency").toString()),
                            (String)jsonObject.get("Email"),
                            (String)jsonObject.get("EmailDomain"),
                            (String)jsonObject.get("Date")));
                });

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        }

        return incidents;
    }


}
