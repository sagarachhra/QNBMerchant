package timesofmoney.qnbmerchant.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import timesofmoney.qnbmerchant.R;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsFirstFragment;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsSecondFragment;
import timesofmoney.qnbmerchant.fragments.RegistrationDetailsThirdFragment;

public class RegistrationFragmentAdapter extends FragmentPagerAdapter  {
    protected static final String[] CONTENT = new String[] { "This", "Is" };
    private int[] offerImages = {
            R.drawable.summer0ne1,
			R.drawable.summertwo1
	};

    private int mCount = CONTENT.length;

    public RegistrationFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0: return  new RegistrationDetailsFirstFragment().newInstance("First Fragment","");
            case 1: return  new RegistrationDetailsSecondFragment().newInstance("Second Fragment","");
            case 2: return  new RegistrationDetailsThirdFragment().newInstance("Third Fragment","");


            default: return  new RegistrationDetailsFirstFragment().newInstance("First Fragment","");


        }


        //return new TestFragment(offerImages[position]);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return RegistrationFragmentAdapter.CONTENT[position % CONTENT.length];
    }

   
    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}