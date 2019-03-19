package com.swf.attence.hikConfig;

import com.sun.jna.Pointer;

public class FRemoteCfgCallBackCardSet implements HCNetSDK.FRemoteConfigCallback {
    @Override
    public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
        switch (dwType){
            // NET_SDK_CALLBACK_TYPE_STATUS
            case 0:
                HCNetSDK.REMOTECONFIGSTATUS_CARD struCardStatus = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                struCardStatus.write();
                Pointer pInfoV30 = struCardStatus.getPointer();
                pInfoV30.write(0, lpBuffer.getByteArray(0, struCardStatus.size()), 0,struCardStatus.size());
                struCardStatus.read();

                int iStatus = 0;
                for(int i=0;i<4;i++)
                {
                    int ioffset = i*8;
                    int iByte = struCardStatus.byStatus[i]&0xff;
                    iStatus = iStatus + (iByte << ioffset);
                }

                switch (iStatus){
                    // NET_SDK_CALLBACK_STATUS_SUCCESS
                    case 1000:
                        System.out.println("下发卡参数成功,dwStatus:" + iStatus);
                        break;
                    case 1001:
                        System.out.println("正在下发卡参数中,dwStatus:" + iStatus);
                        break;
                    case 1002:
                        int iErrorCode = 0;
                        for(int i=0;i<4;i++)
                        {
                            int ioffset = i*8;
                            int iByte = struCardStatus.byErrorCode[i]&0xff;
                            iErrorCode = iErrorCode + (iByte << ioffset);
                        }
                        System.out.println("下发卡参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                        break;
                    default:
                }
                break;
            default:
                break;
        }
    }
}
