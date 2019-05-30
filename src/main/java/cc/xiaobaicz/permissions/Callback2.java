package cc.xiaobaicz.permissions;

/**
 * 权限申请回调接口
 * @since 1.3
 * @author BC
 */
public abstract class Callback2 implements Callback {

    /**
     * 申请失败<br/>
     * 新增授权失败项
     */
    public void failure(String[] permissions) {}

    @Override
    public void failure() {}

}
