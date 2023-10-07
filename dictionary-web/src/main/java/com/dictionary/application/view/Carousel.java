package com.dictionary.application.view;


import java.io.*;

public class Carousel implements Serializable {


    public Carousel(String height) {


//        HorizontalLayout hLayout = FullHorizontalLayout.createInstance();
//        hLayout.add(clipField);
//
//        wrapper.add(hLayout);
    }

//    public VerticalLayout getWrapper() {
//        return wrapper;
//    }

//    public MediaFile getMediaFile() {
//        return picture.getMediaFile();
//    }
//
//    public void setPicture(MediaFile file) {
//        picture.setSrc(new StreamResource(file.getFullName(), () -> new ByteArrayInputStream(file.getBytes())));
//        picture.setMediaFile(file);
//    }

}

/*
*     private void createSlider(Button prev, Button next, int count) {
        Slider slider = new Slider(count)
                .change((Slider.State state) -> {
                    prev.setEnabled(true);
                    next.setEnabled(true);
                    if (state == Slider.State.START) {
                        prev.setEnabled(false);
                    } else if (state == Slider.State.FINISH) {
                        next.setEnabled(false);
                    } else if (state == Slider.State.ONE) {
                        prev.setEnabled(false);
                        next.setEnabled(false);

                    }
                });
        slider.current(0);
        prev.addClickListener(event -> slider.prev());
        next.addClickListener(event -> slider.next());
    }
* */