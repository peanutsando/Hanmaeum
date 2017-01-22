package kr.ac.mju.hanmaeum.utils.subway;

import kr.ac.mju.hanmaeum.utils.Constants;

/**
 * Created by Youthink on 2017-01-22.
 */

public class SubwayImages {
    public static int getSubwayImages(String line) {
        for (int i = 0; i < Constants.SUBWAY_LINE_KEY.length; i++) {
            if (Constants.SUBWAY_LINE_KEY[i].equals(line)) {
                return Constants.SUBWAY_LIEN_IMAGE[i];
            }
        }
        return 0;
    }
}
