package com.company.sokolov.utils;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeAttrTest {

    @Test
    public void testMergeMapWithOverriding() throws Exception {
        Map<String, TestBean> beans = new HashMap<>();
        beans.put("one", new TestBean("one"));
        beans.put("two", new TestBean("two"));
        beans.put("three", new TestBean("three"));
        ModelMap model = new ModelMap();
        model.put("one", new TestBean("oneOld"));
        model.mergeAttributes(beans);
        assertEquals(3, model.size());
        assertEquals("oneOld", ((TestBean) model.get("one")).getName());
    }
}

class TestBean{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public TestBean(String name) {
        this.name = name;
    }
}