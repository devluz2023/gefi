package br.com.cptm.gefimobile.ui.adapters;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import br.com.cptm.gefimobile.ui.fragments.RevisaoFragment;
import br.com.cptm.gefimobile.ui.fragments.DevolucaoFragment;
import br.com.cptm.gefimobile.ui.fragments.SolicitacaoFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final int numOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    // tab titles


    private String[] tabTitles = new String[]{"Solicitacao", "Devolução", "Revisão"};

    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SolicitacaoFragment();
            case 1:
                return new DevolucaoFragment();
            case 2:
                return new RevisaoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}
