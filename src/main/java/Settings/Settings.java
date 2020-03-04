package Settings;

import Constants.Environments;
import Constants.Users;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Settings {

    JSONArray environments;
    JSONObject environment;
    JSONArray users;
    JSONObject user;

    public static Settings instance = null;

    private Settings() throws IOException, ParseException {

        Object environmentsFromFile = new JSONParser().parse(new FileReader("C:\\Users\\dp020\\IdeaProjects\\qa_automation\\src\\main\\resources\\Settings\\Environments.json"));
        Object usersFromFile  = new JSONParser().parse(new FileReader("C:\\Users\\dp020\\IdeaProjects\\qa_automation\\src\\main\\resources\\Settings\\Users.json"));
        JSONObject jsonObject = (JSONObject) environmentsFromFile;
        environments = (JSONArray) jsonObject.get("ENVIRONMENTS");
        jsonObject = (JSONObject) usersFromFile;
        users = (JSONArray) jsonObject.get("USERS");
    }

    public synchronized static Settings getInstance() {
        try {
            if (instance == null)
                instance = new Settings();
        } catch (Exception e) {

        }
        return instance;
    }

    public Settings getEnv(Environments env) {
        Iterator envs = environments.iterator();
        while (envs.hasNext()) {
            JSONObject test = (JSONObject) envs.next();
            if (test.keySet().toArray()[0].equals(env.toString()))
                environment = test;
        }
        return this;
    }

    public Settings getUser(Users neededUser) {
        Iterator us = users.iterator();
        while (us.hasNext()) {
            JSONObject testUser = (JSONObject) us.next();
            if (testUser.keySet().toArray()[0].equals(neededUser.toString()))
                user = testUser;
        }
        return this;
    }

    public String getEnvUrl() {
        Iterator claims = environment.values().iterator();
        String url = null;
        while (claims.hasNext()) {
            JSONObject test = (JSONObject) claims.next();
            url = test.values().toArray()[2].toString();
        }
        return url;
    }

    public String getEnvHost() {
        Iterator claims = environment.values().iterator();
        String host = null;
        while (claims.hasNext()) {
            JSONObject test = (JSONObject) claims.next();
            host = test.values().toArray()[1].toString();
        }
        return host;
    }

    public String getUserLogin() {
        Iterator claims = user.values().iterator();
        String userLogin = null;
        while (claims.hasNext()) {
            JSONObject object = (JSONObject) claims.next();
            userLogin = object.values().toArray()[2].toString();
        }
        return userLogin;
    }

    public String getUserPassword() {
        Iterator claims = user.values().iterator();
        String password = null;
        while (claims.hasNext()) {
            JSONObject object = (JSONObject) claims.next();
            password = object.values().toArray()[2].toString();
        }
        return password;
    }
}

