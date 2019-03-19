package com.swf.attence.hikConfig;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

import java.io.*;

public class CDFLibBox {
    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    public NativeLong lUserID;//用户句柄
    public NativeLong lAlarmHandle;//报警布防句柄
    public NativeLong lListenHandle;//报警监听句柄
    public NativeLong RemoteConfig;
    public FMSGCallBack fMSFCallBack;//报警回调函数实现
    public FMSGCallBack_V31 fMSFCallBack_V31;//报警回调函数实现
    public FRemoteCfgCallBackCardGet fRemoteCfgCallBackCardGet;
    public FRemoteCfgCallBackCardSet fRemoteCfgCallBackCardSet;
    public FRemoteCfgCallBackFaceGet fRemoteCfgCallBackFaceGet;
    public FRemoteCfgCallBackFaceSet fRemoteCfgCallBackFaceSet;
    public String m_sDeviceIP;//已登录设备的IP地址
    public String username; //设备用户名
    public String password;//设备登录密码
    public int iPort;//设备端口号

    /**
     * 获取卡号及人脸数据
     */
    public void getFaceCfg() {
        int iErr = 0;
        HCNetSDK.NET_DVR_FACE_PARAM_COND m_struFaceInputParam = new HCNetSDK.NET_DVR_FACE_PARAM_COND();
        m_struFaceInputParam.dwSize = m_struFaceInputParam.size();
        m_struFaceInputParam.byCardNo = "66666666".getBytes(); //人脸关联的卡号
        m_struFaceInputParam.byEnableCardReader[0] = 1;
        m_struFaceInputParam.dwFaceNum = 1;
        m_struFaceInputParam.byFaceID = 1;
        m_struFaceInputParam.write();
        Pointer lpInBuffer = m_struFaceInputParam.getPointer();
        Pointer pUserData = null;
        fRemoteCfgCallBackFaceGet = new FRemoteCfgCallBackFaceGet();
        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_FACE_PARAM_CFG, lpInBuffer, m_struFaceInputParam.size(), fRemoteCfgCallBackFaceGet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("建立获取卡参数长连接成功!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开长连接成功!");
    }

    /**
     * 下发卡号 姓名 工号
     * @param cardName
     * @param userid
     * @throws UnsupportedEncodingException
     */
    public void setCardInfo(String cardName,String userid) throws UnsupportedEncodingException {
        int iErr = 0;
        //设置卡参数
        HCNetSDK.NET_DVR_CARD_CFG_COND m_struCardInputParamSet = new HCNetSDK.NET_DVR_CARD_CFG_COND();
        m_struCardInputParamSet.read();
        m_struCardInputParamSet.dwSize = m_struCardInputParamSet.size();
        m_struCardInputParamSet.dwCardNum = 1;
        m_struCardInputParamSet.byCheckCardNo = 1;
        Pointer lpInBuffer = m_struCardInputParamSet.getPointer();
        m_struCardInputParamSet.write();
        Pointer pUserData = null;
        fRemoteCfgCallBackCardSet = new FRemoteCfgCallBackCardSet();
        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_SET_CARD_CFG_V50, lpInBuffer, m_struCardInputParamSet.size(), fRemoteCfgCallBackCardSet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println( "建立设置卡参数长连接成功!");
        HCNetSDK.NET_DVR_CARD_CFG_V50 struCardInfo = new HCNetSDK.NET_DVR_CARD_CFG_V50(); //卡参数
        struCardInfo.read();
        struCardInfo.dwSize = struCardInfo.size();
        struCardInfo.dwModifyParamType = 0x00000001 + 0x00000002 + 0x00000004 + 0x00000008 +
                0x00000010 + 0x00000020 + 0x00000080 + 0x00000100 + 0x00000200 + 0x00000400 + 0x00000800;
        /***
         * #define CARD_PARAM_CARD_VALID       0x00000001  //卡是否有效参数
         * #define CARD_PARAM_VALID            0x00000002  //有效期参数
         * #define CARD_PARAM_CARD_TYPE        0x00000004  //卡类型参数
         * #define CARD_PARAM_DOOR_RIGHT       0x00000008  //门权限参数
         * #define CARD_PARAM_LEADER_CARD      0x00000010  //首卡参数
         * #define CARD_PARAM_SWIPE_NUM        0x00000020  //最大刷卡次数参数
         * #define CARD_PARAM_GROUP            0x00000040  //所属群组参数
         * #define CARD_PARAM_PASSWORD         0x00000080  //卡密码参数
         * #define CARD_PARAM_RIGHT_PLAN       0x00000100  //卡权限计划参数
         * #define CARD_PARAM_SWIPED_NUM       0x00000200  //已刷卡次数
         * #define CARD_PARAM_EMPLOYEE_NO      0x00000400  //工号
         * #define CARD_PARAM_NAME             0x00000800  //姓名
         */
        for (int i = 0; i < HCNetSDK.ACS_CARD_NO_LEN; i++) {
            struCardInfo.byCardNo[i] = 0;
        }
        for (int i = 0; i < userid.length(); i++) {
            struCardInfo.byCardNo[i] = userid.getBytes()[i];
        }
        struCardInfo.byCardValid = 1;
        struCardInfo.byCardType = 1;
        struCardInfo.byLeaderCard = 0;
        struCardInfo.byDoorRight[0] = 1; //门1有权限
        struCardInfo.wCardRightPlan[0].wRightPlan[0] = 1; //门1关联卡参数计划模板1
        //卡有效期
        struCardInfo.struValid.byEnable = 1;
        struCardInfo.struValid.struBeginTime.wYear = 2017;
        struCardInfo.struValid.struBeginTime.byMonth = 12;
        struCardInfo.struValid.struBeginTime.byDay = 1;
        struCardInfo.struValid.struBeginTime.byHour = 0;
        struCardInfo.struValid.struBeginTime.byMinute = 0;
        struCardInfo.struValid.struBeginTime.bySecond = 0;
        struCardInfo.struValid.struEndTime.wYear = 2018;
        struCardInfo.struValid.struEndTime.byMonth = 12;
        struCardInfo.struValid.struEndTime.byDay = 1;
        struCardInfo.struValid.struEndTime.byHour = 0;
        struCardInfo.struValid.struEndTime.byMinute = 0;
        struCardInfo.struValid.struEndTime.bySecond = 0;
        struCardInfo.dwMaxSwipeTime = 0; //无次数限制
        struCardInfo.dwSwipeTime = 0;
        struCardInfo.byCardPassword = "12346".getBytes();
        struCardInfo.dwEmployeeNo = Integer.parseInt(userid);
        byte[] strCardName = cardName.getBytes("GBK");
        for (int i = 0; i < HCNetSDK.NAME_LEN; i++) {
            struCardInfo.byName[i] = 0;
        }
        for (int i = 0; i < strCardName.length; i++) {
            struCardInfo.byName[i] = strCardName[i];
        }
        struCardInfo.write();
        Pointer pSendBufSet = struCardInfo.getPointer();
        if (!hCNetSDK.NET_DVR_SendRemoteConfig(lHandle, 0x3, pSendBufSet, struCardInfo.size())) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("ENUM_ACS_SEND_DATA失败，错误号：" + iErr);
            return;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开长连接成功!");
    }

    /**
     * 获取工号 姓名信息
     */
    public void getCardInfo() {
        int iErr = 0;
        HCNetSDK.NET_DVR_CARD_CFG_COND m_struCardInputParam = new HCNetSDK.NET_DVR_CARD_CFG_COND();
        m_struCardInputParam.dwSize = m_struCardInputParam.size();
        m_struCardInputParam.dwCardNum = 0xffffffff; //查找全部
        m_struCardInputParam.byCheckCardNo = 1;
        Pointer lpInBuffer = m_struCardInputParam.getPointer();
        fRemoteCfgCallBackCardGet = new FRemoteCfgCallBackCardGet();
        m_struCardInputParam.write();
        HCNetSDK.MY_USER_DATA userData = new HCNetSDK.MY_USER_DATA();
        userData.dwSize = userData.size();
        userData.byteData = "1234567".getBytes();
        Pointer pUserData = userData.getPointer();
        userData.write();
        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_GET_CARD_CFG_V50, lpInBuffer, m_struCardInputParam.size(), fRemoteCfgCallBackCardGet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("建立获取卡参数长连接成功!");
     /*	//查找指定卡号
      HCNetSDK.NET_DVR_CARD_CFG_SEND_DATA m_struCardSendInputParam = new HCNetSDK.NET_DVR_CARD_CFG_SEND_DATA();
	m_struCardSendInputParam.read();
	m_struCardSendInputParam.dwSize = m_struCardSendInputParam.size();
	m_struCardSendInputParam.byCardNo = "111010".getBytes();
	m_struCardSendInputParam.byRes = "0".getBytes();

        Pointer pSendBuf = m_struCardSendInputParam.getPointer();
        m_struCardSendInputParam.write();

        if(!hCNetSDK.NET_DVR_SendRemoteConfig(lHandle, 0x3, pSendBuf, m_struCardSendInputParam.size()))
        {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            JOptionPane.showMessageDialog(null, "ENUM_ACS_SEND_DATA失败，错误号：" + iErr);
            return;
        }*/

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开长连接成功!");
    }

    /**
     * 根据卡号下发人脸
     * @param userid
     * @param userfacePath
     */
    public void setFaceCfg(String userid,String userfacePath) {
        int iErr = 0;
        //设置人脸参数
        HCNetSDK.NET_DVR_FACE_PARAM_COND m_struFaceSetParam = new HCNetSDK.NET_DVR_FACE_PARAM_COND();
        m_struFaceSetParam.dwSize = m_struFaceSetParam.size();
        m_struFaceSetParam.byCardNo = userid.getBytes(); //人脸关联的卡号
        m_struFaceSetParam.byEnableCardReader[0] = 1;
        m_struFaceSetParam.dwFaceNum = 1;
        m_struFaceSetParam.byFaceID = 1;
        m_struFaceSetParam.write();
        Pointer lpInBuffer = m_struFaceSetParam.getPointer();
        Pointer pUserData = null;
        fRemoteCfgCallBackFaceSet = new FRemoteCfgCallBackFaceSet();
        NativeLong lHandle = hCNetSDK.NET_DVR_StartRemoteConfig(lUserID, HCNetSDK.NET_DVR_SET_FACE_PARAM_CFG, lpInBuffer, m_struFaceSetParam.size(), fRemoteCfgCallBackFaceSet, pUserData);
        if (lHandle.intValue() < 0) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("建立长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println( "建立设置卡参数长连接成功!");

        HCNetSDK.NET_DVR_FACE_PARAM_CFG struFaceInfo = new HCNetSDK.NET_DVR_FACE_PARAM_CFG(); //卡参数
        struFaceInfo.read();
        struFaceInfo.dwSize = struFaceInfo.size();
        struFaceInfo.byCardNo = userid.getBytes();
        struFaceInfo.byEnableCardReader[0] = 1; //需要下发人脸的读卡器，按数组表示，每位数组表示一个读卡器，数组取值：0-不下发该读卡器，1-下发到该读卡器
        struFaceInfo.byFaceID = 1; //人脸ID编号，有效取值范围：1~2
        struFaceInfo.byFaceDataType = 1; //人脸数据类型：0- 模板（默认），1- 图片
        FileInputStream picfile = null;
        int picdataLength = 0;
        try {
            picfile = new FileInputStream(new File(userfacePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            picdataLength = picfile.available();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (picdataLength < 0) {
            System.out.println("input file dataSize < 0");
            return;
        }

        HCNetSDK.BYTE_ARRAY ptrpicByte = new HCNetSDK.BYTE_ARRAY(picdataLength);
        try {
            picfile.read(ptrpicByte.byValue);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        ptrpicByte.write();
        struFaceInfo.dwFaceLen = picdataLength;
        struFaceInfo.pFaceBuffer = ptrpicByte.getPointer();

        struFaceInfo.write();
        Pointer pSendBufSet = struFaceInfo.getPointer();

        //ENUM_ACS_INTELLIGENT_IDENTITY_DATA = 9,  //智能身份识别终端数据类型，下发人脸图片数据
        if (!hCNetSDK.NET_DVR_SendRemoteConfig(lHandle, 0x9, pSendBufSet, struFaceInfo.size())) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("NET_DVR_SendRemoteConfig失败，错误号：" + iErr);
            return;
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (!hCNetSDK.NET_DVR_StopRemoteConfig(lHandle)) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println("断开长连接失败，错误号：" + iErr);
            return;
        }
        System.out.println("断开长连接成功!");
    }

    /**
     * 根据卡号删除人脸数据
     * @param userid
     */
    public void delFaceCfg(String userid) {

        int iErr = 0;
        //删除人脸数据
        HCNetSDK.NET_DVR_FACE_PARAM_CTRL m_struFaceDel = new HCNetSDK.NET_DVR_FACE_PARAM_CTRL();
        m_struFaceDel.dwSize = m_struFaceDel.size();
        m_struFaceDel.byMode = 0; //删除方式：0- 按卡号方式删除，1- 按读卡器删除
        m_struFaceDel.struProcessMode.setType(HCNetSDK.NET_DVR_FACE_PARAM_BYCARD.class);
        m_struFaceDel.struProcessMode.struByCard.byCardNo = userid.getBytes();//需要删除人脸关联的卡号
        m_struFaceDel.struProcessMode.struByCard.byEnableCardReader[0] = 1; //读卡器
        m_struFaceDel.struProcessMode.struByCard.byFaceID[0] = 1; //人脸ID
        m_struFaceDel.write();
        Pointer lpInBuffer = m_struFaceDel.getPointer();
        boolean lRemoteCtrl = hCNetSDK.NET_DVR_RemoteControl(lUserID, HCNetSDK.NET_DVR_DEL_FACE_PARAM_CFG, lpInBuffer, m_struFaceDel.size());
        if (!lRemoteCtrl) {
            iErr = hCNetSDK.NET_DVR_GetLastError();
            System.out.println( "NET_DVR_DEL_FACE_PARAM_CFG删除人脸图片失败，错误号：" + iErr);
        } else {
            System.out.println("NET_DVR_DEL_FACE_PARAM_CFG成功!");
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        ClientDemo clientDemo = new ClientDemo();
        clientDemo.CameraInit();
        clientDemo.register("admin", "ytkj123456", "10.21.244.169");
        CDFLibBox cdfLibBox = new CDFLibBox();
        /*cdfLibBox.getFaceCFG();*/
       /* cdfLibBox.setCardInfo("12345678","哈哈","12345678");*/
        /*cdfLibBox.getCardInfo();*/
        /*cdfLibBox.setFaceCfg("12345678","F:\\Attence相关\\12345678.jpg");*/
        cdfLibBox.delFaceCfg("12345678");
    }
}
