package de.tubs.cs.ias.fbadstestbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.facebook.ads.AudienceNetworkAds;

import de.tubs.cs.ias.fbadstestbed.databinding.FourthStateBinding;

public class FourthFragment extends Fragment implements AudienceNetworkAds.InitListener {

    private FourthStateBinding binding;
    private CharSequence text = "Fourth State: Init";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FourthStateBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_FourthFragment_to_FifthFragment);
            }
        });

        binding.buttonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudienceNetworkAds.
                        buildInitSettings(FourthFragment.this.getActivity().getApplicationContext()).
                        withInitListener(FourthFragment.this).
                        initialize();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onInitialized(AudienceNetworkAds.InitResult initResult) {
        binding.textviewFourth.setText(text + " [" + initResult.getMessage() + "]");
    }
}