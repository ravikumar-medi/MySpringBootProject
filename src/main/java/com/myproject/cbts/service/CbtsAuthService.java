package com.myproject.cbts.service;

import com.myproject.cbts.dto.CbtsLoginRequest;
import com.myproject.cbts.dto.CbtsLoginResponse;

public interface CbtsAuthService {
    CbtsLoginResponse Cbtslogin(CbtsLoginRequest request);
}