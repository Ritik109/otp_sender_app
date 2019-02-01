package com.my.OtpSender;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SentMsgFragment extends Fragment {

    ArrayList<String> formList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_sent_msg, container, false);
        final ListView listView = view.findViewById(R.id.lvsentmsg);
        formList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, formList);
        listView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor=new MyDbHandler(getActivity()).getAllData();
                if (cursor.getCount()==0)
                {
                    formList.add("There is Nothing in History");
                }

                while (cursor.moveToNext())
                {
                    String formula_value= cursor.getString(0)+" \n"+cursor.getString(1)+" OTP:"+cursor.getString(2);
                    formList.add(formula_value);
                }

            }
        }).start();


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sent_msg, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_status) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }
}
