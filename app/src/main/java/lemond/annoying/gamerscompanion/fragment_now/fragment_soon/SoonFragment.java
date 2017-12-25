package lemond.annoying.gamerscompanion.fragment_now.fragment_soon;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentSoonBinding;


public class SoonFragment extends Fragment {

    private FragmentSoonBinding binding;

    public SoonFragment() {}

    public static SoonFragment newInstance() {
        return new SoonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_soon, container, false);
        return binding.getRoot();
    }

}
