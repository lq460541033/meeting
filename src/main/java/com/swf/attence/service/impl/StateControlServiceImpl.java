package com.swf.attence.service.impl;

import com.swf.attence.entity.StateControl;
import com.swf.attence.mapper.StateControlMapper;
import com.swf.attence.service.IStateControlService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2018-12-30
 */
@Service
public class StateControlServiceImpl extends ServiceImpl<StateControlMapper, StateControl> implements IStateControlService {
    @Autowired
   private  StateControlMapper stateControlMapper;
    @Override
    public boolean failidExist(StateControl stateControl) {
        StateControl stateControl1 = stateControlMapper.selectOne(stateControl);
        if (stateControl1==null){
            return  true;
        }else {
            return false;
        }
    }


}
