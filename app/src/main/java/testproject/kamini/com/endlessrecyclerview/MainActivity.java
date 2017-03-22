package testproject.kamini.com.endlessrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    public static String api_key="1aecea2469b91f54451b94b55f174fb42b994b9323e2f7bded146dec2c99fda2";
    private static final String per_page = "10";
    private ArrayList<ImageItem> imageItemslist=new ArrayList<>();
    public static String order_By="latest";
    ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* RecyclerView rvItems = (RecyclerView) findViewById(R.id.rvContacts);
        // final List<Contact> allContacts = Contact.createContactsList(10, 0);

        loadDataUsingVolley(1,order_By);
        // final ContactsAdapter adapter = new ContactsAdapter(allContacts);
        imageAdapter = new ImageAdapter(imageItemslist,this);
        rvItems.setAdapter(imageAdapter);
        //  final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        final int startPosition = imageItemslist.size();
        rvItems.setLayoutManager(linearLayoutManager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
               // imageItemslist=new ArrayList<>();
                loadDataUsingVolley(page,  order_By);
               // List<Contact> moreContacts = Contact.createContactsList(10, page);
              // final int curSize = imageAdapter.getItemCount();
               // imageAdapter.notifyDataSetChanged();
              //  imageAdapter.notifyItemRangeInserted(startPosition, imageItemslist.size());
               // allContacts.addAll(moreContacts);

               *//* view.post(new Runnable() {
                    @Override
                    public void run() {
                        imageAdapter.notifyItemRangeInserted(curSize,imageItemslist.size());
                    }
                });*//*
            }
        };

        rvItems.addOnScrollListener(scrollListener);*/



    }

    void loadDataUsingVolley(int page, String order_by) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://api.unsplash.com/photos/?page=" + page + "&client_id=" + api_key + "&per_page=" + per_page + "&order_by=" + order_by;
        Log.d(TAG, URL);
        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                int len = array.length();
                final int startPosition;





                for (int i = 0; i < len; i++) {
                    try {
                        JSONObject object = array.getJSONObject(i);
                        String id = object.getString("id");
                        JSONObject object1 = object.getJSONObject("urls");
                        String imageURL = object1.getString("regular");
                        JSONObject object2 = object.getJSONObject("links");
                        String downloadURL = object2.getString("download");
                        imageItemslist.add(new ImageItem(imageURL, downloadURL, id));
                        Log.d(TAG, downloadURL);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /*if (dialog != null) {
                    dialog.dismiss();
                }*/
                imageAdapter.notifyDataSetChanged();
                Log.d(TAG, imageItemslist.size() + "imageitem size");




            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  dialog.dismiss();
               // Toast.makeText(get, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(objectRequest);
    }
}