package lemond.annoying.gamerscompanion.now.released;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentReleasedBinding;


public class ReleasedFragment extends Fragment {

    private FragmentReleasedBinding binding;

    public ReleasedFragment() {}

    public static ReleasedFragment newInstance() {
        return new ReleasedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_released, container, false);
        return binding.getRoot();
    }

}
