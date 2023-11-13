package com.example.autocare;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autocare.main.home.HomeListModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    private static volatile Util instance;

    private Util() { }

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }

    public static FirebaseAuth getAuthInstance = FirebaseAuth.getInstance();

    private static Dialog loadingScreenDialog;

    public static void displayCustomDialog(
            Activity activity,
            Integer layoutDialog
    ) {
        try {
            loadingScreenDialog = new Dialog(activity);
            loadingScreenDialog.setContentView(layoutDialog);

            loadingScreenDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.loading_screen_dialog_background));
            loadingScreenDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            loadingScreenDialog.setCancelable(false);
            loadingScreenDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            loadingScreenDialog.show();
        } catch (Exception e) {
            Log.e("MyTag", "displayCustomDialog: " + e.getMessage());
            // Handle the exception, if necessary
        }
    }

    public static final ArrayList<HomeListModel> collections = new ArrayList<>();

    public static void displayCustomDialog(
            Activity activity,
            Integer layoutDialog,
            HomeListModel homeListModel,
            RecyclerView recyclerView,
            int position
    ) {
        try {
            loadingScreenDialog = new Dialog(activity);
            loadingScreenDialog.setContentView(layoutDialog);

            loadingScreenDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.loading_screen_dialog_background));
            loadingScreenDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            loadingScreenDialog.setCancelable(false);
            loadingScreenDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            loadingScreenDialog.show();
        } catch (Exception e) {
            Log.e("MyTag", "displayCustomDialog: " + e.getMessage());
            // Handle the exception, if necessary
        }

        try {
            loadingScreenDialog.findViewById(R.id.btnProceed).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "Add action confirmed", Toast.LENGTH_SHORT).show();
                    collections.add(homeListModel);

                    Util.getInstance().collectionList.remove(position);
                    recyclerView.getAdapter().notifyItemRemoved(position);
                    recyclerView.getAdapter().notifyItemRangeChanged(position, Util.getInstance().collectionList.size());
                    dismissDialog();
                }
            });

            loadingScreenDialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "Cancel", Toast.LENGTH_SHORT).show();
                    dismissDialog();
                }
            });
        } catch (Exception exception) {
            Toast.makeText(activity, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show();
            Log.e("MyTag", "displayCustomDialog: ${e.message}");
        }
    }
    public static void dismissDialog() {
        loadingScreenDialog.dismiss();
    }

    public ArrayList<HomeListModel> collectionList = new ArrayList<>(Arrays.asList(
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_1"),
                    "DFT Water pump AW4126",
                    "OMR 187"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_2"),
                    "Radiator fan motor asse",
                    "OMR 40"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_3"),
                    "AVON AW4108 Engine Pump",
                    "OMR 192"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_4"),
                    "AVON AW4128 Engine Wat",
                    "OMR 57"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_5"),
                    "Air intake filter",
                    "OMR 294"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_6"),
                    "Car Intercooler Radiator",
                    "OMR 843"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_7"),
                    "Car Sensor",
                    "OMR 14"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_8"),
                    "Car Starter",
                    "OMR 926"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_9"),
                    "Car suspension",
                    "OMR 473"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_10"),
                    "DFT Water pump AW4126",
                    "OMR 837"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_11"),
                    "Engine Turbocharger",
                    "OMR 172"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_12"),
                    "Ford Cortina Carburetor",
                    "OMR 273"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_13"),
                    "Injector Alternator",
                    "OMR 43"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_14"),
                    "Spark Plug",
                    "OMR 19"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_15"),
                    "Three assorted-color filters",
                    "OMR 172"
            ),
            new HomeListModel(
                    Uri.parse("android.resource://com.example.autocare/drawable/home_pic_16"),
                    "Car Suspension Automobile",
                    "OMR 37"
            )
    ));
}
