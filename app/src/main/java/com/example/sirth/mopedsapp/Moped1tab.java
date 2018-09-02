package com.example.sirth.mopedsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sirth.mopedsapp.model.Item;
import com.example.sirth.mopedsapp.ui.Moped1RecyclerAdapter;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

import static com.example.sirth.mopedsapp.Constants.REALM_BASE_URL;

public class Moped1tab extends Fragment {

    private Realm realm;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private Moped1RecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.content_items, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<Item> items = setUpRealm();


        mAdapter = new Moped1RecyclerAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private RealmResults<Item> setUpRealm() {
        SyncConfiguration configuration = SyncUser.current()
                .createConfiguration(REALM_BASE_URL + "/default")
                .build();
        realm = Realm.getInstance(configuration);

        return realm
                .where(Item.class)
                .sort("itemId", Sort.DESCENDING)
                .findAllAsync();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
