package track.Cycling;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import java.text.DecimalFormat;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TrackCyclingFreeActivity extends Activity {
	String plato = "1";
	String cog = "1";
	String lap = "250";
	String perim = "2.096";
	double velo = 0.0;
	double effcm = 0.0;
boolean metroak=true;
boolean millak=false;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/**
		 * EditText- wheel size a
		 */

		EditText tm = (EditText) findViewById(R.id.editText1);
		tm.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				EditText perime = (EditText) findViewById(R.id.editText1);
				if (perime.length() > 3 && !(keyCode == 16)) {
					if (perime.toString() != "0") {
						perim = perime.getText().toString();
						metroak();
					}
				}
				return false;
			}
		});
		/**
		 * EditText- lap size a
		 */

		EditText etl = (EditText) findViewById(R.id.editText2);
		etl.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				EditText laps = (EditText) findViewById(R.id.editText2);
				if (laps.length() > 2 && !(keyCode == 16)) {
					lap = laps.getText().toString();
					if (lap != "0") {
						metroak();
					}
				}
				return false;

			}
		});

		/**
		 * spinner
		 * 
		 */

		final Spinner s = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,
				R.array.ring, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);

		s.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {

				TextView resultText = (TextView) findViewById(R.id.SpinnerResult);
				setRing(s.getItemAtPosition(position).toString());
				resultText.setText(plato);
				metroak();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		/**
		 * SpinnerCog
		 * 
		 */
		final Spinner s2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter<?> adapter2 = ArrayAdapter.createFromResource(this,
				R.array.cog, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s2.setAdapter(adapter2);

		s2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {

				TextView resultText2 = (TextView) findViewById(R.id.SpinnerResult2);
				setCog(s2.getItemAtPosition(position).toString());
				resultText2.setText(cog);
				metroak();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "dev.").setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(2, 2, 1, "Date").setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(3, 3, 2, "meters").setIcon(android.R.drawable.ic_menu_day);
		menu.add(4, 4, 3, "miles").setIcon(android.R.drawable.ic_menu_day);
		return true;
	}
 
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
 
		case 1:
 
			Toast.makeText(this, "Developed by Unai Tolosa a.k.a @UtolotU", Toast.LENGTH_LONG).show();
 
			break;
	case 2:
		
		Toast.makeText(this, "v-1.0 -> 2012-02 \nv-2.0 -> 2012-08", Toast.LENGTH_LONG).show();

		break;
	case 3:
		if (metroak==true) {
			Toast.makeText(this, "already in meters", Toast.LENGTH_LONG).show();
		}else{
			metroak();
			millak=false;
			metroak=true;
			
		Toast.makeText(this, "meters", Toast.LENGTH_LONG).show();
		taulaberritu();
		}
		break;
		
	case 4:
		if (millak==true) {
				Toast.makeText(this, "already in miles", Toast.LENGTH_LONG).show();
		}else{
				millak();
			millak=true;
			metroak=false;
			Toast.makeText(this, "miles", Toast.LENGTH_LONG).show();
			taulaberritu();
		}
		break;
	}
 
		return super.onOptionsItemSelected(item);
	}

	/**
	 * tabla
	 * 
	 */

	public TableLayout taulain(int ertztam, int ilaraZenb, int zutabeZenb,
			String colorBorder) {
		TableLayout table = new TableLayout(this);
		DecimalFormat df1 = new DecimalFormat("0.0");
		DecimalFormat df3 = new DecimalFormat("0.000");
		if (ilaraZenb >= 1 && zutabeZenb >= 1) {
			TableRow ilara = new TableRow(this);

			int kutxaKop = ilaraZenb * zutabeZenb;

			/*
			 * zabalera= pantZabalera/zutabekop-
			 * bordeakpixeletan-ertzeko(padding) pixel kop
			 */
			int Zabalera = 0;
			getResources().getConfiguration();
			if (Configuration.ORIENTATION_LANDSCAPE != getResources()
					.getConfiguration().orientation) {
				Zabalera = (getWindowManager().getDefaultDisplay().getWidth() / (zutabeZenb))
						- ((ertztam * (zutabeZenb))-5);
			} else {
				Zabalera = ((getWindowManager().getDefaultDisplay().getWidth() - 190) / (zutabeZenb))
						- ((ertztam * (zutabeZenb)));
			}

			int contColum = 0;
			int contRow = 0;
			int kad = 55;
			for (int i = 0; i <= kutxaKop; i++) {

				if (contColum == zutabeZenb) {
					table.addView(ilara);
					ilara = new TableRow(this);
					contColum = 0;
					contRow++;

				}

				// taula ertzak
				RelativeLayout Ertz = new RelativeLayout(this);
				Ertz.setPadding(ertztam, ertztam, 0, 0);
				// baina azken zutabea bada
				if (contColum == zutabeZenb - 1) {
					Ertz.setPadding(ertztam, ertztam, 0, 0);
				}
				// azken ilara
				if (contRow == ilaraZenb - 1) {
					Ertz.setPadding(ertztam, ertztam, 0, ertztam);
					// gainera azken zutabe
					if (contColum == (zutabeZenb) - 1) {
						Ertz.setPadding(ertztam, ertztam, ertztam, ertztam);
					}
				}
				// ertz kolorea
				Ertz.setBackgroundColor(Color.parseColor(colorBorder));

				// testua
				TextView text = new TextView(this);
				switch (i) {
				case 0:
					text.setText("Cadence");
					break;
				case 1:
					if(metroak){
					text.setText("Km/h");}
					else{
					text.setText("mph");
					}
					break;
				case 2:
					text.setText("Lap time");
					break;
				case 3:
					if(metroak){
						text.setText("Km time");}
						else{
						text.setText("mile time");
						}
					break;
				default:

					if (contColum == 0) {
						kad = kad + 5;
						text.setText(String.valueOf(kad) + " rpm");
					}
					if (contColum == 1) {
						velo = 0;
						velo = (effcm * kad * 60) / 1000;
						if(millak){
							double velom=velo* 0.62137;
							text.setText(df3.format((velom)));
							}else{
						text.setText(df3.format((velo)));}
						
					}
					if (contColum == 2) {

						text.setText(df3.format(Double.parseDouble(lap) * 3.6
								/ (velo))
								+ " s");
					}
					if (contColum == 3) {
						
						if(millak){
							double velom=velo* 0.62137;
							double d = (1 * 3600 / (velom));
							int j = (int) (d / 60);
							text.setText(j
									+ "m "
									+ String.valueOf(df1.format((1 * 3600 / (velom))
											- ((double) (j) * 60))) + "s");
							}else{
								double d = (1 * 3600 / (velo));
								int j = (int) (d / 60);
						text.setText(j
								+ "m "
								+ String.valueOf(df1.format((1 * 3600 / (velo))
										- ((double) (j) * 60))) + "s");
										}
					}

				}

				text.setWidth(Zabalera);
				text.setGravity(Gravity.CLIP_HORIZONTAL);
				text.setPadding(2, 2, 2, 2);

				text.setBackgroundColor(Color.BLACK);
				// Ertzari txt ezarri
				Ertz.addView(text);

				// ilarari ertza
				ilara.addView(Ertz);
				contColum++;

			}
		} else {
			TextView errorea = new TextView(this);
			errorea.setText("table size must be more than");
			table.addView(errorea);
		}
		return table;
	}

	/**
	 * metodoak
	 * 
	 * @param str
	 */

	public void setRing(String str) {
		plato = str;
	}

	public void setCog(String str) {
		cog = str;
	}

	public void setPerim(String str) {
		perim = str;
	}

	public void metroak() {
		effcm = 0;
		effcm = (Double.parseDouble(perim)) * (Integer.parseInt(plato))
				/ (Integer.parseInt(cog));
		String eran = String.valueOf(effcm);
		TextView resultText2 = (TextView) findViewById(R.id.dmetro);
		resultText2.setText(eran);
		taulaberritu();

	}
	public void millak() {
		effcm = 0;
		effcm = (Double.parseDouble(perim)) * (Integer.parseInt(plato))
				/ (Integer.parseInt(cog));
		String eran = String.valueOf(effcm);
		TextView resultText2 = (TextView) findViewById(R.id.dmetro);
		resultText2.setText(eran);
		taulaberritu();

	}

	public void taulaberritu() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.myTableLayout);
		ll.removeAllViews();
		// metodo taulain (ertzaba, filak, zutabe, ertzkolore)
		TableLayout taula = taulain(2, 20, 4, "#0055AA");
		ll.addView(taula);

	}

}