package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.RedMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface RedDao extends CrudRepository<RedMode, Integer> {
    RedMode findByUserWxToken(String openid);
    List<RedMode> findBySuperiorWxToken(String openid);
    @Query(nativeQuery = true, value = "SELECT O.superior_money,O.creat_time,C.wechat_name,C.wechat_icon FROM red_packet O , t_user C WHERE O.user_wx_token = C.wechat_token AND O.superior_wx_token = ?1")
    List<Map<String,Object>> findVoteList(@Param("token") String token);

}
