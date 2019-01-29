package com.swf.attence.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : white.hou
 * @description : jpa实现的对实体类ICommand的crud
 * @date: 2019/1/29_11:28
 */
@Repository
public interface ICommandJpa extends JpaRepository<com.swf.attence.entity.ICommand,Integer> {
}
