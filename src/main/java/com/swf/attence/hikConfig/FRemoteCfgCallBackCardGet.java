package com.swf.attence.hikConfig;

import com.sun.jna.Pointer;

import java.io.UnsupportedEncodingException;

public class FRemoteCfgCallBackCardGet implements HCNetSDK.FRemoteConfigCallback {
    @Override
    public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        HCNetSDK.MY_USER_DATA m_userData = new HCNetSDK.MY_USER_DATA();
        m_userData.write();
        Pointer pUserVData = m_userData.getPointer();
        pUserVData.write(0, pUserData.getByteArray(0, m_userData.size()), 0, m_userData.size());
        m_userData.read();

        System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
        switch (dwType) {
            //NET_SDK_CALLBACK_TYPE_STATUS
            case 0:
                HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                struCfgStatus.write();
                Pointer pCfgStatus = struCfgStatus.getPointer();
                pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0, struCfgStatus.size());
                struCfgStatus.read();

                int iStatus = 0;
                for (int i = 0; i < 4; i++) {
                    int ioffset = i * 8;
                    int iByte = struCfgStatus.byStatus[i] & 0xff;
                    iStatus = iStatus + (iByte << ioffset);
                }
                // NET_SDK_CALLBACK_STATUS_SUCCESS
                switch (iStatus) {
                    case 1000:
                        System.out.println("查询卡参数成功,dwStatus:" + iStatus);
                        break;
                    case 1001:
                        System.out.println("正在查询卡参数中,dwStatus:" + iStatus);
                        break;
                    case 1002:
                        int iErrorCode = 0;
                        for (int i = 0; i < 4; i++) {
                            int ioffset = i * 8;
                            int iByte = struCfgStatus.byErrorCode[i] & 0xff;
                            iErrorCode = iErrorCode + (iByte << ioffset);
                        }
                        System.out.println("查询卡参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                        break;
                    default:
                }
                break;
            //NET_SDK_CALLBACK_TYPE_DATA
            case 2:
                HCNetSDK.NET_DVR_CARD_CFG_V50 m_struCardInfo = new HCNetSDK.NET_DVR_CARD_CFG_V50();
                m_struCardInfo.write();
                Pointer pInfoV30 = m_struCardInfo.getPointer();
                pInfoV30.write(0, lpBuffer.getByteArray(0, m_struCardInfo.size()), 0, m_struCardInfo.size());
                m_struCardInfo.read();

                String str = new String(m_struCardInfo.byCardNo);


                try {
                    //姓名
                    String srtName = new String(m_struCardInfo.byName, "GBK").trim();
                    System.out.println("查询到的卡号,getCardNo:" + str + "姓名:" + srtName);
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
