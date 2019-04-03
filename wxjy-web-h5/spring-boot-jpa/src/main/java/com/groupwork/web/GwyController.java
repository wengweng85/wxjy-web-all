package com.groupwork.web;

import com.groupwork.dao.G04Dao;
import com.groupwork.entity.G04;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/gwy")
public class GwyController {

    @Autowired
    G04Dao g04Dao;

    /**
     * qrcode
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        List<G04> g04List=g04Dao.findAll();
        HashMap map=new HashMap();
        map.put("g04list",g04List);
        model.addAllAttributes(map);
        return "gwy/list";
    }

}