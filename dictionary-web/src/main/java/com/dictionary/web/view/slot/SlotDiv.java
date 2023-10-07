package com.dictionary.web.view.slot;

import com.dictionary.web.domain.Size;
import com.vaadin.flow.component.html.Div;

public class SlotDiv extends Div {
    public SlotDiv() {
        setWidth(Size.PX_300);
        setHeight(Size.PX_300);
        getStyle().set("border", "1px dashed gray");
    }
}
