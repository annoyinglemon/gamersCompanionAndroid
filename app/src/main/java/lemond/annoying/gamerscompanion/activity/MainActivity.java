package lemond.annoying.gamerscompanion.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.ActivityMainBinding;
import lemond.annoying.gamerscompanion.app.GamersApplication;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private MainActivityComponent component;

    @Inject
    public MainFragmentPagerAdapter mainFragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .gamersAppComponent(GamersApplication.get(this).getGamersAppComponent())
                .build();

        component.injectMainActivity(this);

        binding.mainViewPager.setAdapter(mainFragmentPagerAdapter);

        binding.mainViewPager.setOnTouchListener((view, motionEvent) -> true);
        binding.mainNavigationBottom.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_news:
                binding.mainViewPager.setCurrentItem(1, false);
                break;
            case R.id.main_menu_search:
                binding.mainViewPager.setCurrentItem(2, false);
                break;
            default:
            case R.id.main_menu_now:
                binding.mainViewPager.setCurrentItem(0, false);
                break;
        }
        return true;
    }

    public MainActivityComponent getComponent() {
        return component;
    }
}
