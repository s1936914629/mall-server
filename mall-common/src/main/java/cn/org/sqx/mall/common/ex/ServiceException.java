package cn.org.sqx.mall.common.ex;

import cn.org.sqx.mall.common.web.State;

/**
 * 全局异常类
 *
 * @auther: sqx
 * @Date: 2022-09-12
 */
public class ServiceException extends RuntimeException {
    private State state;

    public ServiceException(State state, String message) {
        super(message);
        if (state == null) {
            throw new IllegalArgumentException("使用ServiceException必须指定错误时的业务状态码！");
        }
        this.state=state;
    }

    public State getState(){
        return state;
    }
}
