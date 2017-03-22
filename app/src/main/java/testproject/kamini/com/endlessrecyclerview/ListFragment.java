package testproject.kamini.com.endlessrecyclerview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


public class ListFragment extends Fragment {

    public static String api_key="1aecea2469b91f54451b94b55f174fb42b994b9323e2f7bded146dec2c99fda2";
    private static final String per_page = "10";
    private ArrayList<ImageItem>  imageItemslist=new ArrayList<>();
    public static String order_By="latest";
    ImageAdapter imageAdapter;
    EndlessRecyclerViewScrollListener scrollListener;

   private GridLayoutManager linearLayoutManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView rvItems = (RecyclerView) view.findViewById(R.id.rvContacts);
       // final List<Contact> allContacts = Contact.createContactsList(10, 0);

        loadDataUsingVolley(1,order_By);
       // final ContactsAdapter adapter = new ContactsAdapter(allContacts);
        imageAdapter = new ImageAdapter(imageItemslist,getContext());
        rvItems.setAdapter(imageAdapter);
        //  final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        final int startPosition = imageItemslist.size();
        rvItems.setLayoutManager(linearLayoutManager);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // imageItemslist=new ArrayList<>();
                loadDataUsingVolley(page,  order_By);


        }
    };

        rvItems.addOnScrollListener(scrollListener);
        return view;
    }



    void loadDataUsingVolley(int page, String order_by) {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(objectRequest);
    }
}
