package com.ocnyang.anchorimageview;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time: 2019-10-28 14:42.
 *    *     *   *         *   * *       Email address: ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site: www.ocnyang.com
 *******************************************************************/

public interface IAnchor {
    double getWidthScale();
    double getHeightScale();

    /**
     * 锚点的标识，用于标识点击事件的锚点
     *
     * 可以不设置，
     *
     * @return
     */
    Object getTag();
}
