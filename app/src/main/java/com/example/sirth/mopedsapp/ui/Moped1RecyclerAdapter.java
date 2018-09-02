/*
 * Copyright 2018 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.sirth.mopedsapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import com.example.sirth.mopedsapp.R;
import com.example.sirth.mopedsapp.model.Item;

public class Moped1RecyclerAdapter extends RealmRecyclerViewAdapter<Item, Moped1RecyclerAdapter.MyViewHolder> {

    public Moped1RecyclerAdapter(OrderedRealmCollection<Item> data) {
        super(data, true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.moped1tab, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Item item = getItem(position);
        holder.setItem(item);
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        Item mItem;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.section_label);
        }

        void setItem(Item item){
            this.mItem = item;
            this.textView.setText(item.getURL());
        }

    }
}
