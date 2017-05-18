package com.sfxie.utils;

/**
 * User: wquan
 * Date: 13-6-24
 * Time: 下午1:32
 */
public class Constants {

    public static String System_Root_Url = "";//系统根路径URL
    public static String System_Unknow_Exception = "1001";
//    public static String Img_Path = ConfigUtil.getProperty("img.path");
    public static short No_State = 0;
    public static short Yes_State = 1;

    public static short Wait_Pay = 0; //等待支付
    public static short Pay_Success = 1;//支付成功
    public static short Stock_NotEnough = 2;//库存不足
    public static short Gold_NotEnough = 3; //余额不足
    public static short Pay_Fail = 4; //支付失败

    public static short GetGold_ActionType_Watch = 1;//赢金币
    public static short GetGold_ActionType_Answer = 2;//点金答
    public static short GetGold_ActionType_FirstHp = 3;//首次进入零花钱
    public static short GetGold_ActionType_FirstYjb = 4;//首次进入赢金币
    public static short GetGold_ActionType_FirstDjd = 5;//首次进入点金答
    public static short GetGold_ActionType_FirstZjk = 6;//首次进入砸金库
    public static short GetGold_ActionType_INDEX = 7;//首页
    public static short GetGold_ActionType_ZJK = 8;//砸金库
    public static short GetGold_ActionType_UpdateUserData = 9;//完善用户资料
    public static short GetGold_ActionType_Sign=10;//签到
    public static short GetGold_ActionType_WxSign=11;//微信签到
    public static short GetGold_ActionType_MakeMoney=12;// 赚钱

    public static int Zjk_select_rules1 = 1;//表示广告池中的随机抽取4个
    public static int Zjk_select_rules2 = 2;//表示按4档金币数量随机


    public static String Zjk_index_tip = "砸开宝箱每次最高可获得H#元，最少也可获得L#元";  //砸金库首页提示
    public static String Zjk_index_highlightext = "$,H#,L#";  //砸金库首页提示 高亮文本
    public static String Zjk_idex_tip_en = "Every time break treasure box maximum get H# RMB, \nminimum could receive at least L# RMB";

    public static String Zjk_index_tip2 = "恭喜您上一个锤子砸出$元，继续砸开宝箱每次最高可获得H#元，最少也可获得L#元";  //砸金库首页提示
    public static String Zjk_idex_tip2_en = "Congratulations to you one hammer out $ RMB, continue to hit treasure box, \nyou could maximum get H# each time, minimum could receive at least L# RMB";

    public static String Currency_unit_zh = "元";
    public static String Currency_unit_en = "RMB";
    public static String Currency_char_zh = "¥";
    public static String Currency_char_en = "¥";

    public static int Order_Address_No = 0;   //未发货

    public static short Login_Action_type = 1;//登录
    public static short Logout_Action_type = 2;//登出

    public static String loginpage = "需更换手机帐号，请拨打客服热线：";
    public static String rechargepage = "010-67330258";
    public static String exchangetip = "请先登录用户中心，否则无法兑换";
    public static String switchusertip = "您的账号已经切换至$$，请注意！";

    public static String AD_SYSCONFIG_LOCALCACHE_KEY = "AD_SYSCONFIG_LOCALCACHE_KEY";
    public static String AD_USER_TOKEN_LOCALCACHE_KEY = "AD_USER_TOKEN_LOCALCACHE_KEY";
    public static String AD_DYNAMIC_PWD_KEY = "AD_DYNAMIC_PWD_KEY";
    public static String AD_ADS_MAP_LOCALCACHE_KEY = "AD_ADS_MAP_LOCALCACHE_KEY";
    public static String AD_ADS_MAP_LOCALCACHE_KEY_API3 = "AD_ADS_MAP_LOCALCACHE_KEY_API3";
    public static String AD_RESOURCEURL_LIST_LOCALCACHE_KEY = "AD_RESOURCEURL_LIST_LOCALCACHE_KEY";
    public static  String AD_USERSIGN_FLAG_CACHE_KEY="AD_USERSIGN_FLAG_CACHE_KEY_"; //用户签到标志缓存key
    public static  String AD_REWARD_SUM_CACHE_KEY="AD_REWARD_SUM_CACHE_KEY"; //用户奖励总计
    public static  String AD_CONSUME_SUM_CACHE_KEY="AD_CONSUME_SUM_CACHE_KEY"; //用户消费总计
    public static  String AD_GOODSTYPE_CACHE_KEY="AD_GOODSTYPE_CACHE_KEY"; //商品类型缓存key
    public static  String AD_USERGUIDE_CACHE_KEY="AD_USERGUIDE_CACHE_KEY"; //用户向导缓存key

    public static  String WX_USERSIGN_FLAG_CACHE_KEY="WX_USERSIGN_FLAG_CACHE_KEY_"; //微信用户签到标志缓存key



    public static String EXCEPTION_REQUEST_PARAM_MISS="缺少请求参数";
    public static String EXCEPTION_REQUEST_PARAM_FORMAT_ERROR="请求xml参数格式错误";
    public static String EXCEPTION_SERVICE_DATA_ERROR="业务数据处理错误";

    public static  String XJQ_USER_RULE="现金券使用规则\n" +
            "1.登录www.benlai.com或手机下载本来生活APP（可在各大应用商店下载），注册成为会员或使用已有账户登陆，选择您所在的城市；\n" +
            "2.购物结算时，在使用优惠券处输入您获得的优惠券密码，即可抵扣15元费用（全网满60元包邮）；\n" +
            "注意事项\n" +
            "1.优惠券有效期：2015年06月01日-06月30日，请及时使用；\n" +
            "2.优惠券全国范围均可使用，部分偏远地区不抵达，具体配送详情可登录网站查询。\n" +
            "3.如有疑问请致电客服电话：4008-000-917；\n" +
            "4.本活动最终解释权在法律范围内归本来生活网所有。";

    public static String HZQ_USE_RULE="红枣券使用规则\n" +
            "1.扫描右边二维码手机下载本来生活APP（可在各大应用商店下载），注册成为会员或使用已有账户登陆，选择您所在的城市；\n" +
            "2.进入兑换专区， 在兑换专区中选择【兑换专用】和田枣加入购物车，继续挑选其他优质好食材（订单总金额满60元包邮）；\n" +
            "3.结算时，在使用优惠券处输入您获得的优惠券密码，即可抵扣相关食材费用；\n" +
            "注意事项\n" +
            "1.优惠券仅限移动端兑换，有效期：2015年06月01日-06月30日，请及时使用；\n" +
            "2.本红枣券每个客户限用一张，使用范围仅限八个城市，分别为广州、深圳、惠州、中山、珠海、东莞、佛山、江门；\n" +
            "3.如有疑问请致电客服电话：4008-000-917；\n" +
            "4.本活动最终解释权在法律范围内归本来生活网所有。";

    public static String EXCHANGE_CHECK_TIP2="很抱歉，您的余额不足！";
    public  static  String EXCHANGE_CHECK_TIP3="很抱歉，商品库存不足";
    public static String EXCHANGE_CHECK_TIP5="今天快递小哥都被累趴了，亲，明天再来兑换吧！";
    public static String EXCHANGE_CHECK_TIP4="未手机登录";
    public static String EXCHANGE_CHECK_TIP1="正常兑换";

    public static String FlAG_PVLOG_ID="pv_log"; //pv_log分表ID
    public static String FlAG_ANSWERLOG_ID="answer_log";//answer_log分表ID
    public static String FlAG_GETGOLDLOG_ID="getgold_log";//getgold_log分表ID

    public static String USER_REGISTER_BY_PHONE="user_register_by_phone"; //手机登陆缓存后缀
    
    /**	lhq3商品列表缓存key	*/
    public static String LHQ3_MEMCACHED_LIST_GOODS = "AD3_MEMCACHED_LIST_GOODS";
    /**	lhq3主配置缓存key	*/
    public static String LHQ3_main_config_url = "LHQ3_main_config_url";
    /**	lhq3系统配置缓存key*/
    public static String LHQ3_SYSCONFIG_LOCALCACHE_KEY = "LHQ3_SYSCONFIG_LOCALCACHE_KEY";
    
    /**操作钱包reson定义**/
    public static String OPERATE_wALLET_REASON_LUCKDRAW_IN = "撞大运抽奖_奖励";
    public static String OPERATE_wALLET_REASON_LUCKDRAW_OUT = "撞大运抽奖_消费";
    public static String OPERATE_wALLET_REASON_SIGN = "签到";
    public static String OPERATE_WALLET_REASON_ACHIEVEMENT_REWARD = "领取成就：";
    /**	看广告送现金_{0}_{1} */
    public static String OPERATE_wALLET_REASON_WatchLog = "看广告送现金_{0}_{1}";
    /**	广告问答送现金_{0}_{1} */
    public static String OPERATE_wALLET_REASON_AnswerLog = "广告问答送现金_{0}_{1}";
    /**	订单抵扣金额_{0}_{1} */
    public static String OPERATE_wALLET_REASON_CreatedOrder = "订单抵扣金额[用户:{0}_金额:{1}]";

}
