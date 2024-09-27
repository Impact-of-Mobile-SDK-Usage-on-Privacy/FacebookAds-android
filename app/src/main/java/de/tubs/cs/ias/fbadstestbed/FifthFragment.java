package de.tubs.cs.ias.fbadstestbed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.facebook.ads.*;

import de.tubs.cs.ias.fbadstestbed.databinding.FifthStateBinding;

public class FifthFragment extends Fragment {

    private FifthStateBinding binding;

    private String placementId = "PLACEMENT_ID_PLACEHOLDER";
    private boolean test = false;
    private AdView adView = null;
    private CharSequence text = "Fifth State: Basic Func";
    private boolean EMULATOR = false;

    public String getPlacementId() {
        if(this.test) {
            //this is specific four our device - needs to change on different device
            AdSettings.setTestMode(true);
            if(EMULATOR) {
                AdSettings.addTestDevice("DEVICE_TEST_ID_EMULATOR");
            } else {
                AdSettings.addTestDevice("DEVICE_TEST_ID_DEVICE");
            }
            return "IMG_16_9_APP_INSTALL#" + this.placementId;
        } else {
            return this.placementId;
        }
    }

    AdListener adListener = new AdListener() {
        @Override
        public void onError(Ad ad, AdError adError) {
            binding.textviewFifth.setText(text + " [ERROR " + adError.getErrorCode() +  "/" + adError.getErrorMessage() + "]");
            Log.e("custom",adError.getErrorMessage());
        }

        @Override
        public void onAdLoaded(Ad ad) {
            binding.textviewFifth.setText(text + " [LOADED]");
        }

        @Override
        public void onAdClicked(Ad ad) {

        }

        @Override
        public void onLoggingImpression(Ad ad) {

        }
    };


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FifthStateBinding.inflate(inflater, container, false);

        binding.buttonShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getPlacementId();
                Log.i("placement id",id);
                adView = new AdView(FifthFragment.this.getActivity(),id,AdSize.BANNER_HEIGHT_50);
                // Find the Ad Container
                LinearLayout adContainer = (LinearLayout) FifthFragment.this.getActivity().findViewById(R.id.banner_container);
                // Add the ad view to your activity layout
                adContainer.addView(adView);
                // Request an ad
                adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(adView != null) {
            adView.destroy();
        }
        binding = null;
    }

}
