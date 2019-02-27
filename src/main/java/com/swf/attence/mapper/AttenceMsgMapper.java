package com.swf.attence.mapper;

import com.swf.attence.entity.AttenceMsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface AttenceMsgMapper extends BaseMapper<AttenceMsg> {
    /**
     * 通过时间和状态找值
     * @param day
     * @param num
     * @return
     */
   ArrayList<AttenceMsg> selectByTimeAndState(@Param("day")String day, @Param("num") int num);
}
