package com.example.autocare.account.register;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.autocare.R;
import com.example.autocare.Util;
import com.example.autocare.databinding.FragmentAccountRegisterBinding;

import java.util.Objects;

public class AccountRegisterFragment extends Fragment {

    private AccountRegisterViewModel registerViewModel;
    private FragmentAccountRegisterBinding binding;

    public static AccountRegisterFragment newInstance() {
        return new AccountRegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountRegisterBinding.inflate(inflater, container, false);
        registerViewModel = new ViewModelProvider(this).get(AccountRegisterViewModel.class);

        initClickableViews();

        return binding.getRoot();
    }

    private void initClickableViews() {
        binding.tvExistingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountRegisterFragment_to_accountLoginFragment);
            }
        });

        binding.ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountRegisterFragment_to_accountLoginFragment);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.displayCustomDialog(
                        requireActivity(),
                        R.layout.loading_screen_dialog
                );

                registerViewModel.registerUserToAuth(
                        binding.etUsername,
                        binding.etPassword,
                        requireActivity(),
                        binding.getRoot()
                );
            }
        });
    }
}