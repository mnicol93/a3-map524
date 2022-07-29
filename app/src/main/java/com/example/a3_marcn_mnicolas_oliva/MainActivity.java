package com.example.a3_marcn_mnicolas_oliva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a3_marcn_mnicolas_oliva.adapters.ItemAdapter;
import com.example.a3_marcn_mnicolas_oliva.databinding.ActivityMainBinding;
import com.example.a3_marcn_mnicolas_oliva.models.ItemResponse;
import com.example.a3_marcn_mnicolas_oliva.models.Zelda;
import com.example.a3_marcn_mnicolas_oliva.network.API;
import com.example.a3_marcn_mnicolas_oliva.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG="ASSINGMENT3";
    private ActivityMainBinding binding;

    private ArrayList<Zelda> zeldaItemsList = new ArrayList<>();
    private ArrayList<Zelda> newSearch = new ArrayList<>();
    private ItemAdapter adapter;

    private List<String> listLocations;

    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // create an adapter for the RV (the RV belongs to a class of UI elements known as AdapterViews
        // and they cannot show their own data directly, so they need the help of a class Adapter to do so).
        adapter = new ItemAdapter(this, newSearch);
        // configure the RV to use the adapter

        // - Perform additional configuration on the RV
        binding.rvItems.setLayoutManager(new LinearLayoutManager(this));
        binding.rvItems.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        // - Attach the adapter to the RV
        binding.rvItems.setAdapter(adapter);

        // When the app loads, get a reference to the API via RetrofitClient Singleton
        this.api = RetrofitClient.getInstance().getApi();
        // Using the API, connect to the endpoint and retrieve the data, create a network request
        Call<ItemResponse> request = this.api.getAllItems();

        // Execute the network request (sync or async), do it async
        request.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                Log.d(TAG, "The request was succesful");
                Log.d(TAG, "Response code: " + response.code());

                if(response.isSuccessful() == false){
                    Log.d(TAG, "Error, Response code: " + response.code());
                    return;
                }
                // retrieve the contents of the response
                ItemResponse receivedList = response.body();
                List<Zelda> itemsFromAPI = receivedList.getData();

                zeldaItemsList.clear();
                zeldaItemsList.addAll(itemsFromAPI);

                adapter.notifyDataSetChanged();

                // Create list of all locations
//                for(int i = 0; i < zeldaItemsList.size(); i++){
//                    //populate list with data
//                    if(listLocations.isEmpty()){
//                        for (String location: zeldaItemsList.get(0).getCommon_locations()) {
//                            listLocations.add(location);
//                        }
//                    }
//                    else {
//                        for (String loc : locations) {
//                            for (String location: zeldaItemsList.get(i).getCommon_locations()) {
//                                if(!loc.equals(location)){
//                                    locations.add(location);
//                                }
//                            }
//                        }
//                    }
                }
//                for(String loc : listLocations) {
//                    Log.d("XULO", loc);
//                }
           // }


            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                // No internet, server down
                Log.d(TAG, "The request failed");
                Log.d(TAG, t.getMessage());

            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = binding.etKeyword.getText().toString();

                newSearch.clear();

                if(!itemName.isEmpty()) {
                    // Locate the input in the item name
                    for (int i = 0; i < zeldaItemsList.size(); i++) {
                        if (zeldaItemsList.get(i).getName().toLowerCase()
                                .contains(itemName.toLowerCase())) {
                            // Populate RecyclerView
                            newSearch.add(zeldaItemsList.get(i));

                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}