package com.ixxxk.mail.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述: 
 * @Author: Tany
 * @CreateDate: 2020/7/5
 * @UpdateUser: Tany
 * @UpdateDate: 2020/7/5
 * @Version: 3.0.1
 */
@Data
public class HelloInfoDto implements Serializable {
    private static final long serialVersionUID = 2411121227576763338L;
    private String ip = "未知";
    private String city = "未知";
}
