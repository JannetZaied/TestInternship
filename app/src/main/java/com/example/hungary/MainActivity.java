package com.example.hungary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hungary.Adapter.MeetingAdapter;
import com.example.hungary.data.Meetings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public String URL = "http://91.229.93.100/api/app/article_types/5729fc387fdea7e267fa9761";
    public ArrayList<Meetings> meetings ;
    private MeetingAdapter ma ;
    static Meetings static_meeting ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        meetings = new ArrayList<>(); //init list of meetings

        ConsumeAPI();// put data in adapter then I will show it
        ma = new MeetingAdapter(this , meetings); //init the adapter
        //declare my new recyclerview type : linear
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1 , dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ma);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Meetings m = meetings.get(position);
                static_meeting = m ;

                Toast.makeText(MainActivity.this , "you clicked on this meeting" , Toast.LENGTH_LONG).show();
                if (!static_meeting.equals(null)){
                    Intent i = new Intent(MainActivity.this , DetailsActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }




    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void ConsumeAPI(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());
//
                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i); // 3amarhom tawa ki chnraniwh mch behc yemchy tatra
                        String name = jObj.getString("name");
                        String sub = jObj.getString("subtitle");
                        String tumbnail = jObj.getString("thumbnail");
                        String external = jObj.getString("external_link");
                        String id = jObj.getString("id");
                        String created = jObj.getString("created");
                        Meetings m = new Meetings(name , sub , tumbnail , external ) ;
                        m.setId(id);
                        m.setCreated(created);

                        meetings.add(m);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ma.listM = meetings;

                ma.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }



}
