package com.nzxmmp.nzxm.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import com.nzxmmp.nzxm.entity.Rediscache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@CacheNamespace(implementation = com.nzxmmp.nzxm.entity.Rediscache.class,eviction = Rediscache.class)
//@TableName("Oldnzxmbd")
public interface OldnzxmbdMapper extends BaseMapper<Oldnzxmbd> {
}
