package com.yf.warehouse.viewhelper;

/**
 * @author cjq
 */
public class DemoDetails {
    public final int titleId;
    public int descriptionId = -1;
    public final Class<? extends android.app.Activity> activityClass;

    public DemoDetails(int titleId, int descriptionId,
                       Class<? extends android.app.Activity> activityClass) {
        super();
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;
    }
}
