package com.ixxxk.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ixxxk.mail.pojo.entity.HelloInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述: 
 * @Author: Tany
 * @CreateDate: 2020/7/5
 * @UpdateUser: Tany
 * @UpdateDate: 2020/7/5
 * @Version: 3.0.1
 */
@Repository
public interface HelloMapper extends BaseMapper<HelloInfo> {
    /**
     * SELECT * FROM 表名 WHERE TO_DAYS( NOW( ) ) - TO_DAYS( 时间字段名) <= 1
     * 查询昨天的内容
     * @return list
     */
    @Select("select * from hello_info where TO_DAYS(now()) - TO_DAYS(create_time) = 1")
    List<HelloInfo> findByYesterday();
}
