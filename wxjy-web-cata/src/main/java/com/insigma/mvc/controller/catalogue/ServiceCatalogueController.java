package com.insigma.mvc.controller.catalogue;

import com.insigma.http.HttpRequestUtils;
import com.insigma.mvc.ApiUriContraints;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.catalogue.SearchCondition;
import com.insigma.mvc.model.catalogue.ServiceBusType;
import com.insigma.mvc.model.catalogue.ServiceDepartment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务目录controller
 */
@Controller
@RequestMapping("/catalogue")
public class ServiceCatalogueController extends MvcHelper {

    @Resource
    private HttpRequestUtils httpRequestUtils;

    /**
     * 个人服务大厅页面
     *
     * @param modelMap
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @RequestMapping("/perServiceHall")
    public String perServiceHall(HttpSession session, ModelMap modelMap, SearchCondition searchCondition) throws Exception {
        searchCondition.setConsumerType("1"); //个人
        searchCondition.setSearchType(StringUtils.isNotEmpty(searchCondition.getSearchType())? searchCondition.getSearchType() : "1");
        if (StringUtils.isNotEmpty(searchCondition.getDepartmentId())) {
            searchCondition.setSearchType("2");
        }

        JSONObject obj = httpRequestUtils.httpPostReturnObject(ApiUriContraints.API_CATA_PERSERVICE_HALL, searchCondition);
        modelMap.put("busTypeList", JSONArray.toList(obj.getJSONArray("busTypeList"), ServiceBusType.class));
        modelMap.put("departmentList", JSONArray.toList(obj.getJSONArray("departmentList"), ServiceDepartment.class));
        modelMap.put("condition", searchCondition);
        modelMap.put("listMap", obj.getJSONObject("listMap"));
        return "catalogue/perServiceHall";
    }

    /**
     * 单位服务大厅页面
     *
     * @param modelMap
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @RequestMapping("/comServiceHall")
    public String comServiceHall(ModelMap modelMap, SearchCondition searchCondition) throws Exception {
        searchCondition.setConsumerType("2"); //单位
        searchCondition.setSearchType( StringUtils.isNotEmpty(searchCondition.getSearchType()) ? searchCondition.getSearchType() : "1");
        if (StringUtils.isNotEmpty(searchCondition.getDepartmentId())) {
            searchCondition.setSearchType("2");
        }

        JSONObject obj = httpRequestUtils.httpPostReturnObject(ApiUriContraints.API_CATA_COMSERVICE_HALL, searchCondition);
        modelMap.put("busTypeList", JSONArray.toList(obj.getJSONArray("busTypeList"), ServiceBusType.class));
        modelMap.put("departmentList", JSONArray.toList(obj.getJSONArray("departmentList"), ServiceDepartment.class));
        modelMap.put("condition", searchCondition);
        modelMap.put("listMap", obj.getJSONObject("listMap"));
        return "catalogue/comServiceHall";
    }

    /**
     * list
     * @param session
     * @param modelMap
     * @param searchCondition
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpSession session, ModelMap modelMap, SearchCondition searchCondition) throws Exception {
        JSONObject obj = httpRequestUtils.httpPostReturnObject(ApiUriContraints.API_CATA_LIST, searchCondition);
        modelMap.put("busTypeList", JSONArray.toList(obj.getJSONArray("busTypeList"), ServiceBusType.class));
        modelMap.put("list", obj.getJSONObject("list"));
        modelMap.put("condition", searchCondition);
        return "catalogue/cataList";
    }

    /**
     * detail
     * @param session
     * @param modelMap
     * @param cataId
     * @return
     * @throws Exception
     */
    @RequestMapping("/detail/{cataId}")
    public String detail(HttpSession session, ModelMap modelMap, @PathVariable String cataId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("cata_id", cataId);
        JSONObject obj = httpRequestUtils.httpPostReturnObject(ApiUriContraints.API_CATA_DETAIL, map);
        modelMap.put("catalogue", obj.getJSONObject("catalogue"));
        modelMap.put("detailList", obj.getJSONArray("detailList"));
        return "catalogue/detail";
    }

    /**
     * map
     * @param modelMap
     * @param lon
     * @param lat
     * @param department
     * @param address
     * @return
     * @throws Exception
     */
    @RequestMapping("/map")
    public String map(ModelMap modelMap, double lon, double lat, String department, String address) throws Exception {
        modelMap.put("lon", lon);
        modelMap.put("lat", lat);
        modelMap.put("department", department);
        modelMap.put("address", address);
        return "catalogue/map";
    }
}
