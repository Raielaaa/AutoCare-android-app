package com.example.autocare.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.autocare.R;
import com.example.autocare.Util;
import com.example.autocare.databinding.FragmentMainBinding;
import com.example.autocare.main.home.GridSpacingItemDecoration;
import com.example.autocare.main.home.HomeListAdapter;
import com.example.autocare.main.home.HomeListModel;

import java.util.ArrayList;

public class MainFragment extends Fragment implements HomeListAdapter.ItemClickListener {
    private HomeListAdapter homeListAdapter;
    private MainViewModel mainViewModel;
    private FragmentMainBinding binding;
    private RecyclerView recyclerView; // Declare the RecyclerView here

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initRecyclerView();
        initClickViews();
        initSearchFunction();

        return binding.getRoot();
    }

    private void initSearchFunction() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called when the text is changed.
                String searchText = charSequence.toString().toLowerCase().trim();

                if (searchText.isEmpty()) {
                    // If the search text is empty, display the full list
                    homeListAdapter.setCollection(Util.getInstance().collectionList);
                } else {
                    ArrayList<HomeListModel> filteredList = new ArrayList<>();
                    for (HomeListModel item : homeListAdapter.getCollection()) {
                        // Filter based on both product and code
                        if (item.getProduct().toLowerCase().contains(searchText) ||
                                item.getCode().toLowerCase().contains(searchText)) {
                            filteredList.add(item);
                        }
                    }

                    // Update the RecyclerView with the filtered list
                    homeListAdapter.setCollection(filteredList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initClickViews() {
        binding.ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_cartFragment);
            }
        });

        binding.ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_mainFragment_to_accountLoginFragment);
            }
        });
    }

    private void initRecyclerView() {
        homeListAdapter = new HomeListAdapter(
                Util.getInstance().collectionList,
                requireActivity(),
                this, // Set this as the addClickListener
                this  // Set this as the deleteClickListener
        );

        recyclerView = binding.rvFragmentMain; // Assign the RecyclerView here
        recyclerView.setAdapter(homeListAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));
    }

    @Override
    public void onItemClick(View view, int position) {
        HomeListModel clickedItem = Util.getInstance().collectionList.get(position);

        if (view.getId() == R.id.cvAdd) {
            Util.displayCustomDialog(
                    requireActivity(),
                    R.layout.confirm_action_dialog,
                    clickedItem,
                    recyclerView,
                    position
            );
        } else if (view.getId() == R.id.cvDelete) {
            Toast.makeText(requireActivity(), "Item successfully deleted", Toast.LENGTH_SHORT).show();
            // Handle cvDelete click
            // Do something when cvDelete is clicked
            Util.getInstance().collectionList.remove(position);
            recyclerView.getAdapter().notifyItemRemoved(position);
            recyclerView.getAdapter().notifyItemRangeChanged(position, Util.getInstance().collectionList.size());
        }
    }
}