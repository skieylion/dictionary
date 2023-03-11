package com.example.application.view;

import com.example.application.domain.FragmentLayout;

import java.util.ArrayList;
import java.util.List;

public class LayoutList extends FullVerticalLayout {
    private final List<FragmentLayout> fragmentList = new ArrayList<>();
    private static final int MAX = 4;

    protected void putLayout(FragmentLayout fragmentLayout) {
        fragmentList.add(fragmentLayout);
    }

    protected void state() {
        switch (fragmentList.size()) {
            case 1:
                fragmentList.get(0).getCloseButton().setEnabled(false);
                break;
            case 2:
                fragmentList.get(0).getCloseButton().setEnabled(true);
                break;
        }
    }

    protected void clickRemove(FragmentLayout fragmentLayout) {
        fragmentLayout.getCloseButton().addClickListener(event -> {
            this.remove(fragmentLayout.getLayout());
            fragmentList.remove(fragmentLayout);
            fragmentList.get(fragmentList.size() - 1).getPlusButton().setEnabled(true);
            state();
        });
    }

    protected void clickAdd(FragmentLayout fragmentLayout, Runnable runnable) {
        fragmentLayout.getPlusButton().addClickListener(event -> {
            if (fragmentList.size() < MAX) {
                fragmentLayout.getPlusButton().setEnabled(false);
                runnable.run();
                state();
            }
        });
    }

    protected List<FragmentLayout> getFragmentList() {
        return fragmentList;
    }
}
