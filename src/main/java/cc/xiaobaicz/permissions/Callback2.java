package cc.xiaobaicz.permissions;

/**
 * 权限申请回调接口
 * @since 1.3
 * @author BC
 */
public abstract class Callback2 implements Callback {

    @Override
    public void failure() {}

    @Override
    public void failure(String[] permissions) {}

    @Override
    public void neverPrompt(String[] permissions) {}

}
