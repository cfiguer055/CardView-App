package com.example.cardviewapp;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.cardviewapp.databinding.ActivityScrollingBinding;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    private RecyclerView appsRecyclerView;
    private AppsAdapter appsAdapter;
    private ArrayList<AppsModel> appsModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Scrolling stuff
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Initialize RecyclerView and create new array list
        appsRecyclerView = findViewById(R.id.recycler_view);
        appsModelArrayList = new ArrayList<>();

        // Initialize Adapter class and pass arraylist
        AppsAdapter appsAdapter = new AppsAdapter(this, appsModelArrayList);

        // Set layout manager as a grid with 2 columns
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        // Setting layout manager and ItemDecoration to recycler view
        appsRecyclerView.setLayoutManager(layoutManager);
        appsRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        appsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Set Adapter for Recycler View
        appsRecyclerView.setAdapter(appsAdapter);

        // Preparing the cards:
        InsertDataIntoCards();
        appsAdapter.notifyDataSetChanged();

    }


    private void InsertDataIntoCards() {
        // Add the cards data and display them:
        int[] appsCovers = new int[]{
                R.drawable.masterpro,       // appsCover[0]
                R.drawable.loggo,
                R.drawable.masterandroid,
                R.drawable.youtube,
                R.drawable.satar
        };

        AppsModel a = new AppsModel("Master Android App", 800000, appsCovers[0]);
        appsModelArrayList.add(a);

        a = new AppsModel("Master Flutter App", 800, appsCovers[1]);
        appsModelArrayList.add(a);

        a = new AppsModel("Master Android App", 450, appsCovers[2]);
        appsModelArrayList.add(a);

        a = new AppsModel("Youtube Channel", 7500, appsCovers[3]);
        appsModelArrayList.add(a);

        a = new AppsModel("Rate Us", 2000, appsCovers[4]);
        appsModelArrayList.add(a);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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

            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if(position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if(position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}