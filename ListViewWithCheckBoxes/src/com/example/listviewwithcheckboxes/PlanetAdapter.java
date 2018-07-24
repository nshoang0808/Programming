package com.example.listviewwithcheckboxes;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

class Planet {

	String name;
	int distance;
	boolean selected = false;

	public Planet(String name, int distance) {
		super();
		this.name = name;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

public class PlanetAdapter extends ArrayAdapter<Planet>{

	private List<Planet> planetList;
	private Context context;

	public PlanetAdapter(List<Planet> planetList, Context context) {
		super(context, R.layout.single_listview_item, planetList);
		this.planetList = planetList;
		this.context = context;
	}

	private static class PlanetHolder {
		public TextView planetName;
		public TextView distView;
		public CheckBox chkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		PlanetHolder holder = new PlanetHolder();

		if(convertView == null) {

			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.single_listview_item, null);

			holder.planetName = (TextView) v.findViewById(R.id.name);
			holder.distView = (TextView) v.findViewById(R.id.dist);
			holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

			holder.chkBox.setOnCheckedChangeListener((MainActivity) context);

		} else {
			holder = (PlanetHolder) v.getTag();
		}

		Planet p = planetList.get(position);
		holder.planetName.setText(p.getName());
		holder.distView.setText("" + p.getDistance());
		holder.chkBox.setChecked(p.isSelected());
		holder.chkBox.setTag(p);

		return v;
	}
}
