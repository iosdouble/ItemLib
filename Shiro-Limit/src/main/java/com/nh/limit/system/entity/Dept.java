package com.nh.limit.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nh.limit.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Dept
 * @Description TODO
 * @Date 2019/10/30 2:31 PM
 * @Created by nihui
 */
@Data
@TableName("t_dept")
@Excel("部门信息表")
public class Dept implements Serializable {
    /**
     * 部门 ID
     */
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Long deptId;

    /**
     * 上级部门 ID
     */
    @TableField("PARENT_ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField("DEPT_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "部门名称")
    private String deptName;

    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Long orderNum;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;
}
