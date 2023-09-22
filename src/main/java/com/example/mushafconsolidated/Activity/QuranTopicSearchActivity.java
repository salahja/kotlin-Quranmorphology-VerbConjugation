package com.example.mushafconsolidated.Activity;


import static com.example.mushafconsolidated.R.drawable.custom_search_box;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushafconsolidated.Activityimport.BaseActivity;
import com.example.mushafconsolidated.Adapters.QuranTopicSearchAdapter;
import com.example.mushafconsolidated.Entities.quranexplorer;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuranTopicSearchActivity extends BaseActivity implements OnItemClickListenerOnLong {
    private static final int LAUNCH_SECOND_ACTIVITY = 1;
    FloatingActionButton btnSelection;
    private QuranTopicSearchAdapter searchDownloadAdapter;

    public QuranTopicSearchActivity() {
        super();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String result = null;
                if (data != null) {
                    result = data.getStringExtra("result");
                }
                if (result != null) {
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
  /*      SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        String isNightmode = shared.getString("theme", "dark");
        final int color = ContextCompat.getColor(this, R.color.color_background_overlay);
        final int colorsurface = ContextCompat.getColor(this, R.color.colorAccent);
        final int coloronbackground = ContextCompat.getColor(this, R.color.neutral0);
        if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
            toolbar.setBackgroundColor(coloronbackground);
            toolbar.setBackgroundColor(color);+
        } else {
            toolbar.setBackgroundColor(colorsurface);
        }*/
        Utils utils = new Utils(this);
        btnSelection = findViewById(R.id.btnShow);
        //     searchDownloadAdapter = new DownloadSearchAdapter(this,translationEntity -> {});
        ArrayList<quranexplorer> qurandictionaryArrayList = (ArrayList<quranexplorer>) utils.getTopicSearchAll();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        searchDownloadAdapter = new QuranTopicSearchAdapter(QuranTopicSearchActivity.this, qurandictionaryArrayList);
        whiteNotificationBar(recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //   recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(searchDownloadAdapter);
        btnSelection.setOnClickListener(v -> {
            StringBuilder data = new StringBuilder();
            StringBuilder titles = new StringBuilder();
            List<quranexplorer> stList = ((QuranTopicSearchAdapter) searchDownloadAdapter).getList();
            HashMap<String, String> datas = new HashMap<>();
            for (int i = 0; i < stList.size(); i++) {
                quranexplorer selectedlist = stList.get(i);
                if (selectedlist.isSelected()) {
                    datas.put(selectedlist.getTitle(), selectedlist.getAyahref());
                    data.append(selectedlist.getAyahref()).append(",");
                    titles.append(selectedlist.getTitle()).append(",");

                }

            }
            //  extracted(data, titles);
            Bundle dataBundle = new Bundle();
            if (!data.toString().contains("null")) {
                if (datas.size() > 0) {
                    dataBundle.putSerializable("map", datas);
                    Intent intents = new Intent(QuranTopicSearchActivity.this, TopicDetailAct.class);
                    intents.putExtras(dataBundle);
                    startActivity(intents);

                }
            } else {
                Toast.makeText(QuranTopicSearchActivity.this, "Not found", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
        }
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("Type something…");
        Drawable sear = ContextCompat.getDrawable(this, custom_search_box);
        searchView.setClipToOutline(true);
        searchView.setBackgroundDrawable(sear);
        searchView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                searchDownloadAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                searchDownloadAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.backButtonView) {
            Intent rintent = new Intent(QuranTopicSearchActivity.this, QuranGrammarAct.class);
            startActivity(rintent);
            finish();

        }
        Intent rintent = new Intent(QuranTopicSearchActivity.this, QuranGrammarAct.class);
        startActivity(rintent);
        finish();
        //return super.onOptionsItemSelected(item);
        return true;

    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onItemClick(@NonNull View v, int position) {
        Toast.makeText(this, "onItemCLick", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(int position, @NonNull View v) {
    }

}
