package com.example.autocare.account.login;

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

public class AccountLoginViewModel extends ViewModel {
    private final String TAG = "MyTag";

    public void validateUser(
            EditText etUsernameLogin,
            EditText etPasswordLogin,
            View view,
            Context context
    ) {
        try {
            final String username = etUsernameLogin.getText().toString();
            final String password = etPasswordLogin.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                Util.getAuthInstance.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    displayToastMessage("Login successful", context);

                                    Navigation.findNavController(view).navigate(R.id.action_accountLoginFragment_to_mainFragment);
                                    Util.dismissDialog();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                displayToastMessage("Error: " + e.getLocalizedMessage(), context);
                                Util.dismissDialog();
                            }
                        });
            } else {
                displayToastMessage("All fields are required", context);
                Util.dismissDialog();
            }
        } catch (Exception exception) {
            displayToastMessage("Error: " + exception.getLocalizedMessage(), context);
            Util.dismissDialog();
            Log.e(TAG, "validateUser: " + exception.getMessage());
        } finally {
            clearEditTextEntries(etUsernameLogin, etPasswordLogin);
        }
    }

    private void displayToastMessage(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void clearEditTextEntries(EditText etUsername, EditText etPassword) {
        etUsername.setText("");
        etPassword.setText("");
    }
}