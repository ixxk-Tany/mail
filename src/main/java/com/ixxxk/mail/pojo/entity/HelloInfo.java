package com.ixxxk.mail.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述: 
 * @Author: Tany
 * @CreateDate: 2020/7/5
 * @UpdateUser: Tany
 * @UpdateDate: 2020/7/5
 * @Version: 3.0.1
 */
@TableName(value = "hello_info")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloInfo extends Model<HelloInfo> {
    private static final long serialVersionUID = -2369336483506690528L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String ip;
    private String city;
    private Date createTime;
}
