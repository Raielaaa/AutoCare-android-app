package com.example.autocare.account.login;

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
import com.example.autocare.databinding.FragmentAccountLoginBinding;

public class AccountLoginFragment extends Fragment {

    private AccountLoginViewModel loginViewModel;
    private FragmentAccountLoginBinding binding;

    public static AccountLoginFragment newInstance() {
        return new AccountLoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(AccountLoginViewModel.class);

        initClickableView();

        return binding.getRoot();
    }

    private void initClickableView() {
        binding.tvNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_accountLoginFragment_to_accountRegisterFragment);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.displayCustomDialog(
                        requireActivity(),
                        R.layout.loading_screen_dialog
                );

                loginViewModel.validateUser(
                        binding.etUsernameLogin,
                        binding.etPasswordLogin,
                        binding.getRoot(),
                        requireActivity()
                );
            }
        });
    }
}