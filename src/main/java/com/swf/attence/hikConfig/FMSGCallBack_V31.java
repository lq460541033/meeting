package com.swf.attence.hikConfig;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31{
    //报警信息回调函数
    @Override
    public void invoke(NativeLong lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser)
    {
        try {
            String sAlarmType = new String();
            //报警时间
            Date today = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String[] sIP = new String[2];
            sAlarmType = new String("lCommand=") + lCommand.intValue();
            //lCommand是传的报警类型
            if(lCommand.intValue()==HCNetSDK.COMM_SNAP_MATCH_ALARM){
                HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM strFaceSnapMatch = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                strFaceSnapMatch.write();
                Pointer pFaceSnapMatch = strFaceSnapMatch.getPointer();
                pFaceSnapMatch.write(0, pAlarmInfo.getByteArray(0, strFaceSnapMatch.size()), 0, strFaceSnapMatch.size());
                strFaceSnapMatch.read();

                sAlarmType = sAlarmType + "：签到比对报警，相识度：" + strFaceSnapMatch.fSimilarity + "，姓名：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byName, "GBK").trim() + "，\n证件信息：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byCertificateNumber).trim();

                //获取人脸库ID
                byte[] FDIDbytes;
                if ((strFaceSnapMatch.struBlackListInfo.dwFDIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pFDID != null)) {
                    ByteBuffer FDIDbuffers = strFaceSnapMatch.struBlackListInfo.pFDID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwFDIDLen);
                    FDIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwFDIDLen];
                    FDIDbuffers.rewind();
                    FDIDbuffers.get(FDIDbytes);
                    sAlarmType = sAlarmType + "，人脸库的ID:" + new String(FDIDbytes).trim();
                }
                //获取人脸图片ID
                byte[] PIDbytes;
                if ((strFaceSnapMatch.struBlackListInfo.dwPIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pPID != null)) {
                    ByteBuffer PIDbuffers = strFaceSnapMatch.struBlackListInfo.pPID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwPIDLen);
                    PIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwPIDLen];
                    PIDbuffers.rewind();
                    PIDbuffers.get(PIDbytes);
                    sAlarmType = sAlarmType + "，人脸图片的ID:" + new String(PIDbytes).trim();
                }
                //报警类型
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                System.out.println("报警时间"+dateFormat.format(today)+"报警IP"+sIP[0]+"报警类型"+sAlarmType);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
