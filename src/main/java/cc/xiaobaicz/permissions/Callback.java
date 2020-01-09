package cc.xiaobaicz.permissions;

/**
 * 权限申请回调接口
 * @author BC
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

    /**
     * 申请失败<br/>
     * 新增授权失败项
     * @param permissions 授权失败的权限列表
     * @since 1.3.0
     */
    void failure(String[] permissions);

    /**
     * 永不提示权限<br/>
     * <b>优先级比 @see failure 低</b>
     * @param permissions 永不提示的权限列表
     * @since 1.4.0
     */
    void neverPrompt(String[] permissions);

}
