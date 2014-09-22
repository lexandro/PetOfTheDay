package zooplus.potd.domain;

import lombok.Data;


@Data
public class ImageURL implements Comparable {

    private int id;
    private String url;

    public ImageURL(int id, String url) {
        this.id = id;
        this.url = url;
    }


    @Override
    public int compareTo(Object another) {
        if (another instanceof ImageURL) {
            ImageURL anotherUrl = (ImageURL) another;
            if (this.id > anotherUrl.getId()) {
                return 1;
            } else if (this.id < anotherUrl.getId()) {
                return -1;
            } else {
                return 0;
            }
        } else {
            throw new IllegalArgumentException("Can't compare: " + another);
        }
    }
}
