package com.example.lmageeachtwo.model.controller.UserDataCon;





import com.example.lmageeachtwo.model.service.UserDataSer.UserDataServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserDataController {

    @Resource
    private UserDataServiceImpl userDataService;

    @GetMapping("/")
    public Object getTest(){
        return userDataService.list();
    }
}
