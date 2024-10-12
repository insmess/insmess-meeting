package com.insmess.meeting.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 通用Service实现类的父类
 * @param <M> Mapper类型
 * @param <T> 实体类类型
 * @author xujq
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

}
