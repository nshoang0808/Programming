package com.example.listviewwithcheckboxes;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		android.widget.CompoundButton.OnCheckedChangeListener {

	ListView lv;
	ArrayList<Planet> planetList;
	PlanetAdapter plAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		lv = (ListView) findViewById(R.id.listview);
		displayPlanetList();
	}

	private void displayPlanetList() {

		planetList = new ArrayList<Planet>();
		planetList.add(new Planet("Mercury", 57000000));
		planetList.add(new Planet("Venus", 23700000));
		planetList.add(new Planet("Mars", 35000000));
		planetList.add(new Planet("Jupiter", 5000000));
		planetList.add(new Planet("Saturn", 746000000));

		plAdapter = new PlanetAdapter(planetList, this);
		lv.setAdapter(plAdapter);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		int pos = lv.getPositionForView(buttonView);
		if (pos != ListView.INVALID_POSITION) {
			Planet p = planetList.get(pos);
			p.setSelected(isChecked);

			Toast.makeText(
					this,
					"Clicked on Planet: " + p.getName() + ". State: is "
							+ isChecked, Toast.LENGTH_SHORT).show();
		}
	}
}
