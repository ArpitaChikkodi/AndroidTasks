package com.example.photogallery;

import java.util.ArrayList;

public class Images {
    private ArrayList<Integer> imageId;
    public Images() {
        imageId = new ArrayList<Integer>();
        imageId.add(R.drawable.img1);
        imageId.add(R.drawable.img2);
        imageId.add(R.drawable.img3);
        imageId.add(R.drawable.img4);
        imageId.add(R.drawable.img5);
    }
    public ArrayList<Integer> getImageItem() {
        return imageId;
    }
}
