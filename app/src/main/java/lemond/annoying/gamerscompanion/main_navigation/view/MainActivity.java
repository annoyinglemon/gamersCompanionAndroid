package lemond.annoying.gamerscompanion.main_navigation.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        binding.mainViewPager.setAdapter(mainFragmentPagerAdapter);
        binding.mainNavigationBottom.setOnNavigationItemSelectedListener(this);
        binding.mainViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_news:
                binding.mainViewPager.setCurrentItem(1);
                break;
            case R.id.main_menu_search:
                binding.mainViewPager.setCurrentItem(2);
                break;
            default:
            case R.id.main_menu_now:
                binding.mainViewPager.setCurrentItem(0);
                break;
        }
        return true;
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                binding.mainNavigationBottom.setSelectedItemId(R.id.main_menu_now);
                break;
            case 1:
                binding.mainNavigationBottom.setSelectedItemId(R.id.main_menu_news);
                break;
            case 2:
                binding.mainNavigationBottom.setSelectedItemId(R.id.main_menu_search);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
