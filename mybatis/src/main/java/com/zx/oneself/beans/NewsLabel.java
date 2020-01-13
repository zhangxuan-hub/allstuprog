package com.zx.oneself.beans;

import java.util.Set;

/**
 * @ClassName NewsLabel
 * @Author Administrator
 * @Description TODO
 * @Date 2020/1/13 12:47
 * @Version 1.0
 */
public class NewsLabel {
    private Integer id;
    private String name;
    private Set<NewsLabel> children;

    public NewsLabel() {
    }

    public NewsLabel(Integer id, String name, Set<NewsLabel> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsLabel> getChildren() {
        return children;
    }

    public void setChildren(Set<NewsLabel> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NewsLabel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
