package com.example.autocare.account.register;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.autocare.R;
import com.example.autocare.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class AccountRegisterViewModel extends ViewModel {
    private final String TAG = "MyTag";

    public void registerUserToAuth(
            EditText etUsername,
            EditText etPassword,
            Context context,
            View view
    ) {
        final String inputtedUsername = etUsername.getText().toString();
        final String inputtedPassword = etPassword.getText().toString();

        if (!inputtedUsername.isEmpty() && !inputtedPassword.isEmpty()) {
            insertDataToAuth(
                    etUsername,
                    etPassword,
                    context,
                    view
            );
        } else {
            Util.dismissDialog();
            displayToastMessage("All fields are required", context);

            clearEditTextEntries(etUsername, etPassword);
        }
    }

    private void insertDataToAuth(
            EditText etUsername,
            EditText etPassword,
            Context context,
            View view
    ) {
        Util.getAuthInstance.createUserWithEmailAndPassword(etUsername.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        displayToastMessage("Account successfully registered", context);
                        clearEditTextEntries(etUsername, etPassword);
                        Navigation.findNavController(view).navigate(R.id.action_accountRegisterFragment_to_accountLoginFragment);

                        Util.dismissDialog();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayToastMessage("An error occurred: " + e.getLocalizedMessage(), context);
                        clearEditTextEntries(etUsername, etPassword);
                        Util.dismissDialog();

                        Log.e(TAG, "onFailure: " + e.getMessage());
                    }
                });
    }

    private void displayToastMessage(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void clearEditTextEntries(EditText etUsername, EditText etPassword) {
        etUsername.setText("");
        etPassword.setText("");
    }
}