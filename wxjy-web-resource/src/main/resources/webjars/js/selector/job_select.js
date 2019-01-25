var strVarJob = '';
strVarJob += '<div class="aui_state_box"><div class="aui_state_box_bg"></div>';
strVarJob += '  <div class="aui_alert_zn aui_outer">';
strVarJob += '    <table class="aui_border" style="border:2px solid #fff;">';
strVarJob += '      <tbody>';
strVarJob += '        <tr>';
strVarJob += '          <td class="aui_c">';
strVarJob += '            <div class="aui_inner">';
strVarJob += '              <table class="aui_dialog">';
strVarJob += '                <tbody>';
strVarJob += '                  <tr>';
strVarJob += '                    <td class="aui_header" colspan="2"><div class="aui_titleBar">';
strVarJob += '                      <div class="aui_title" style="cursor: move;">选择工种信息</div>';
strVarJob += '                        <a href="javascript:;" class="aui_close" onclick="closeJob()">×</a>';
strVarJob += '                      </div>';
strVarJob += '                    </td>';
strVarJob += '                  </tr>';
strVarJob += '                  <tr>';
strVarJob += '                  <tr>';
strVarJob += '                    <td class="aui_icon" style="display: none;">';
strVarJob += '                     <div class="aui_iconBg" style="background: transparent none repeat scroll 0% 0%;"></div></td>';
strVarJob += '                       <td class="aui_main" style="width: auto; height: auto;">';
strVarJob += '                        <div class="aui_content" style="padding: 0px; position:relative">';
strVarJob += '                          <div id="" style="width: 680px; position:relative;">';
strVarJob += '                            <div class="data-result"><em>最多选择 <strong>2000</strong> 项</em></div>';
strVarJob += '                            <div class="data-error" style="display: none;">最多只能选择 3 项</div>';
strVarJob += '                            <div class="data-search" id="searchRun"><input class="run" name="searcharea"/><div class="searchList run"></div></div>';
strVarJob += '                            <div class="data-tabs">';
strVarJob += '                              <ul>';
strVarJob += '                                <li onclick="removenode_job(this)" data-selector="tab-all" class="active"><a href="javascript:;"><span>全部</span><em></em></a></li>';
strVarJob += '                              </ul>';
strVarJob += '                            </div>';
strVarJob += '                            <div class="data-container data-container-city">';
strVarJob += '                            </div>';
strVarJob += '                          </div>';
strVarJob += '                        </div>';
strVarJob += '                      </div>';
strVarJob += '                    </td>';
strVarJob += '                  </tr>';
strVarJob += '                  <tr>';
strVarJob += '                    <td class="aui_footer" colspan="2">';
strVarJob += '                      <div class="aui_buttons">';
strVarJob += '                      <button class="aui-btn aui-btn-primary" onclick="saveJob()" type="button">确定</button>';
strVarJob += '                        <button class="aui-btn aui-btn-light" onclick="closeJob()" type="button">取消</button>';
strVarJob += '                      </div>';
strVarJob += '                    </td>';
strVarJob += '                  </tr>';
strVarJob += '                </tbody>';
strVarJob += '              </table>';
strVarJob += '            </div></td>';
strVarJob += '        </tr>';
strVarJob += '      </tbody>';
strVarJob += '    </table>';
strVarJob += '  </div>';
strVarJob += '</div>';

// 全局变量
var datatype = "";
var selectText = null;
var selectId = null;
var isMinLevel = false;

var searchJobValue = searchJobData();
var selectNum;

function openJob(job_id,job_name,Maxsize,isToMinLevel){
    //只能选择最小级
    if(isToMinLevel){
        isMinLevel = true;
    }
    //是否多选
    if(Maxsize && Maxsize>1){
        selectMoreJob(job_id,job_name,Maxsize);
    }else{
        selectOneJob(job_id,job_name);
    }
}

function selectMoreJob(job_id,job_name,Maxsize){
    appendJob(job_id, job_name, 'duoxuan', Maxsize);
}

function selectOneJob(job_id,job_name){
    appendJob(job_id, job_name, 'danxuan', 1);
}


function appendJob(_selectId, _selectText, Cityxz, Maxsize) {
    selectId = '#'+_selectId;
    selectText = '#'+_selectText;
    datatype = Cityxz;
    selectNum = Maxsize;
    $('body').append(strVarJob);
    if (datatype == "danxuan") {
        $('.data-result').find('strong').text('1');
    } else {
        $('.data-result').find('strong').text(Maxsize);
    }
    if ($(selectId).val() != "") {
        var inputarry = $(selectId).val().split(',');
        var inputarryname = $(selectText).val().split(',');
        if (inputarry.length > 0) {
            for (var i in inputarry) {
                $('.data-result').append('<span class="svae_box aui-titlespan" data-code="' + inputarry[i] + '" data-name="' + inputarryname[i] + '" onclick="removespan_job(this)">' + inputarryname[i] + '<i>×</i></span>');
            }
        }
    }

    var minwid = document.documentElement.clientWidth;
    $('.aui_outer .aui_header').on("mousedown", function (e) {
        /*$(this)[0].onselectstart = function(e) { return false; }*///防止拖动窗口时，会有文字被选中的现象(事实证明不加上这段效果会更好)
        $(this)[0].oncontextmenu = function (e) { return false; } //防止右击弹出菜单
        var getStartX = e.pageX;
        var getStartY = e.pageY;
        var getPositionX = (minwid / 2) - $(this).offset().left,
            getPositionY = 60;
        $(document).on("mousemove", function (e) {
            var getEndX = e.pageX;
            var getEndY = e.pageY;
            $('.aui_outer').css({
                left: getEndX - getStartX - getPositionX,
                top: getEndY - getStartY + getPositionY
            });

        });
        $(document).on("mouseup", function () {
            $(document).unbind("mousemove");
        })
    });
    selectJob('all', null, '');
    auto_job.run();
}

var jobDataArrary = __LocalJobData.list;
function selectJob(type, con, isremove) {
    //显示省级
    var strVarJob = "";
    if (type == "all") {
        var dataCityxz      = __LocalJobData.category.provinces;
        var datahotcityxz   = __LocalJobData.category.hotcities;
        // 加载热门城市和省份
        strVarJob += '<div class="view-all" id="">';
        strVarJob += '  <p class="data-title">热门工种</p>';
        strVarJob += '    <div class="data-list data-list-hot">';
        strVarJob += '      <ul class="clearfix">';
        for (var i in datahotcityxz) {
            strVarJob += '<li><a href="javascript:;" data-code="' + datahotcityxz[i] + '" data-name="' + jobDataArrary[datahotcityxz[i]][0] + '" class="d-item"  onclick="selectJob(\'sub\',this,\'\')">' + jobDataArrary[datahotcityxz[i]][0] + '<label>0</label></a></li>';
        }
        strVarJob += '      </ul>';
        strVarJob += '    </div>';
        strVarJob += '    <p class="data-title">全部</p>';
        strVarJob += '   <div class="data-list data-list-provinces">';
        strVarJob += '  <ul class="clearfix">';
        for (var i in dataCityxz) {
            strVarJob += '<li><a href="javascript:;" data-code="' + dataCityxz[i] + '" data-name="' + jobDataArrary[dataCityxz[i]][0] + '" class="d-item"  onclick="selectJob(\'sub\',this,\'\')">' + jobDataArrary[dataCityxz[i]][0] + '<label>0</label></a></li>';
        }
        strVarJob += ' </ul>';
        strVarJob += '</div>';
        $('.data-container-city').html(strVarJob);

        $('.data-result span').each(function (index) {
            if ($('a[data-code=' + $(this).data("code") + ']').length > 0) {
                $('a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
                if ($('a[data-code=' + $(this).data("code") + ']').attr("class").indexOf('data-all') > 0) {
                    $('a[data-code=' + $(this).data("code") + ']').parent('li').nextAll('li').find('a').css({ 'color': '#ccc', 'cursor': 'not-allowed' });
                    $('a[data-code=' + $(this).data("code") + ']').parent('li').nextAll("li").find('a').attr("onclick", "");
                } else {
                    var date_code=$(this).data("code");
                    while(typeof (jobDataArrary[date_code]) != "undefined"){
                        var super_date_code = jobDataArrary[date_code][4];
                        if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                            var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
                            $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) + 1).show();
                        }
                        date_code = super_date_code;
                    }
                }
            } else {
                var date_code=$(this).data("code");
                while(typeof (jobDataArrary[date_code]) != "undefined"){
                    var super_date_code = jobDataArrary[date_code][4];
                    if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                        var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
                        $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) + 1).show();
                    }
                    date_code = super_date_code;
                }
            }
        });
    }
    //显示下一级
    else {
        var dataCityxz = __LocalJobData.category.provinces;
        var relations  = __LocalJobData.relations;
        if (typeof (relations[$(con).data("code")]) != "undefined") {
            //添加标题
            if (isremove != "remove") {
                $('.data-tabs li').each(function () {
                    $(this).removeClass('active');
                });
                //长度过长截取
                var fatherName = $(con).data("name").toString();
                if(fatherName.length>8){
                    fatherName = fatherName.substring(0, 7)+'...';
                }
                $('.data-tabs ul').append('<li data-code=' + $(con).data("code") + ' data-name=' + $(con).data("name") + ' class="active" onclick="removenode_job(this)"><a href="javascript:;"><span>' + fatherName + '</span><em></em></a></li>');
            }
            //添加内容
            strVarJob += '<ul class="clearfix">';
            if (datatype == "danxuan") {
                strVarJob += '<li class="" style="width:100%" ><a href="javascript:;" class="d-item data-all"  data-code="' + $(con).data("code") + '"  data-name="' + $(con).data("name") + '"  onclick="selectitem_job(this)">' + $(con).data("name") + '<label>0</label></a></li>';
            } else {
                strVarJob += '<li class="" style="width:100%"><a href="javascript:;" class="d-item data-all"  data-code="' + $(con).data("code") + '"  data-name="' + $(con).data("name") + '"  onclick="selectitem_job(this)">' + $(con).data("name") + '<label>0</label></a></li>';
            }
            for (var i in relations[$(con).data("code")]) {
                strVarJob += '<li><a href="javascript:;" class="d-item" data-code="' + relations[$(con).data("code")][i] + '"  data-name="' + jobDataArrary[relations[$(con).data("code")][i]][0] + '" onclick="selectJob(\'sub\',this,\'\')">' + jobDataArrary[relations[$(con).data("code")][i]][0] + '<label>0</label></a></li>';
            }
            strVarJob += '</ul>';
            $('.data-container-city').html(strVarJob);
        } else {
            if (datatype == "duoxuan") {
                if ($(con).attr("class").indexOf('d-item-active') > 0) {//如果原先是已选择，则是取消选择
                    $('.data-result span[data-code="' + $(con).data("code") + '"]').remove();
                    $(con).removeClass('d-item-active');
                    // 省份显示城市数量减一,当为0时不显示
                    var date_code=$(con).data("code");
                    while(typeof (jobDataArrary[date_code]) != "undefined"){
                        var super_date_code = jobDataArrary[date_code][4];
                        if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                            var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
                            if (parseInt(numlabel) == 1) {
                                $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(0).hide();
                            } else {
                                $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) - 1);
                            }
                        }
                        date_code = super_date_code;
                    }
                    return false;
                }

                var changeNum = 0;
                $('.data-result span').each(function () {
                    var date_code=$(con).data("code");
                    while(typeof (jobDataArrary[date_code]) != "undefined"){
                        var super_date_code = jobDataArrary[date_code][4];
                        if(super_date_code.length>0 && super_date_code.toString() == $(this).data("code").toString()){
                            changeNum++;
                        }
                        date_code = super_date_code;
                    }
                });

                if (changeNum == 0 && $('.data-result span').length >= selectNum) {
                    $('.data-error').text('最多只能选择 ' + selectNum + ' 项');
                    $('.data-error').slideDown();
                    setTimeout("$('.data-error').hide()", 3000);
                    return false;
                } else {
                    $('.data-result').append('<span class="svae_box aui-titlespan" data-code="' + $(con).data("code") + '" data-name="' + $(con).data("name") + '" onclick="removespan_job(this)">' + $(con).data("name") + '<i>×</i></span>');
                    $(con).addClass('d-item-active');
                }

                $('.data-result span').each(function () {
                    var date_code=$(con).data("code");
                    while(typeof (jobDataArrary[date_code]) != "undefined"){
                        var super_date_code = jobDataArrary[date_code][4];
                        if(super_date_code.length>0 && super_date_code.toString() == $(this).data("code").toString()){
                            $(this).remove();
                            $('.data-container-city a[data-code=' + $(this).data("code") + ']').removeClass("d-item-active");
                        }
                        date_code = super_date_code;
                    }
                });
            } else {
                //单选
                $('.data-result span').remove();
                // 消除搜索影响
                $('.data-list-hot li').siblings('li').find('a').removeClass('d-item-active');
                $('.data-container-city li').siblings('li').find('a').removeClass('d-item-active');

                $('.data-result').append('<span class="svae_box aui-titlespan" data-code="' + $(con).data("code") + '" data-name="' + $(con).data("name") + '" onclick="removespan_job(this)">' + $(con).data("name") + '<i>×</i></span>');
                $(con).parent('li').siblings('li').find('a').removeClass('d-item-active')
                $(con).addClass('d-item-active');

                var date_code=$(con).data("code");
                while(typeof (jobDataArrary[date_code]) != "undefined"){
                    var super_date_code = jobDataArrary[date_code][4];
                    if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                        $('.data-container-city a[data-code=' + super_date_code + ']').removeClass('d-item-active');
                        $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(0).hide();
                    }
                    date_code = super_date_code;
                }
            }
        }
        $('.data-result span').each(function () {
            var date_code=$(this).data("code");
            while(typeof (jobDataArrary[date_code]) != "undefined"){
                var super_date_code = jobDataArrary[date_code][4];
                if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                    $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(0).hide();
                }
                date_code = super_date_code;
            }
        });
        $('.data-result span').each(function () {
            if ($('a[data-code=' + $(this).data("code") + ']').length > 0) {
                $('a[data-code=' + $(this).data("code") + ']').addClass('d-item-active');
                if ($('a[data-code=' + $(this).data("code") + ']').attr("class").indexOf('data-all') > 0) {
                    if (datatype == "duoxuan") {
                        $('a[data-code=' + $(this).data("code") + ']').parent('li').nextAll('li').find('a').css({ 'color': '#ccc', 'cursor': 'not-allowed' });
                        $('a[data-code=' + $(this).data("code") + ']').parent('li').nextAll("li").find('a').attr("onclick", "");
                    }
                } else {
                    if (datatype == "danxuan") {
                        $('.data-list-provinces a').each(function () {
                            $(this).find('label').text(0).hide();
                        });
                    }
                    var date_code=$(this).data("code");
                    while(typeof (jobDataArrary[date_code]) != "undefined"){
                        var super_date_code = jobDataArrary[date_code][4];
                        if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                            var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
                            $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) + 1).show();
                        }
                        date_code = super_date_code;
                    }
                }
            } else {
                var date_code=$(this).data("code");
                while(typeof (jobDataArrary[date_code]) != "undefined"){
                    var super_date_code = jobDataArrary[date_code][4];
                    if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                        var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
                        $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) + 1).show();
                    }
                    date_code = super_date_code;
                }
            }
        });
    }
}

function selectitem_job(con) {
    if (datatype == "duoxuan") {
        var chanceNum = 0;
        $('.data-result span').each(function () {
            var date_code=$(this).data("code");
            while(typeof (jobDataArrary[date_code]) != "undefined"){
                var super_date_code = jobDataArrary[date_code][4];
                if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
                    if(super_date_code.toString() == $(con).data("code").toString()){
                        $(this).remove();
                        chanceNum++;
                    }
                }
                date_code = super_date_code;
            }
        });
        $(con).parent('li').siblings('li').find("a").removeClass("d-item-active");
        $(con).parent('li').siblings('li').find("a").find('label').text(0).hide();
        $(con).find('label').text(0).hide();

        if($(con).attr("class").indexOf('d-item-active') > 0){
            $(con).parent('li').siblings('li').find("a").removeClass("d-item-active");

            $(con).parent('li').nextAll("li").find('a').css({ 'color': '#0077b3', 'a.d-item-active:hover': '#fff', 'cursor': 'pointer' })
            $(con).parent('li').nextAll("li").find('a').attr("onclick", 'selectArea("sub",this,"")');

            $('.data-result span[data-code="' + $(con).data("code") + '"]').remove();
            $(con).removeClass('d-item-active');
            return false;
        }else{
            if(chanceNum == 0 && $('.data-result span').length >= selectNum) {
                $('.data-error').text('最多只能选择 ' + selectNum + ' 项');
                $('.data-error').slideDown();
                setTimeout("$('.data-error').hide()", 3000);
                return false;
            }

            $(con).parent('li').nextAll("li").find('a').css({ 'color': '#ccc', 'cursor': 'not-allowed' })
            $(con).parent('li').nextAll("li").find('a').attr("onclick", "");
            $('.data-result').append('<span class="svae_box aui-titlespan" data-code="' + $(con).data("code") + '" data-name="' + $(con).data("name") + '" onclick="removespan_job(this)">' + $(con).data("name") + '<i>×</i></span>');
            $(con).addClass('d-item-active');
        }
    } else {
        //单选
        $('.data-result span').remove();
        $('.data-result').append('<span class="svae_box aui-titlespan" data-code="' + $(con).data("code") + '" data-name="' + $(con).data("name") + '" onclick="removespan_job(this)">' + $(con).data("name") + '<i>×</i></span>');
        $(con).parent('li').siblings('li').find('a').removeClass('d-item-active')
        $(con).addClass('d-item-active');
        $(con).find('label').text(0).hide();
    }
}

function removenode_job(lithis) {
    $(lithis).siblings().removeClass('active');
    $(lithis).addClass('active');
    if ($(lithis).nextAll('li').length == 0) {
        return false;
    }
    $(lithis).nextAll('li').remove();
    if ($(lithis).data("selector") == "tab-all") {
        selectJob('all', null, '');
    } else {
        selectJob('sub', lithis, 'remove');
    }
}

function removespan_job(spanthis) {
    $('a[data-code=' + $(spanthis).data("code") + ']').removeClass('d-item-active');
    if ($('a[data-code=' + $(spanthis).data("code") + ']').length > 0) {
        if ($('a[data-code=' + $(spanthis).data("code") + ']').attr("class").indexOf('data-all') > 0) {
            $('a[data-code=' + $(spanthis).data("code") + ']').parent('li').nextAll('li').find('a').css({ 'color': '#0077b3', 'a.d-item-active:hover': '#fff', 'cursor': 'pointer' });
            $('a[data-code=' + $(spanthis).data("code") + ']').parent('li').nextAll("li").find('a').attr("onclick", 'selectJob("sub",this,"")');
        }
    }
    var date_code=$(spanthis).data("code");
    while(typeof (jobDataArrary[date_code]) != "undefined"){
        var super_date_code = jobDataArrary[date_code][4];
        if (super_date_code.length>0 && $('.data-container-city a[data-code=' + super_date_code + ']').length > 0) {
            var numlabel = $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text();
            if (parseInt(numlabel) == 1) {
                $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(0).hide();
            } else {
                $('.data-container-city a[data-code=' + super_date_code + ']').find('label').text(parseInt(numlabel) - 1);
            }
        }
        date_code = super_date_code;
    }
    $(spanthis).remove();
}

//确定选择
function saveJob() {
    var val = '';
    var Cityname = '';
    var result = true;
    if ($('.svae_box').length > 0) {
        $('.svae_box').each(function () {
            val += $(this).data("code") + ',';
            Cityname += $(this).data("name") + ' , ';
            if(isMinLevel){
                var relations  = __LocalAreaData.relations;
                //选中项有下级
                if (typeof (relations[$(this).data("code")]) != "undefined") {
                    result = false;
                }
            }
        });
    }
    if(result){
        if (val != '') {
            val = val.substring(0, val.lastIndexOf(','));
        }
        if (Cityname != '') {
            Cityname = Cityname.substring(0, Cityname.lastIndexOf(','));
        }

        $(selectId).val(val);
        $(selectText).val(Cityname);

        closeJob();
    }else{
        $('.data-error').text('请选择到最小级');
        $('.data-error').slideDown();
        setTimeout("$('.data-error').hide()", 3000);
    }
}

function closeJob() {
    $('.aui_state_box').remove();
}

function searchJobData() {
    var list    = __LocalJobData.list;
    var dataArr = [];
    for (var i in list) {
        var temp = {};
        temp.code   = i;
        temp.name   = list[i][0];
        temp.pinyin = list[i][1];
        temp.py     = list[i][2];
        dataArr.push(temp);
    }
    return dataArr;
}

var auto_job = {
    run: function () {// 运行应用
        var run = $('.data-search input[name=searcharea]'), runList = $('.searchList'), ac_menu = $('.searchList .area_menu');
        var def_text = '请搜索...';
        run.val(def_text);
        run.focus(function () {
            if (this.value == def_text) this.value = '';
        }).blur(function () {
            if (this.value == '') this.value = def_text;
            auto_job.delay(function () { runList.hide() }, 300);//延时，等待选择事件执行完成
        }).bind('keyup', function () {
            auto_job.appRunList(runList, run.val());
        }).keydown(function (e) {
            if (e.keyCode == 13) setTimeout(auto_job.appRunExec, 200);
        });
    },
    delay: function (f, t) {
        { if (typeof f != "function") return; var o = setTimeout(f, t); this.clear = function () { clearTimeout(o) } }
    },
    appRunList: function (runList, v) {//自动搜索应用
        if (v == '') {
            runList.hide();
            return;
        }
        var i, temp = '', n = 0, loaded = {};
        //搜索以关键词开头的应用
        for (i in searchJobValue) {
            if (isNaN(i) || loaded[i] || !searchJobValue[i].name) {
                continue;
            }
            runSearchCode = searchJobValue[i].code
            runSearchName = searchJobValue[i].name;
            runSearchPinyin = searchJobValue[i].pinyin;
            runSearchPy = searchJobValue[i].py;
            if (runSearchName.indexOf(v) >= 0 || runSearchPinyin.indexOf(v) >= 0 || runSearchPy.indexOf(v) >= 0 || runSearchPinyin.toLowerCase().indexOf(v) >= 0 || runSearchPy.toLowerCase().indexOf(v) >= 0) {
                loaded[i] = 1;
                temp += '<a class="area_menu" href="javascript:;" data-flag=1 data-code="' + runSearchCode + '" data-name="' + runSearchName + '" onclick="selectJob(\'sub\',this,\'\')"><em>' + runSearchPinyin.replace(v, "<b>" + v + "</b>") + '</em>' + runSearchName.replace(v, "<b>" + v + "</b>") + '</a>';
                if (++n > 10) break;
            }
        }
        if (temp) {// 搜索到应用则显示
            runList.show().html(temp);
        } else {
            runList.hide().html('');
        }
    },

    appRunExec: function () {// 运行按纽点击
        ac_menu = $('.searchList .area_menu');
        if (ac_menu.length > 0) {
            ac_menu.eq(0).trigger('click');
        }
    },
};