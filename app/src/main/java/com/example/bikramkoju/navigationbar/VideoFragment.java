package com.example.bikramkoju.navigationbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 3/29/2017.
 */

public class VideoFragment extends Fragment {
    RecyclerView recyclerView;
    String url="https://www.googleapis.com/youtube/v3/search?order=date&part=snippet&channelid=UCeVF1GpfFR8JwuhQHzTa5dQ%20&maxResults=50&key=AIzaSyBB9y9L5_2G5edLz9FGV3i2OKLB3daqelE";
    ArrayList<Module> mydata = new ArrayList<>();
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.videofragment_layout,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        requestQueue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               try {
                   JSONObject firstobj = new JSONObject(response);
                   JSONArray itemArray = firstobj.getJSONArray("items");
                   for (int i = 0; i < itemArray.length(); i++) {
                       JSONObject secondobj = itemArray.getJSONObject(i);
                       JSONObject id = secondobj.getJSONObject("id");
                       String videoId = id.getString("videoId");

                       JSONObject snippet = secondobj.getJSONObject("snippet");
                       String date = snippet.getString("publishedAt");
                       String title = snippet.getString("title");
                       JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                       JSONObject medium = thumbnails.getJSONObject("medium");
                       String thumburl = medium.getString("url");

                       Module m = new Module();
                       m.videoid = videoId;
                       m.date = date;
                       m.title = title;
                       m.url = thumburl;
                       mydata.add(m);
                   }
                   displayResult();
                   
               }catch (Exception e){
                   Toast.makeText(getActivity(), "could not fetch the data", Toast.LENGTH_SHORT).show();
               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest);

    }

    private void displayResult() {
        recyclerView.setAdapter(new MyCustomAdapter(getActivity(),mydata));
    }
}
