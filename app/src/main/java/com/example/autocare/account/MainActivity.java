package com.example.autocare.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.autocare.R;
import com.example.autocare.Util;
import com.example.autocare.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_account);
        NavController navController = navHostFragment.getNavController();

        //  Initialize singleton class
        try {
            Util.getInstance();
        } catch (Exception exception) {
            Toast.makeText(
                    this,
                    "An error occurred: " + exception.getLocalizedMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}