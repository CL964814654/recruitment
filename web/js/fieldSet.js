/**
 * 定义字段集合、字段格式化和字段设置相关数据和函数
 *
 */

//----------------------------------------------------------------数据集合-----------------------

var items_welfare = [
    {value:1,text:"五险一金"},
    {value:2,text:"包住"},
    {value:4,text:"包吃"},
    {value:8,text:"周末双休"},
    {value:16,text:"交通补助"},
    {value:32,text:"加班补助"},
    {value:64,text:"餐补"},
    {value:128,text:"话补"},
    {value:256,text:"房补"},
    {value:512,text:"交通补助"}
];

var items_degree = [
    {value:0,text:"高中以下"},
    {value:1,text:"高中"},
    {value:2,text:"中专"},
    {value:3,text:"大专"},
    {value:4,text:"本科"},
    {value:5,text:"硕士"},
    {value:6,text:"博士"}
];

var items_degree_limit = [{value:-1,text:"不限"}].concat(items_welfare);

//----------------------------------------------------------------字段集合-----------------------
//简历字段集合
var columns_curriculum = [[
    {fixed: 'left', type: 'checkbox'},
    {field:'name', title: '姓名',width:100},
    {field:'sex', title: '性别',width:100},
    {field:'birthday', title: '出生年月',width:130},
    {field:'degree', title: '学历',width:100},
    {field:'phone', title: '电话',width:130},
    {field:'positions', title: '期望职位'},
    {field:'salary', title: '期望薪资',width:130},
    {fixed: 'right', title: '操作', toolbar: '#bar_curriculum',width:300}
]];

//求职申请字段集合
var columns_app = [[
    {fixed: 'left', type: 'checkbox'},
    {field:'candidate', title: '求职者'},
    {field:'title', title: '投递职位'},
    {field:'salary', title: '期望薪资',width:130},
    {field:'phone', title: '联系电话',width:130},
    {field:'status', title: '状态',width:100},
    {fixed: 'right', title: '操作', toolbar: '#bar_app',width:300}
]];

//职位字段集合
var columns_job = [[
    {fixed: 'left', type: 'checkbox'},
    {field:'title', title: '标题'},
    {field:'category', title: '职位类别',width:180},
    {field:'salary', title: '薪资',width:110},
    {field:'amount', title: '招聘人数',width:90},
    {field:'modtime', title: '刷新日期',width:100},
    {field:'recruit', title: '招聘情况',width:300},
    {fixed: 'right', title: '操作', toolbar: '#bar_job',width:350}
]];

//面试字段集合
var columns_interview = [[
    {fixed: 'left', type: 'checkbox'},
    {field:'candidate', title: '求职者',width:200},
    {field:'title', title: '投递职位',width:180},
    {field:'phone', title: '联系电话',width:120},
    {field:'time', title: '面试时间',width:140},
    {field:'address', title: '面试地点'},
    {field:'status', title: '状态',width:70},
    {fixed: 'right', title: '操作', toolbar: '#bar_interview',width:330}
]];

//企业推荐字段集合
var columns_recommend = [[
    {field:'start', title: '起始日期',width:140},
    {field:'days', title: '推荐天数',width:100},
    {field:'status', title: '状态',width:100}
]];

//积分获取记录
var columns_income = [[
    {field:'comment', title: '事项'},
    {field:'quantity', title: '获取积分',width:200},
    {field:'date', title: '日期',width:200}
]];

//积分消费记录
var columns_consume = [[
    {field:'comment', title: '事项'},
    {field:'quantity', title: '使用积分',width:200},
    {field:'date', title: '日期',width:200}
]];

//----------------------------------------------------------------字段格式化-----------------------
/**
 *  成绩格式化函数
 * @param value 类型值
 * @param row 当前行记录
 * @param index 行号
 * @returns {string} 成绩字符串
 */
function format_welfare(value) {
    var str = "";
    for(var i in items_welfare){
        var item = items_welfare[i];
        if(item.value & value){
            if(str.length!=0){
                str += "|";
            }
            str += item.text;
        }
    }
    return str;
}

function format_sex(value){
    switch (value){
        case 0: return "男";
        case 1: return "女";
    }
}

function format_degree(value){
    switch (value){
        case 0: return "高中以下";
        case 1: return "高中";
        case 2: return "中专";
        case 3: return "大专";
        case 4: return "本科";
        case 5: return "硕士";
        case 6: return "博士";
    }
}





//----------------------------------------------------------------字段设置相关-----------------------


/**
 * 根据字段值提取相应的字段
 * @param columns 所有的字段
 * @param value 字段值，相应的二进制位为1表示有该字段
 * @returns {[field]} 字段集合，形如[[field1,field2……]]。
 */
function getColumns(columns, value) {
    var result = [];
    for (var i in columns){
        if((value & Math.pow(2,i))!=0){
            result.push(columns[i]);
        }
    }
    return [result];
}


