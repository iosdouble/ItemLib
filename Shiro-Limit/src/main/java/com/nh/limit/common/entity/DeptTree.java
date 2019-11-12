package com.nh.limit.common.entity;

import com.nh.limit.system.entity.Dept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname DeptTree
 * @Description TODO
 * @Date 2019/10/30 2:30 PM
 * @Created by nihui
 */
@Data
public class DeptTree<T> implements Serializable {
    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DeptTree<T>> children;
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Dept data;

    public void initChildren(){
        this.children = new ArrayList<>();
    }
}
