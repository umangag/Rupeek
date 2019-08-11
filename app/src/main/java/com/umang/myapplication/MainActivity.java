package com.umang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.TextView;

import com.umang.myapplication.Database.AppDatabase;
import com.umang.myapplication.Database.DataAccess;
import com.umang.myapplication.NetworkClasses.ApiManager;
import com.umang.myapplication.NetworkClasses.ApiResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ApiResponse, SearchResultsAdapter.CardClickListener {


    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    ApiManager apiManager;
    private SearchResultsAdapter searchResultsAdapter;

    ModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchResultsAdapter = new SearchResultsAdapter(this);
        LinearLayoutManager linearVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvItems.setLayoutManager(linearVertical);
        rvItems.setAdapter(searchResultsAdapter);

        if (isNetworkAvailable(this)) {
            System.out.println("MainActivity.onCreate:::  Network Available");
            apiManager = new ApiManager(this);
            apiManager.getData();
        } else {
            System.out.println("MainActivity.onCreate:::  Network Not Available");
            useOfflineData();
        }
    }

    private void useOfflineData() {
        DataAccess.getItemsData(new DataAccess.DataAccessWithResultListener<ModelClass>() {
            @Override
            public void onSuccess(ModelClass result) {
                System.out.println("DATAAAA::: " + result.locations.size());
                updateUI(result);
            }
        });

    }

    @Override
    public void apiCallback(ModelClass modelClass) {
        System.out.println("MainActivity.apiCallback:::: " + modelClass.cust_name);
        updateUI(modelClass);
    }

    private void updateUI(ModelClass modelClass) {

        if (modelClass == null) {
            return;
        }

        this.modelClass = modelClass;

        txtName.setText("Hello "+modelClass.cust_name);

        System.out.println("MainActivity.updateUI:::  " + modelClass.locations.size());

        searchResultsAdapter.updateData(modelClass.locations);
    }

    @Override
    public void onCardClicked(int position) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("details", modelClass.locations.get(position));
        startActivity(intent);
    }
}
