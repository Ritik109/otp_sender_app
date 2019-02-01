package com.my.OtpSender;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        setHasOptionsMenu(true);
        final ListView listView = view.findViewById(R.id.lvMain);
        final ArrayList<String> formList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, formList);
        listView.setAdapter(adapter);
        try {
            JSONObject obj = new JSONObject(new JsonHandler(getActivity()).loadJSONFromAsset("contacts.json"));
            JSONArray m_jArry = obj.getJSONArray("contact_info");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //Log.d("Details-->", jo_inside.getString("fullname"));
                String formula_value = jo_inside.getString("firstname") + " " + jo_inside.getString("lastname");
                formList.add(formula_value);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mn1 = (MainActivity) getActivity();
                mn1.loadContactDetails(position);

            }
        });
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_contacts, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_chat) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }

}