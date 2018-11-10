package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.UserMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserDao extends CrudRepository<UserMode, Integer> {
    UserMode findByWechatToken(String openid);
    UserMode findByShiyongToken(String s);
    @Query(nativeQuery = true, value = "SELECT * FROM t_user where commodity_id = ?1 order by id desc limit ?2, 20")
    List<UserMode> findlist(String hangye,int index);
    @Query(nativeQuery = true, value = "SELECT * FROM t_user order by id desc limit ?1, 20")
    List<UserMode> findlist(int index);

}
