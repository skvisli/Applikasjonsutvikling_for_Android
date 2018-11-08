package no.hist.itfag.books;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String KEY_PREF_BACKGROUND_COLOR = "pref_background_color";
    SharedPreferences sharedPref;
    String backgroundColorPref;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        backgroundColorPref = sharedPref.getString(SettingsActivity.KEY_PREF_BACKGROUND_COLOR, "");
        view = this.getWindow().getDecorView();
        setBackgroundColor(backgroundColorPref);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_BACKGROUND_COLOR)) {
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            backgroundColorPref = sharedPreferences.getString(key, "");
            setBackgroundColor(backgroundColorPref);
        }
    }

    private void setBackgroundColor(String color) {
        if (color.equals("Blue")){
            view.setBackgroundColor(Color.parseColor("#2df9e9"));
        }
        if (color.equals("Green")) {
            view.setBackgroundColor(Color.parseColor("#6ff979"));
        }
        if (color.equals("Red")) {
            view.setBackgroundColor(Color.parseColor("#fb5d5d"));
        }
    }
}
