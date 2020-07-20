package cn.meredith.day18.service.impl;

import cn.meredith.day18.dao.LogDao;
import cn.meredith.day18.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addLog() {
        logDao.add("addLog" + System.currentTimeMillis());
    }
}
