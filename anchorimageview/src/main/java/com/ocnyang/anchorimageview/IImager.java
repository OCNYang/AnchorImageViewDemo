package com.ocnyang.anchorimageview;

import java.util.ArrayList;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time: 2019-10-28 14:45.
 *    *     *   *         *   * *       Email address: ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site: www.ocnyang.com
 *******************************************************************/

public interface IImager {
    String getUrl();
    float getScale();
    ArrayList<? extends IAnchor> getAnchorList();
}
