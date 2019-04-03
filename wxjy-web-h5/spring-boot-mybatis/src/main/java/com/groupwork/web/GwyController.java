package com.groupwork.web;

import com.groupwork.entity.G04;
import com.groupwork.mapper.G04Mapper;
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
    G04Mapper g04Mapper;

    /**
     * qrcode
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        List<G04> g04List=g04Mapper.getAll("0d558667c4e148ebbf1a43cdd8c636af");
        HashMap map=new HashMap();
        map.put("g04list",g04List);
        model.addAllAttributes(map);
        return "gwy/list";
    }

}