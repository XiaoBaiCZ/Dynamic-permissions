package cc.xiaobaicz.permissions;

/**
 * 权限申请回调接口
 * @author BC
 * @deprecated
 */
public interface Callback {
    /**
     * 申请成功
     */
    void success();

    /**
     * 申请失败
     */
    @Deprecated
    void failure();
}
