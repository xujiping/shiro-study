package com.study.common;

import tk.mybatis.mapper.common.Mapper;

/**
 * 基本mapper继承通用mapper，可以直接使用通用的增删改查等方法
 *
 * @author xujiping 2018-01-16 17:36
 */

public interface BaseDao<T> extends Mapper<T> {
}
