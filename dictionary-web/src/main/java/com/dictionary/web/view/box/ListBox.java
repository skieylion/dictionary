package com.dictionary.web.view.box;

import com.dictionary.web.domain.ListBoxItem;
import com.dictionary.web.domain.Size;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListBox extends VerticalLayout {
    {
        setWidth(Size.PERCENT_100);
        setMargin(false);
        setPadding(false);
    }

    private final int count;
    private final List<ListBoxItem> items;
    private final List<Consumer<ListBoxItem>> consumers;

    public ListBox(int count) {
        this.count = count;
        items = new ArrayList<>();
        consumers = new ArrayList<>();
    }

    public void addPlusListener(Consumer<ListBoxItem> listener) {
        consumers.add(listener);
    }

    public void addItem(ListBoxItem listBoxItem) {
        items.add(listBoxItem);
        consumers.forEach(consumer -> consumer.accept(listBoxItem));
    }

    public void refreshState() {
        switch (items.size()) {
            case 1:
                items.get(0).getCloseButton().setEnabled(false);
                break;
            case 2:
                items.get(0).getCloseButton().setEnabled(true);
                break;
        }
    }

    public void clickRemoveListener(ListBoxItem listBoxItem) {
        listBoxItem.getCloseButton().addClickListener(event -> {
            remove(listBoxItem.getLayout());
            items.remove(listBoxItem);
            items.get(items.size() - 1).getPlusButton().setEnabled(true);
            refreshState();
        });
    }

    public void clickAddListener(ListBoxItem listBoxItem, Runnable runnable) {
        listBoxItem.getPlusButton().addClickListener(event -> {
            if (items.size() < count) {
                listBoxItem.getPlusButton().setEnabled(false);
                runnable.run();
                refreshState();
            }
        });
    }

    public void clearItems() {
        if (items.size() > 0) {
            if (items.size() == 1) {
                remove(items.get(0).getLayout());
                items.remove(items.get(0));
            } else {
                clickRemoveListener(items.get(0));
                clearItems();
            }
        }
    }

    public List<ListBoxItem> getItems() {
        return items;
    }
}
