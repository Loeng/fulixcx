package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CallStaffRepository extends JpaRepository<CallStaffMode,Integer>,JpaSpecificationExecutor<CallStaffMode> {
    @Modifying
    @Query(nativeQuery = true, value = "update call_staff set state =?2 where id = ?1")
    void updateStatus(Integer id, Integer state);
}
