package cn.org.sqx.mall.product.webapi.controller;

import cn.org.sqx.mall.common.web.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @auther: sqx
 * @Date: 2022-09-14
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("classpath:truncate.sql")
    public void testAddNewSuccessfully() throws Exception {

        // 准备测试数据，不需要封装，应该全部声明为String类型
        String name = "水果";
        String parentId = "0"; // 即使目标类型是Long，参数值也不要加L
        String keywords = "水果的关键字是啥";
        String sort = "66";
        String icon = "图标待定";

        /*
            补全Category对象中的属性值：isParent >>> 0
            category.setIsParent(0);
         */
        // Integer isParent;
        String isDisplay = "1";
        // 请求路径，不需要写协议、服务器主机和端口号
        String url = "/categories/add-new";
        // 执行测试
        // 以下代码相对比较固定
        mockMvc.perform( // 执行发出请求
                        MockMvcRequestBuilders.post(url) // 根据请求方式决定调用的方法
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的文档类型，例如：application/json; charset=utf-8
                                .param("name", name) // 请求参数，有多个时，多次调用param()方法
                                .param("parentId", parentId)
                                .param("keywords", keywords)
                                .param("icon", icon)
                                .param("sort", sort)
                                .param("isDisplay", isDisplay)
                                .accept(MediaType.APPLICATION_JSON)) // 接收的响应结果的文档类型，注意：perform()方法到此结束
                .andExpect( // 预判结果，类似断言
                        MockMvcResultMatchers
                                .jsonPath("state") // 预判响应的JSON结果中将有名为state的属性
                                .value(20000)) // 预判响应的JSON结果中名为state的属性的值，注意：andExpect()方法到此结束
                .andDo( // 需要执行某任务
                        MockMvcResultHandlers.print()); // 打印日志
    }

    @Test
    @Sql({"classpath:truncate.sql", "classpath:insert_data.sql"})
    public void testAddNewFailBecauseNameDuplicate() throws Exception {
        // 准备测试数据，不需要封装，应该全部声明为String类型
        String name = "类别001";
        String parentId = "0"; // 即使目标类型是Long，参数值也不要加L
        String keywords = "水果的关键字是啥";
        String sort = "66";
        String icon = "图标待定";
        String isDisplay = "1";
        // 请求路径，不需要写协议、服务器主机和端口号
        String url = "/categories/add-new";
        // 执行测试
        // 以下代码相对比较固定
        mockMvc.perform( // 执行发出请求
                        MockMvcRequestBuilders.post(url) // 根据请求方式决定调用的方法
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的文档类型，例如：application/json; charset=utf-8
                                .param("name", name) // 请求参数，有多个时，多次调用param()方法
                                .param("parentId", parentId)
                                .param("keywords", keywords)
                                .param("icon", icon)
                                .param("sort", sort)
                                .param("isDisplay", isDisplay)
                                .accept(MediaType.APPLICATION_JSON)) // 接收的响应结果的文档类型，注意：perform()方法到此结束
                .andExpect( // 预判结果，类似断言
                        MockMvcResultMatchers
                                .jsonPath("state") // 预判响应的JSON结果中将有名为state的属性
                                .value(State.ERR_CATEGORY_NAME_DUPLICATE.getValue())) // 预判响应的JSON结果中名为state的属性的值，注意：andExpect()方法到此结束
                .andDo( // 需要执行某任务
                        MockMvcResultHandlers.print()); // 打印日志
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testAddNewFailBecauseParentNotFound() throws Exception {
        // 准备测试数据，不需要封装，应该全部声明为String类型
        String name = "类别001";
        String parentId = "-1"; // 即使目标类型是Long，参数值也不要加L
        String keywords = "水果的关键字是啥";
        String sort = "66";
        String icon = "图标待定";
        String isDisplay = "1";
        // 请求路径，不需要写协议、服务器主机和端口号
        String url = "/categories/add-new";
        // 执行测试
        // 以下代码相对比较固定
        mockMvc.perform( // 执行发出请求
                        MockMvcRequestBuilders.post(url) // 根据请求方式决定调用的方法
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的文档类型，例如：application/json; charset=utf-8
                                .param("name", name) // 请求参数，有多个时，多次调用param()方法
                                .param("parentId", parentId)
                                .param("keywords", keywords)
                                .param("icon", icon)
                                .param("sort", sort)
                                .param("isDisplay", isDisplay)
                                .accept(MediaType.APPLICATION_JSON)) // 接收的响应结果的文档类型，注意：perform()方法到此结束
                .andExpect( // 预判结果，类似断言
                        MockMvcResultMatchers
                                .jsonPath("state") // 预判响应的JSON结果中将有名为state的属性
                                .value(State.ERR_CATEGORY_NOT_FOUND.getValue())) // 预判响应的JSON结果中名为state的属性的值，注意：andExpect()方法到此结束
                .andDo( // 需要执行某任务
                        MockMvcResultHandlers.print()); // 打印日志
    }

    @Test
    @Sql({"classpath:truncate.sql"})
    public void testAddNewFailBecauseBadRequest() throws Exception {
        // 准备测试数据，注意：此次没有提交必要的name属性值
        String parentId = "0"; // 即使目标类型是Long，参数值也不要加L
        String keywords = "水果的关键字是啥";
        String sort = "66";
        String icon = "图标待定";
        String isDisplay = "1";
        // 请求路径，不需要写协议、服务器主机和端口号
        String url = "/categories/add-new";
        // 执行测试
        // 以下代码相对比较固定
        mockMvc.perform( // 执行发出请求
                        MockMvcRequestBuilders.post(url) // 根据请求方式决定调用的方法
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 请求数据的文档类型，例如：application/json; charset=utf-8
                                // .param("name", name) // 注意：此处不提交必要的name属性
                                .param("parentId", parentId)
                                .param("keywords", keywords)
                                .param("icon", icon)
                                .param("sort", sort)
                                .param("isDisplay", isDisplay)
                                .accept(MediaType.APPLICATION_JSON)) // 接收的响应结果的文档类型，注意：perform()方法到此结束
                .andExpect( // 预判结果，类似断言
                        MockMvcResultMatchers
                                .jsonPath("state") // 预判响应的JSON结果中将有名为state的属性
                                .value(State.ERR_BAD_REQUEST.getValue())) // 预判响应的JSON结果中名为state的属性的值，注意：andExpect()方法到此结束
                .andDo( // 需要执行某任务
                        MockMvcResultHandlers.print()); // 打印日志
    }


}
