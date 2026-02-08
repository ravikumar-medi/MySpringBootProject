package com.myproject.cbts.service;

import com.myproject.cbts.dto.CbtsCreateUserRequest;
import com.myproject.cbts.dto.CbtsCreateUserResponse;

public interface CbtsUserService {

    CbtsCreateUserResponse createUser(CbtsCreateUserRequest request);
}