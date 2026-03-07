package com.in.dev.cbts.service;

import com.in.dev.cbts.dto.CbtsCreateUserRequest;
import com.in.dev.cbts.dto.CbtsCreateUserResponse;

public interface CbtsUserService {

    CbtsCreateUserResponse createUser(CbtsCreateUserRequest request);
}