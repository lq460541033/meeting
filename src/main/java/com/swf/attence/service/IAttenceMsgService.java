package com.swf.attence.service;

import com.swf.attence.entity.AttenceMsg;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
public interface IAttenceMsgService extends IService<AttenceMsg> {
    /**
     * 添加的用户是否存在
     * @param attenceMsg
     * @return
     */
  boolean attenceMsgExist(AttenceMsg attenceMsg);
}
