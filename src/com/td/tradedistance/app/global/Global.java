package com.td.tradedistance.app.global;

import com.td.tradedistance.app.R;






/**
 * <p>功能描述:全局类 </p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class Global {
	/**
	 * 租户系统ID
	 */
	public static String XT_ID ="10036";
	/**
	 * 终端系统类型(android)
	 */
	public static final String ZDXTLX = "2";
	
	/**
	 * 缓存密码
	 */
	public static String password;
	/**
	 * 用户类型(学员)
	 */
	public static final String YHLX = "3";
	/**
	 * DES key 密钥
	 */
	public static final String KEY = "DwQ6e_4k";//"Q2NzqsZE";
	/**
	 * 用户名
	 */
	public static final String USERNAME = "BAP120900016  ";//tt002/tt002 tt005/tt005 tt006/tt006
	
	
	/**
	 * 密码
	 */
	public static final String PASSWORD = "1";
	
	/**
	 * EDS Model
	 */
	public static final String DES_MODEL = "DES/ECB/PKCS5Padding";
	
	/**
	 * 用户标识
	 */
	public static String YongHuBiaoShi= "YongHuBiaoShi";//加密的
	/**
	 * 用户标识
	 */
	public static String NODecYongHuBiaoShi= "NODecYongHuBiaoShi";//没加密的
	/**
	 * 授权码
	 */
	public static String ShouQuanMa = "ShouQuanMa";
	/**
	 * 姓名
	 */
	public static String XingMing = "XingMing";
	/**
	 * 身份证
	 */
	public static String ShenFenZheng = "ShenFenZheng";
	/**
	 * 积分
	 */
	public static String JiFen = "JiFen";
	public static String Guide = "Guide";
	public static String touxiang = "touxiang.png";
	/**
	 * 终端绑定标识名称
	 */
	public static final String ZhongDuanBangDingKey = "zdbd";
	public static final String ZhongDuanBangDingValue = "1";
	/**
	 * Ret 执行状态
	 */
	public static final int Success = 0;
	public static final String show_success = "成功";
	public static final String login_success = "登陆成功";
	public static final String cancle_success = "注销成功";
	public static final String show_failed = "绑定失败,请检查网络";//绑定失败
	/**
	 * 终端推送码更新成功
	 */
	public static final int push_success = 100;
	public static final String show_push_success = "终端推送码更新成功";
	
	/**
	 * 该信息或文件不存在
	 */
	public static final int no_file = -110;
	public static final String no_file_info = "不存在";
	/**
	 * 表示没有该信息下载权限
	 */
	public static final int no_quan_xian = -109;
	public static final String no_quan_xian_info = "没有该信息下载权限";
	/**
	 * 表示接收人未绑定终端推送码
	 */
	public static final int no_banding_zdtsm = -111;
	public static final String no_bangding_zdtsm_info = "接收人未绑定终端推送码";
	/**
	 * 表示接收人注销登陆（即离线）
	 */
	public static final int no_lixiang= -112;
	public static final String no_lixiang_info = "对方已注销,接收不到此信息!";
	
	/**
	 * 解密失败
	 */
	public static final int dec_failed = -101;
	public static final String show_dec_failed ="用户名或密码错误，请再试一次";//"解密失败";
	/**
	 * 参数错误或必选参数没传
	 */
	public static final int param_error = -102;
	public static final String show_param_error = "网络出错";//"参数错误或必选参数没传";
	/**
	 * 系统不存在或已过期
	 */
	public static final int sys_no_exist = -103;
	public static final String show_sys_no_exist ="您的账户已到期，请和人力资源部门联系" ;//"系统不存在或已过期";
	/**
	 * 执行出错
	 */
	public static final int execute_error = -104;
	public static final String show_execute_error = "读取数据失败，请刷新重新获取数据";//"执行出错";
	/**
	 * 用户或密码错误
	 */
	public static final int user_or_pass_error = -105;
	public static final String show_user_or_pass_error = "用户名或密码错误，忘记密码了？请联系客服!";
	/**
	 * 终端识别码不存在
	 */
	public static final int android_imei_no_exist = -107;
	public static final String show_android_imei_no_exist = "终端识别码不存在";
	/**
	 * 错误授权码
	 */
	public static final int error_sqm = -106;
	public static final String show_error_sqm = "用户已存在,请重新登录";//错误授权码
	/**
	 * 手机号已注册过
	 */
	public static final int sjhyzc = -300;
	public static final String show_sjhyzc = "手机号已注册";
	/**
	 * 发送验证码失败
	 */
	public static final int fsyzmsb = -299;
	public static final String show_fsyzmsb = "发送验证码失败";
	/**
	 * 无数据提示
	 */
	public static final int noData = 110;
	public static final String show_no_data = "无数据";//
	/**
	 * 账号未开放或账号已过期
	 */
	public static final int account_no_open = -108;
	public static final String show_account_no_open = "账号可能过期，请联系客服!";
	/**
	 * 注销成功
	 */
	public static final String log_off_success = "注销成功";
	/**
	 * 注销失败
	 */
	public static final String log_off_failed= "注销失败";
	
	
	/**
	 * 强制更新
	 */
	public static final  String qzgx = "1";
	/**
	 * 不强制更新
	 */
	public static final  String noqzgx = "0";
	/**
	 * 机构ID
	 */
	public static final  String jgid = "0";
	/**
	 * zlmid
	 */
	public static final  String zlmid = "0";
	/**
	 * 获取条数
	 */
	public static final  String hqts = "20";
	/**
	 * 获取条数
	 */
	public static final  String hqts10 = "10";
	/**
	 * 首页图获取条数
	 */
	public static final  String tphqts = "4";
	/**
	 * 获取推荐
	 */
	public static final String hqtj = "1";
	
	
	
	/**
	 * RGB颜色
	 */
	public static final String rgbys = "0";
	
	/**
	 * 用户名必填提示
	 */
	public static final String UserNameTip = "用户名必填";
	/**
	 * 用户名必填提示
	 */
	public static final String NameTip = "姓名必填";
	/**
	 * 身份证必填提示
	 */
	public static final String SFZTip = "身份证必填";
	/**
	 * 性别
	 */
	public static final String XBTip = "性别必填";
	/**
	 *提示
	 */
	public static final String PasswordTip = "密码必填";
	/**
	 * 请输入当前密码提示
	 */
	public static final String currPasswordTip = "请输入当前密码";
	/**
	 * 请输入新密码提示
	 */
	public static final String newPasswordTip = "请输入新密码";
	
	/**
	 * 确认新密码提示
	 */
	public static final String confirmPasswordTip = "请输入确认新密码";
	/**
	 * 新密码 和确认新密码不正确
	 */
	public static final String isConfirmTip = "新密码 和确认新密码不一致";
	/**
	 * 旧密码不正确
	 */
	public static final String oldPasswordTip = "当前输入旧密码不正确";
	/**
	 * 无网络提示
	 */
	public static final String NoNetWorkTip = "无法连接到网络，请检查网络配置";
	/**
	 * 开放报名
	 */
	public static final String IsKaiFangBaoMing1 = "1";
	/**
	 * 不开放报名
	 */
	public static final String IsKaiFangBaoMing0 = "0";
	/**
	 * 电话不能为空
	 */
	public static final String BaoMingPhone = "电话不能为空";
	/**
	 * 姓名不能为空
	 */
	public static final String BaoMingName = "姓名不能为空";
	
	/**
	 * 秒
	 */
	public static final int SetMiao = 5000;
	/**
	 * nosdcard提示
	 */
	public static final String noSDTip = "SD卡不存在,请检查SD卡！";
	
	/**
	 * epub格式
	 */
	public static final String epubType = "epub";
	/**
	 * pdf格式
	 */
	public static final String pdfType = "pdf";
	
	/**
	 * "下载失败!"
	 */
	public static final String Downfailed = "下载失败";
	/**
	 * 没有文件下载
	 */
	public static final String noFile = "没有文件下载";
	
	/**
	 * 我的分享
	 */
	public static final String MyShare = "wdfx";
	/**
	 * 是否保存密码
	 */
	public static final String IsSavePassWordValue = "";
	/**
	 * 是否自动登陆
	 */
	public static final  String IsLoginValue = "true";
	/**
	 * 是否记住密码
	 */
	public static final  String IsPassValue = "true";
	
	/**
	 * 是否保存密码 key
	 */
	public static final String PassWordKey = "passWord";
	
	/**
	 * 是否保存密码 key1
	 */
	public static final String PassWordKey1 = "passWord1";
	/**
	 * 是否自动登陆 key
	 */
	public static final  String LoginKey = "login";
	/**
	 * 是否记住密码 key
	 */
	public static final  String passKey = "pass";
	/**
	 * 是否显示用户名和密码 true 显示  false 不显示
	 */
	public static final  String flag = "true";
	/**
	 * 退出登陆时显示自动保存用户名和密码标记
	 */
	public static final String ShowNameAndPass = "ShowNameAndPass";
	/**
	 * 是否保存用户名 key
	 */
	public static final  String UserNameKey = "username";
	
	
	/**
	 * 无数据提示
	 */
	public static final String no_data = "无数据";
	
	/**
	 * 0 表示更新版本
	 */
	public static final String update_version = "0";
	/**
	 * 1 表示必须更新版本
	 */
	public static final String must_update_version = "1";
	/**
	 * 开始更新
	 */
	public static final int PARSE_SUCCESS = 0;
	/**
	 * 更新成功
	 */
	public static final int DOWNLOAD_SUCCESS = 1;
	/**
	 * 更新失败
	 */
	public static final int DOWNLOAD_ERROR = 2;
	/**
	 * 下载文件失败
	 */
	public static final String download_failed = "下载文件失败";
	/*****************************************************/
	/**
	 * 设备类型
	 */
	public static final String deviceType = "android";
	/**
	 * 账号未开放或账号已过期
	 */
	public static final String dlhdlm = "2";
	
	/**
	 * 是否有下载文件
	 */
	public static boolean isDownLoad = false;
	
	
	public static final String  mp4 = "mp4";
	public static final String mp3 = "mp3";
	public static final String pdf = "pdf";
	public static final String epub = "epub";
	public static final String zip = "zip";
	public static final String html = "html";
	
	
	/**
	 * home lanmu json
	 */
	public static final String LanMuJson = "LanMuJson";
	/**
	 * Info_libActivity  zsjs
	 */
	public static final String zlkzlm = "ZlkzlmJson";
	/**
	 * FocousActivity  jifen
	 */
	public static final String FengHuoLangYanTuPian = "FengHuoLangYanTuPian";
	/**
	 * 终端已绑定
	 */
	public static final boolean YiBangDing = true;
	/**
	 * 终端没绑定
	 */
	public static final boolean MeiBangDing = false;
	/**
	 * 考试结束提示
	 */
	public static final String tishi = "考试结束请提交";
	
	/**
	 * 我的提问key
	 */
	public static final String WdtwKey = "WdtwKey";
	
	/**
	 * 我的回答key
	 */
	public static final String WdhdKey = "WdhdKey";
	
	/**
	 * 我的收藏key
	 */
	public static final String WdscKey = "WdscKey";
	/**
	 * 课程中心分类key
	 */
	public static final String FlKey = "FlKey";
	
	/**
	 * 网络提示
	 */
	public static final String networdtip = "网络有些不给力，请重新再试一下！";
	/**
	 * 
	 */
	public static final String repeatTip = "不能重复添加已有数据";
	/**
	 * 字数多余100个提示
	 */
	public static final String limit = "字数多余100个请删减！";
	
	/**
	 * 资料库 全部选项名称
	 */
	public static final String quanbu = "全部";
	
	/**
	 * 文件加密
	 */
	public static final String WJJMKey = "WJJMC";
	/**
	 * 统一系统id加版本号
	 */
	public static final String TYXTBBHKey = "TYXTBBH";
	/**
	 * 文件授权提示
	 */
	public static final String FileAuthorizationTip = "正在打开，请稍候..." ;//"您访问的资源受到安全保护，正在进行授权认证，请稍候……";
	/**
	 * sd卡剩余空间不足提示
	 */
	public static final String RemainingSpace = "可用存储空间不足，请清理出一些存储空间后，再次尝试访问此资源。";
	/**
	 * 
	 */
	public static final String fileDeleted = "该文件不存在，请删除该文件记录重新下载";
	/**
	 * sd卡剩余空间不足提示
	 */
	public static final String FileAuthorizationFailureTip = "文件授权检查失败，请删除该文件重新下载";
	
	/**
	 * .log
	 */
	public static final String log = "log.txt";
	
	/**
	 * 报名信息已存在提示
	 */
	public static final int name_exist = 100;
	public static final String show_name_exist = "您已报名,如果代替别人报名,请重新填写相应的信息";
	/**
	 * 报名成功提示
	 */
	public static final String baoming_success = "报名成功";
	public static final String dianzang_success = "点赞成功";
	public static final String quxiaodianzang_success = "取消点赞";
	public static final String dianzang_failed = "点赞失败";
	/**
	 * 收藏成功提示
	 */
	public static final String shoucang_success = "收藏成功";
	/**
	 * 收藏成功提示
	 */
	public static final String shoucang_quxiao = "取消收藏";
	/**
	 * 收藏失败提示
	 */
	public static final String shoucang_failed = "失败";
	/**
	 * 报名失败提示
	 */
	public static final String baoming_failed = "报名失败,请重试";
	/**
	 * 评论提示
	 */
	public static final String pinglun_success = "评论成功";
	/**
	 * 评论提示
	 */
	public static final String pinglun_failed = "评论失败";
	/**
	 * 未填写报名信息提示
	 */
	public static final String no_name = "报名人姓名和电话不能为空";
	public static final String modify_failed = "修改失败";
	public static final String modify_success = "修改成功";
	/**
	 * WIFI网络未连接提示
	 */
	public static final String no_WIFI = "未检测到通畅的网络链接,建议断开后重新链接";
	/**
	 * 3G网络未连接提示
	 */
	public static final String no_3G = "未检测到通畅的网络链接,建议换一个信号更好的位置再次尝试";
	/**
	 * 电话号码正确性提示
	 */
	public static final String phone_num_show = "请输入有效的电话号码";
	
	
	public static String w2G3GTip ="您正在使用移动数据网络，可能会大量使用流量，确定下载吗？" ;
	
	/**
	 * 烽火狼烟
	 *//*
	public static final String fhly = "fhly";
	*//**
	 * 实战教学
	 *//*
	public static final String szjx = "szjx";
	*//**
	 * 知识高
	 *//*
	public static final String zsgd = "zsgd";
	*//**
	 * 行动集结
	 *//*
	public static final String xdjj = "hdjj";*/
	/**
	 * listview 刷新时间key
	 */
	public static final String listviewTimeKey = "timeKey";
	
	public static final String hdbm = "活动报名";
	public static final String hdhg = "活动回顾";
	public static final String zxpl = "最新评论";
	public static final String jpld = "金牌领队";
	
	public static final String xdjj = "行动集结";
	public static final String fhly = "烽火狼烟";
	public static final String zsgd = "知识高地";
	public static final String szjx = "实战教学";
	public static final String txz = "通讯站";
	public static final String qb = "全部";
	public static final String ss = "搜索";
	/**
	 *   都市避险 =163
	 */
	public static final int dsbx = 163;
	/**
	 * 人祸逃生=165
	 */
	public static final int rhts = 165;
	/**
	 * 天灾自救=164
	 */
	public static final int tzzj = 164;
	/**
	 * 野外生存=162
	 */
	public static final int ywsc = 162;          
			
    public static final String yhq = "优惠券";
    public static final String gg = "公告";
    public static final String xx = "消息";	
	public static final int tupian_width = 60;
	public static final int tupian_heidth = 60;
	public static final int tupian_width80 = 80;
	public static final int tupian_heidth80 = 80;
	public static final int tupian_width100 = 100;
	public static final int tupian_heidth100 = 100;
	public static final String MP4 = "mp4";
	public static final String HTML = "html";
	/**
	 * 微信平台
	 */
    public static final String WXAppID = "wx65cf9e03d40c261e";
	public static final String WXAppSecret = "d4624c36b6795d1d99dcf0547af5443d";
	/**
	 * qq空间 
	 */
	public static final String QQAppID = "1104916307";
	public static final String QQAppSecret = "RlP9CP3XLfQu6bd7";
	/**
	 * 新浪微博
	 */
	public static final String SLAppID = "164809523";
	public static final String SLAppSecret = "2621d8b380e2f00d71f13dbdfcef62be";
	

	/**
	 * 
	 * 极光推送:客户端推送秘钥是
	 */
	public static final String jpushId = "49cfae7c";
	
	public static final String wtx = "未填写";
	public static final String nan = "男";
	public static final String nv = "女";
	public static final String pass_success = "修改密码成功";
	public static final String pass_fail= "修改密码失败";
	public static final String mobil= "手机";
	public static final String mail= "邮箱";
	public static final String fenzhi = "分值";
	public static final String keshiyong = "可使用";
	public static final String xianyou = "，现有";
	public static final int gonggao = 177;
	public static final int xiaoxi = 178;
	public static final int yiuhuima = 179;
	
	public static final String yhmje = "元优惠码，快来领取";
	public static final String dfm = "兑换码：";
	public static final String fuhao = "￥";
	public static final String fushu = "-￥";
	public static final String KeCheng = "KeCheng";
	public static final String HuoDongBaoMing = "HuoDongBaoMing";
	public static final String sstip = "未找到相关结果，建议扩大搜索范围";
	public static final String ssfind = "找到相关搜索";
	public static final String tiao = "条";
	public static final String yidianzhan = "已点赞";
	public static final String yiuhuiquanstr = "优惠券";
	public static final String hdbmid = "170";
	public static final String alipaySuccess = "success";
	public static final String alipaySuccesstip = "付款成功！";
	public static final String alipayFail = "fail";
	public static final String alipayFailTip = "付款失败！";
	public static final String alipayCancel = "cancel";
	public static final String alipayCancelTip = "取消付款！";
	public static final String birthday = "出生年月";
	public static final String uuid = "uuid";
	public static final String reg = "reg";
	public static final String guanggao = "guanggao";
	public static final String guanggaoTuPian = "GuangGaoTuPian";
	public static final String guanggaolianjie = "GuangGaoLianJie";
	public static final String wsgrxx = "请完善个人信息";
	public static final String kc_wddd = "wddd";
	public static final String kc_hdbm = "hdbm";
	public static final String kc_szzj = "szzj";
	public static final String kc_szjx_hdbm = "szjx_hdbm";
	
	////340304 13910631974
	
	/**
	 * 学习中心
	 */
	public static final int waitIcon = R.drawable.wait;
	public static final int downIcon = R.drawable.down;
	public static final int downedIcon = R.drawable.downed;
	public static final int stopIcon = R.drawable.stop;
	public static final int downingIcon = R.drawable.downing;
	public static final int wait = 0;
	public static final int down = 1;
	public static final int downing = 2;
	public static final int stop = 3;
	public static final int downed = 4;
	/**
	 * XueWeiKe		-是否是学位课（1-是学位课，0-非学位课）
	 */
	public static final String xueweike= "XueWeiKe";
}


