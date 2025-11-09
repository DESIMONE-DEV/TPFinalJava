package Interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface IJson {
    JSONObject toJSON()throws JSONException;
}
