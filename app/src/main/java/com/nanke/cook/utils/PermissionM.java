package com.nanke.cook.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 16/11/29.
 */

public class PermissionM {

    private boolean isNeedCheck = true;


    public interface PermissionProxy<T> {
        void grant(T source, int requestCode);

        void denied(T source, int requestCode);

        boolean needShowRationale(int requestCode);
    }


    private PermissionProxy permissionProxy;


    public PermissionM(PermissionProxy permissionProxy) {
        this.permissionProxy = permissionProxy;
    }

    public PermissionProxy getPermissionProxy() {
        return permissionProxy;
    }

    public void setPermissionProxy(PermissionProxy permissionProxy) {
        this.permissionProxy = permissionProxy;
    }

    public void requestPermissions(Context context, int requestCode, String... permissions) {
        if(!isNeedCheck)
            return;
        if (permissionProxy == null) {
            throw new NullPointerException("permissionProxy is null");
        }

        if (!isOverMarshmallow()) {
            permissionProxy.grant(context, requestCode);
            return;
        }
        checkPermissions(context, requestCode, permissions);
    }


    /**
     * @param permissions
     * @since 2.5.0
     */
    private void checkPermissions(Context context, int requestCode, String... permissions) {


        if (permissionProxy.needShowRationale(requestCode)) {
            List<String> needRequestPermissonList = findDeniedPermissions(context, permissions);
            if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
                _requestPermissions(context, requestCode, needRequestPermissonList);
            } else {
                permissionProxy.grant(context, requestCode);
            }
        } else {
            List<String> needRequestPermissonList = findRefusedPermissions(context, permissions);
            if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
                _requestPermissions(context, requestCode, needRequestPermissonList);
            } else {
                permissionProxy.grant(context, requestCode);
            }
        }
    }

    /**
     * 请求需要的权限
     *
     * @param context
     * @param requestCode
     * @param needRequestPermissonList
     */
    private void _requestPermissions(Context context, int requestCode, List<String> needRequestPermissonList) {
        ActivityCompat.requestPermissions((Activity) context, needRequestPermissonList.toArray(
                new String[needRequestPermissonList.size()]),
                requestCode);
    }

    /**
     * 获取权限 被拒绝授权的权限除外
     *
     * @param context
     * @param permissions
     * @return
     */
    private List<String> findRefusedPermissions(Context context, String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ActivityCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED
                    && !ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) context, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> findDeniedPermissions(Context context, String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ActivityCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) context, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(Context context, int requestCode, int[] paramArrayOfInt) {
        isNeedCheck = false;
        if (!verifyPermissions(paramArrayOfInt)) {
            permissionProxy.denied(context, requestCode);
        } else {
            permissionProxy.grant(context, requestCode);
        }
    }



    private boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
    private Activity getActivity(Context context) {
        if(context instanceof Activity){
            return (Activity) context;
        }
        return null;
    }
}
