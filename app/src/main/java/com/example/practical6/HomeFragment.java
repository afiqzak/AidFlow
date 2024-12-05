package com.example.practical6;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // Parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    private RadioGroup toggleGroup; //radiogroup ni yg toggle pending ke done project

    //ni method tekan2 butang semua
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // gi report fragment
        Button btnReport = view.findViewById(R.id.reportbutton);
        btnReport.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.DestReport)
        );

        // ni semua yg pending or done tu
        toggleGroup = view.findViewById(R.id.toggle);

        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.pending_projects) {
                // tukar ke pending
                switchFragment(new RecycleViewPending());
            } else if (checkedId == R.id.done_projects) {
                // tukar ke done fragment
                switchFragment(new RecycleViewDone());
            }
        });

        // default klau tak pilih pape agi pending
        switchFragment(new RecycleViewPending());
    }

    // ni fragment manager
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }
}
