package ro.marven.globalmarketpulse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ro.marven.globalmarketpulse.databinding.ActivityMainBinding;
import ro.marven.globalmarketpulse.ui.terms_conditions.TermsConditionsDialogFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtinem preferintele pentru Termeni si Conditii
        SharedPreferences preferences = getSharedPreferences("app_prefs", 0);
        boolean termsAccepted = preferences.getBoolean("terms_accepted", false);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_market_trends, R.id.navigation_exchange_rates, R.id.navigation_business_news)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (!termsAccepted) {
            TermsConditionsDialogFragment terms=new TermsConditionsDialogFragment();
            navView.setVisibility(BottomNavigationView.GONE);
            terms.show(getSupportFragmentManager());
        }
        navView.setVisibility(BottomNavigationView.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // Handle Settings action
            return true;
        } else if (id == R.id.action_exit) {
            // Handle Exit action
            finishAndRemoveTask();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}