package xyz.hydrion.care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.hydrion.care.domain.ElderDev;
import xyz.hydrion.care.domain.ElderDevStatus;
import xyz.hydrion.care.domain.form.DevForm;
import xyz.hydrion.care.domain.form.UserForm;
import xyz.hydrion.care.service.DevService;

import java.util.List;

@RequestMapping("/device")
@Controller
public class DevController {

    @Autowired
    DevService devService;


    @ResponseBody
    @RequestMapping(value = "/{dev_id}/status", method = RequestMethod.POST)
    public void updateStatus(@PathVariable Integer dev_id,
                             @RequestBody ElderDevStatus status){
        status.setDevId(dev_id);
        devService.insertStatus(status);
    }

    @ResponseBody
    @RequestMapping(value = "/{dev_id}/user/{user_id}", method = RequestMethod.PUT)
    public void addUser(@PathVariable(name = "dev_id") Integer devId,
                        @PathVariable(name = "user_id") String userId){
        devService.insertUserDev(devId,userId);
    }

    @RequestMapping(value = "/{dev_id}/status", method = RequestMethod.GET)
    public ModelAndView scanStatus(@PathVariable(name = "dev_id") Integer devId){
        List<ElderDevStatus> list = devService.getDevStatus(devId);
        ModelAndView model = new ModelAndView("/table");
        model.addObject("dataList",list);
        model.addObject("test","String");
        return model;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String scanDevice(Model model){
        List<ElderDev> list = devService.getAllDevs();
        model.addAttribute("devs",list);
        model.addAttribute("devForm",new DevForm());
        return "/device";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        List<ElderDev> list = devService.getAllDevs();
        model.addAttribute("devs",list);
        model.addAttribute("devForm",new DevForm());
        return "/index";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createDev(@ModelAttribute DevForm form){
        ElderDev dev = new ElderDev(form);
        devService.createDevice(dev);
        return "redirect:/device";
    }

    @RequestMapping(value = "/{dev_id}/users")
    public String scanUsers(Model model, @PathVariable(name = "dev_id") Integer devId){
        List<String> list = devService.getUsersByDevId(devId);
        model.addAttribute("devId",devId);
        model.addAttribute("users",list);
        model.addAttribute("userForm",new UserForm());
        return "/users";
    }

    @RequestMapping(value = "/{dev_id}/users", method = RequestMethod.POST)
    public String createUserDev(@ModelAttribute UserForm form, @PathVariable(name = "dev_id") Integer devId){
        devService.insertUserDev(devId,form.getUserId());
        return "redirect:/device/" + devId +"/users";
    }

    @RequestMapping(value = "/{dev_id}/users/delete/{user_id}", method = RequestMethod.GET)
    public String deleteUserDev(@PathVariable(name = "dev_id") Integer devId,
                                @PathVariable(name = "user_id") String userId){
        devService.deleteUserDev(userId, devId);
        return "redirect:/device/" + devId +"/users";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)

    @ResponseBody
    public String login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password){
        if(devService.login(username,password))
            return "ok";
        else
            return "false";
    }
}
