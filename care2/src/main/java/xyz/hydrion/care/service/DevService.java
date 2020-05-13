package xyz.hydrion.care.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.hydrion.care.domain.ElderDev;
import xyz.hydrion.care.domain.ElderDevStatus;
import xyz.hydrion.care.mapper.ElderDevMapper;
import xyz.hydrion.care.mapper.UserDevMapper;
import xyz.hydrion.wxhelper.bean.SendMsg;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DevService {
    @Value("${wx.template.danger}")
    private String TEMPLATE_WEARER_IN_DANGER;

    @Autowired
    ElderDevMapper elderDevMapper;
    @Autowired
    UserDevMapper userDevMapper;
    @Autowired
    WxService wxService;

    public List<ElderDev> getAllDevs(){
        return elderDevMapper.selectAll();
    }

    public List<String> getUsersByDevId(Integer devId){
        return userDevMapper.selectUserByDevId(devId);
    }

    public void createDevice(ElderDev dev){
        if (elderDevMapper.selectById(dev.getId()) == null){
            dev.setCreateTime(new Timestamp(new java.util.Date().getTime()));
            elderDevMapper.insert(dev);
        }
    }

    public void insertStatus(ElderDevStatus status){
        if (elderDevMapper.selectById(status.getDevId()) != null){
            Timestamp time = new Timestamp(new java.util.Date().getTime());
            status.setUpdateTime(time);
            elderDevMapper.insertStatus(status);
            checkStatus(status);
        }
    }

    public void insertUserDev(Integer devId, String userId){
        if (elderDevMapper.selectById(devId) != null){
            userDevMapper.insert(userId,devId);
        }
    }
    public boolean login(String username,String password){
        if(password.equals(userDevMapper.getPassword(username)))
            return true;
        else
            return false;
    }
    public List<ElderDevStatus> getDevStatus(Integer devId){
        return elderDevMapper.selectStatusByDevId(devId);
    }

    public void deleteUserDev(String userId, Integer devId){
        userDevMapper.delete(userId, devId);
    }

    private void checkStatus(ElderDevStatus status){
        ElderDev dev = elderDevMapper.selectById(status.getDevId());
        if (status.getDevStatus() == 0)
            //todo:
            ;
        else if (status.getWearerStatus() == 0){
            List<String> userIds = userDevMapper.selectUserByDevId(status.getDevId());
            for (String userId : userIds){
                SendMsg msg = new SendMsg();
                msg.setUrl("");
                msg.setToUser(userId);
                msg.setTemplateId(TEMPLATE_WEARER_IN_DANGER);
                System.out.println(TEMPLATE_WEARER_IN_DANGER);
                msg.setTopColor("#FF0000");
                Map<String, SendMsg.DataValue> map = new HashMap<>();
                map.put("wearer",new SendMsg.DataValue(dev.getWearerName(),"#173177"));
                msg.setData(map);
                wxService.sendMsg(msg);
            }
        }
    }
}
