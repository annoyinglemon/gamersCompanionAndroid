package lemond.annoying.gamerscompanion.now.hyped;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentHypedBinding;

public class HypedFragment extends Fragment {

    private FragmentHypedBinding binding;

    public HypedFragment() {}

    public static HypedFragment newInstance() {
        return new HypedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hyped, container, false);
        return binding.getRoot();
    }

}
