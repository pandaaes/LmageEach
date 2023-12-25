package com.example.lmageeach.controller;

import com.example.lmageeach.model.LmageData;
import com.example.lmageeach.service.lmageDataSer.LmageDataServiceImp.LmageDataServiceImpl;
import com.example.lmageeach.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/lmage")
public class LmageDataController {

    @Autowired
    private LmageDataServiceImpl lmageDataService;

    @RequestMapping("/upload")
    public Result lmageUpload(@RequestParam("image") MultipartFile file, @RequestBody LmageData lmageData, HttpSession session){

        lmageDataService.upload(file,lmageData,session);

        return Result.ok();
    }
}
