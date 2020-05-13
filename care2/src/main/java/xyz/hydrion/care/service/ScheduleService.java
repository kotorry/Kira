package xyz.hydrion.care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final WxService wxService;

    @Autowired
    public ScheduleService(WxService wxService) {
        this.wxService = wxService;
    }

    @Scheduled(fixedRate = 7200000)
    public void updateAccessTokenInTime(){
        wxService.updateAccessToken();
    }
}
