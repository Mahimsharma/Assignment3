package com.example.nimnim.ui.main;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nimnim.Utils.ImageEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private static RequestHandler instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;
    private List<ImageEntity> imageList;
    private String currentQuery;
    private int currentPage;
    private  String per_page="20";

    private RequestHandler(Context context) {
        ctx = context;
        requestQueue = Volley.newRequestQueue(ctx);
        imageList=new ArrayList<>();
        }

    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestHandler(context);
        }
        return instance;
    }

    private void makeSearch(String tag,String num){



    }

    public List<ImageEntity> parseJson(String tag)
    {   if(tag.equals(currentQuery)) currentPage++;
        else{
            currentQuery=tag;
            currentPage = 1;
        }
        imageList.clear();
        String url = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=e44c5252a0f90974cff57177110fdc32&tags="+tag+"&per_page="+per_page+"&page="+currentPage+"&format=json&nojsoncallback=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    response = response.getJSONObject("photos");
                    int totalPages=response.getInt("pages");
                    if(currentPage<=totalPages){
                    JSONArray jsonArray = response.getJSONArray("photo");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String serverID = hit.getString("server");
                        String photoID = hit.getString("id");
                        String secret = hit.getString("secret");
                        String title = hit.getString("title");
                        String imgURL = "https://live.staticflickr.com/"+serverID+"/"+photoID+"_"+secret+"_w.jpg";
                        imageList.add(new ImageEntity(imgURL, title));
                    }
                    }
                    else{
                        return ;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        return imageList;
    }

}
