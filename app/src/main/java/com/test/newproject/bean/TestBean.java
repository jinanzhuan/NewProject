package com.test.newproject.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/30
 *     desc   :
 *     modify :
 * </pre>
 */

public class TestBean {
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List<TestBean> getList(){
        List<TestBean> list = new ArrayList<>();
        TestBean bean = null;
        for(int i = 0; i < 20; i++) {
            bean = new TestBean();
            bean.setName("测试"+(i+1));
            bean.setContent("冻死了附近胜利大街发送来得及发时间到了飞机上家乐福时间段的设计费了");
            list.add(bean);
        }
        return list;
    }
}
