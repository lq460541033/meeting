package com.swf.attence.service.impl;

import com.swf.attence.entity.LeaveMsg;
import com.swf.attence.mapper.LeaveMsgMapper;
import com.swf.attence.service.ILeaveMsgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator123
 * @since 2019-02-26
 */
@Service
public class LeaveMsgServiceImpl extends ServiceImpl<LeaveMsgMapper, LeaveMsg> implements ILeaveMsgService {

    @Override
    public void insertIntoDatabase(String username, String failStart, String failEnd, String description) {
        LeaveMsg leaveMsg = new LeaveMsg();
        String r1 = UUID.randomUUID().toString().replace("-", "");
        leaveMsg.setId(r1);
        leaveMsg.setUsername(username);
        leaveMsg.setFailStart(failStart);
        leaveMsg.setFailEnd(failEnd);
        leaveMsg.setDescription(description);
        insert(leaveMsg);
    }
}
