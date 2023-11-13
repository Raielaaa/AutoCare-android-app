package com.example.autocare.main.cart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.autocare.R;
import com.example.autocare.Util;
import com.example.autocare.databinding.FragmentCartBinding;
import com.example.autocare.main.home.HomeListAdapter;
import com.example.autocare.main.home.HomeListModel;

public class CartFragment extends Fragment implements CartAdapter.ItemClickListener {

    private CartViewModel cartViewModel;
    private FragmentCartBinding binding;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        initClickableViews();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView() {
        binding.rvMainCart.setAdapter(
                new CartAdapter(
                        Util.collections,
                        requireActivity(),
                        this
                )
        );
    }

    private void initClickableViews() {
        binding.ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_cartFragment_to_mainFragment);
            }
        });

        binding.ivBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_cartFragment_to_mainFragment);
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        HomeListModel clickedItem = Util.collections.get(position);

        if (view.getId() == R.id.cvDeleteCart) {
            Util.collections.remove(position);
            binding.rvMainCart.getAdapter().notifyItemRemoved(position);
            binding.rvMainCart.getAdapter().notifyItemRangeChanged(position, Util.getInstance().collectionList.size());

            if (Util.collections.isEmpty()) {
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }

            Toast.makeText(requireActivity(), "Item successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }
}