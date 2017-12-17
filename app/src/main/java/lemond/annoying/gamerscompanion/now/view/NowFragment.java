package lemond.annoying.gamerscompanion.now.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentNowBinding;
import lemond.annoying.gamerscompanion.game.objects.Game;
import lemond.annoying.gamerscompanion.now.viewmodel.NowViewModel;


public class NowFragment extends Fragment {

    private FragmentNowBinding binding;
    private NowViewModel viewModel;

    public NowFragment() {}

    public static NowFragment newInstance() {
        return new NowFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NowViewModel.class);
        viewModel.getMostPopularGames().observe(this, games -> {
            // TODO: 2017-12-07 add to recyclerview/adapter
            for (Game game: games) {
                Log.d("game", "game name: " + game.name);
            }
        });
//        viewModel.getRecentlyReleasedGames().observe(this, games -> {
//            // TODO: 2017-12-07 add to recyclerview/adapter
//        });
//        viewModel.getComingSoonGames().observe(this, games -> {
//            // TODO: 2017-12-07 add to recyclerview/adapter
//        });
        viewModel.getMostPopular();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now, container, false);
        return binding.getRoot();
    }

}
