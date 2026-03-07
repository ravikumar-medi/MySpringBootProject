package com.in.dev.cbts.service;

import com.in.dev.cbts.dto.CbtsLoginRequest;
import com.in.dev.cbts.dto.CbtsLoginResponse;

public interface CbtsAuthService {
    CbtsLoginResponse Cbtslogin(CbtsLoginRequest request);
}