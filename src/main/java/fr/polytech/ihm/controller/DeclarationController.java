package fr.polytech.ihm.controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class DeclarationController {



    void writeJson()
    {
        JSONArray list = new JSONArray();
        JSONArray list2 = new JSONArray();
        list2.add("msg 2");
        list2.add("msg 3");

        list.add(list2);

        JSONObject obj = new JSONObject();
        obj.put("messages", list);


        try (FileWriter file = new FileWriter("C:\\Users\\Polytech\\Desktop\\test.json")) {
            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
