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
import com.example.mushafconsolidated.model.CorpusAyahWord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuranTopicSearchActivity extends BaseActivity implements OnItemClickListenerOnLong {
    private static final int LAUNCH_SECOND_ACTIVITY = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    // url to fetch contacts json
    private static final String URL = "https://api.androidhive.info/json/contacts.json";
    FloatingActionButton btnSelection;
    private RecyclerView recyclerView;
    private ArrayList<CorpusAyahWord> corpusayahWordArrayList;
    private QuranTopicSearchAdapter searchDownloadAdapter;
    private SearchView searchView;
    private View readytodownload, downloadedtranslation, backbutton;
    private ArrayList<quranexplorer> qurandictionaryArrayList;
    //  private DownloadSearchAdapter.ContactsAdapterListener contactsAdapterListener;
    private View translationDownloaded;
    private View translationReadytoDownload;
    private View view2, view1, view3;

    public QuranTopicSearchActivity() {
        super();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                if (result != null) {
                    final String[] split = result.split(",");
                    //       File file=new File(split[0]);
                    //    new InsertingTranslationFetch(TranslationActivitySearch.this).execute(split[0], split[1]);
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
        qurandictionaryArrayList = (ArrayList<quranexplorer>) utils.getTopicSearchAll();
        recyclerView = findViewById(R.id.recycler_view);
        searchDownloadAdapter = new QuranTopicSearchAdapter(QuranTopicSearchActivity.this, qurandictionaryArrayList, false);
        whiteNotificationBar(recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //   recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(searchDownloadAdapter);
        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                String titles = "";
                List<quranexplorer> stList = ((QuranTopicSearchAdapter) searchDownloadAdapter).getList();
                HashMap<String, String> datas = new HashMap<>();
                for (int i = 0; i < stList.size(); i++) {
                    quranexplorer selectedlist = stList.get(i);
                    if (selectedlist.isSelected()) {
                        datas.put(selectedlist.getTitle(), selectedlist.getAyahref());
                        data = data + selectedlist.getAyahref() + ",";
                        titles = titles + selectedlist.getTitle() + ",";

                    }

                }
                //  extracted(data, titles);
                Bundle dataBundle = new Bundle();
                if (!data.contains("null")) {
                    if (datas.size() > 0) {
                        dataBundle.putSerializable("map", datas);
                        Intent intents = new Intent(QuranTopicSearchActivity.this, TopicDetailAct.class);
                        intents.putExtras(dataBundle);
                        startActivity(intents);

                    }
                } else {
                    Toast.makeText(QuranTopicSearchActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void extracted(String data, String titles) {
        if (data.contains("null")) {
            String[] split = titles.split("-");
            Utils utils1 = new Utils(getApplicationContext());
            ArrayList<quranexplorer> topicSearch = (ArrayList<quranexplorer>) utils1.getTopicSearch(split[0]);
            data = "";
            for (quranexplorer search : topicSearch) {
                if (!search.getAyahref().contains("null") || !search.getAyahref().contains("ref")) {
                    data = data + search.getAyahref() + ",";
                }
            }

        }
        data = data.replaceAll("null", "");
        data = data.replaceAll(",,", ",");
        data = data.replaceAll(":,", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
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
        //noinspection SimplifiableIfStatement
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

  /*
    @Override
    public void onBackPressed() {
        finish();

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
   */

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "onItemCLick", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(int position, View v) {
    }

}
