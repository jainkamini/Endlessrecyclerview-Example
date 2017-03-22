package testproject.kamini.com.endlessrecyclerview;

import java.io.Serializable;

/**
 * Created by Kamini on 3/21/2017.
 */

public class ImageItem implements Serializable {

    public String imageURL;
    public String downloadURL;
    public String name;

    public ImageItem(String imageURL, String downloadURL, String id) {
        this.imageURL = imageURL;
        this.downloadURL = downloadURL;
        name = id;
    }
}