package com.gys.fulixcx.dao;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallTaskMode;
import com.gys.fulixcx.request.TaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallTaskRepository extends JpaRepository<CallTaskMode,Integer> {

    EasyUiVo findList(TaskRequest taskRequest);
}
