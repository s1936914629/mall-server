package cn.org.sqx.mall.common.web;

/**
 * 异常状态码
 *
 * @auther: sqx
 * @Date: 2022-09-14
 */
public enum State {

    OK(20000),
    ERR_CATEGORY_NAME_DUPLICATE(40100), // 客户端引起的--类别--名称冲突（被占用）
    ERR_CATEGORY_NOT_FOUND(40101), // 客户端引起的--类别--数据不存在（查询参数值不正确）
    ERR_INSERT(50000), // 服务端引起的--插入数据错误
    ERR_UPDATE(50001); // 服务端引起的--更新数据错误

    private Integer value;

    State(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
