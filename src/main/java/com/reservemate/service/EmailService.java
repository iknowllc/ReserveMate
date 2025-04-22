package com.reservemate.service;

import com.reservemate.pojo.dto.EmailRequest;
import com.reservemate.pojo.vo.Result;

public interface EmailService {
    Result sendCode(EmailRequest emailRequest);

}
