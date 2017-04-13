package klsw.com.viewpager_imageview;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhy.magicviewpager.transformer.RotateYTransformer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private ImageView imageView_left,imageView_right;

    int[] imgRes = {R.mipmap.game0,R.mipmap.game1,R.mipmap.game2,R.mipmap.game3};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        imageView_left = (ImageView) findViewById(R.id.imageview_left);
        imageView_right = (ImageView) findViewById(R.id.imageview_right);

        //设置监听
        imageView_left.setOnClickListener(this);
        imageView_right.setOnClickListener(this);

        ViewPagerScroller viewPagerScroller = new ViewPagerScroller(this);
        viewPagerScroller.initViewPagerScroll(mViewPager);

        mViewPager.setPageMargin(5);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter = new PagerAdapter()
        {
            @Override
            public Object instantiateItem(ViewGroup container, int position)
            {
                ImageView view = new ImageView(MainActivity.this);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setImageResource(imgRes[position%imgRes.length]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object)
            {
                container.removeView((View) object);
            }

            @Override
            public int getCount()
            {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object o)
            {
                return view == o;
            }
        });
        mViewPager.setCurrentItem(240);
        mViewPager.setPageTransformer(true, new RotateYTransformer());

    }

    //imageview的监听方法
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imageview_left:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1,true);
    //            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
                break;


            case R.id.imageview_right:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                break;

            default:
                break;

        }
    }
}
