package com.example.myapplication.Control;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebClient {
    private RequestQueue requestQueue; // QUEUE OF REQUESTS
    private static String IP_ADDR = "192.168.0.36";
    private static String SERVER_URL = "http:/" + IP_ADDR + ":3002/api"; // PATH URL TO SERVER

    //CONSTRUCTOR
    public WebClient(Context context){
        // CREATE NEW REQUEST FROM VOLLEY
        requestQueue = Volley.newRequestQueue(context);
    }

    //  PRIVATE HELPER TAKES MAP: KEY:VALUE  --> FORMATS INTO URL-ENCODED STRING
    private String formatParams(Map<String, String> params) {
        StringBuilder formattedParams = new StringBuilder();
        formattedParams.append("?");

        // CREATE STRING FOR RETURN RESULT RESPONSE
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formattedParams.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        formattedParams.deleteCharAt(formattedParams.length() - 1);

        // FORMATTED_PARAMS --> AS QUERY IN URL
        return formattedParams.toString();
    }


    //GET ALL
    public void getAllNotes() {

        // APPEND REQUEST TO URL
        String urlWithParams = SERVER_URL + "/getAllNotes";
//        Log.d("[GETTING ALL NOTES]", ">>GETTING ALL NOTES");

        //GET REQUEST
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWithParams,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("++++++++++++++++++++++[GET RESPONSE]", response);
                        parseNotes(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e("---------------------[GET ERROR]", error.toString());
                    }
                });

        requestQueue.add(stringRequest);
    }


    //SEND POST REQUEST TO SERVER
    public void write(HashMap<String, String > params){
        String postURL = SERVER_URL + "/addNewNote";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("[ANS ON TEST POST]", response.toString());
//                        parseResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("[ERROR ON TEST POST]", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    // JSON RESPONSE PARSING
    public void parseResponse(String jsonResponse){
        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONObject data = jsonObject.getJSONObject("data");
            boolean success = data.getBoolean("success");
            String message = data.getString("message");
            MainControl.makeToast(message);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // JSON RESPONSE PARSING
    public void parseNotes(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);

            JSONArray data = jsonObject.getJSONArray("data");
            boolean success = jsonObject.getBoolean("success");
            MainControl.clearNotes();
            if(!success) {
                MainControl.makeToast("SERVER ERROR");
                return;
            }
            for(int i = 0; i < data.length(); i++){
                JSONObject noteObj = (JSONObject) data.get(i);
                String _id   = noteObj.getString("_id");
                String title = noteObj.getString("title");
                String text  = noteObj.getString("text");
                MainControl.appendNote(_id, title, text);
            }

            MainControl.updateNotes();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //DELETE
    public void deleteNote(HashMap<String, String> params) {
        String postURL = SERVER_URL + "/deleteNote";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("[ANS ON TEST POST]", response.toString());
                        getAllNotes();
//                        parseResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("[ERROR ON TEST POST]", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
