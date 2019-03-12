package com.swf.attence.hikConfig;

import com.sun.jna.Pointer;



public class FRemoteConfigCallback implements HCNetSDK.FRemoteConfigCallback{
    @Override
    public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {
        System.out.println("1:  "+dwType+"     2: "+lpBuffer+"    3: "+dwBufLen+"     4:"+pUserData);
    }
}
