package com.swf.attence.hikConfig;

import com.sun.jna.Pointer;


public class FRemoteCfgCallBackFaceSet implements HCNetSDK.FRemoteConfigCallback {
    @Override
    public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        System.out.println("长连接回调获取数据,NET_SDK_CALLBACK_TYPE_STATUS:" + dwType);
        switch (dwType){
            // NET_SDK_CALLBACK_TYPE_STATUS
            case 0:
                HCNetSDK.REMOTECONFIGSTATUS_CARD struCfgStatus  = new HCNetSDK.REMOTECONFIGSTATUS_CARD();
                struCfgStatus.write();
                Pointer pCfgStatus = struCfgStatus.getPointer();
                pCfgStatus.write(0, lpBuffer.getByteArray(0, struCfgStatus.size()), 0,struCfgStatus.size());
                struCfgStatus.read();

                int iStatus = 0;
                for(int i=0;i<4;i++)
                {
                    int ioffset = i*8;
                    int iByte = struCfgStatus.byStatus[i]&0xff;
                    iStatus = iStatus + (iByte << ioffset);
                }

                switch (iStatus){
                    // NET_SDK_CALLBACK_STATUS_SUCCESS
                    case 1000:
                        System.out.println("下发人脸参数成功,dwStatus:" + iStatus);
                        break;
                    case 1001:
                        System.out.println("正在下发人脸参数中,dwStatus:" + iStatus);
                        break;
                    case 1002:
                        int iErrorCode = 0;
                        for(int i=0;i<4;i++)
                        {
                            int ioffset = i*8;
                            int iByte = struCfgStatus.byErrorCode[i]&0xff;
                            iErrorCode = iErrorCode + (iByte << ioffset);
                        }
                        System.out.println("下发人脸参数失败, dwStatus:" + iStatus + "错误号:" + iErrorCode);
                        break;
                        default:
                }
                break;
            // 获取状态数据
            case 2:
                HCNetSDK.NET_DVR_FACE_PARAM_STATUS  m_struFaceStatus = new HCNetSDK.NET_DVR_FACE_PARAM_STATUS();
                m_struFaceStatus.write();
                Pointer pStatusInfo = m_struFaceStatus.getPointer();
                pStatusInfo.write(0, lpBuffer.getByteArray(0, m_struFaceStatus.size()), 0,m_struFaceStatus.size());
                m_struFaceStatus.read();
                String str = new String(m_struFaceStatus.byCardNo).trim();
                System.out.println("下发人脸数据关联的卡号:" + str + ",人脸读卡器状态:" +
                        m_struFaceStatus.byCardReaderRecvStatus[0] + ",错误描述:" + new String(m_struFaceStatus.byErrorMsg).trim());
            default:
                break;
        }
    }
}
