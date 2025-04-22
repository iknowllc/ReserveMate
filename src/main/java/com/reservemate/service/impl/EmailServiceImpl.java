package com.reservemate.service.impl;
import com.reservemate.pojo.dto.EmailRequest;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender; // 假设你使用 Spring Boot 的邮件发送工具
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public Result sendCode(EmailRequest emailRequest) {
        if (emailRequest.getEmail() == null || !isValidEmail(emailRequest.getEmail())) {
            return Result.failure("无效的邮箱地址");
        }
        // 1. 生成验证码
        String email = emailRequest.getEmail();
        String verificationCode = generateVerificationCode();
        // 2. 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("餐厅预约系统");
        message.setText("你正在注册餐厅预约系统，您的验证码是：" + verificationCode);
        message.setFrom("18803705331@139.com");
        mailSender.send(message);  // 发送邮件
        // 把验证码存入缓存,保留两分钟
        redisTemplate.opsForValue().set("email:" + email, verificationCode);
        //todo 验证码保留时间的代码（已完成，测试时不开启）
        //redisTemplate.expire("email:" +email, 120, TimeUnit.SECONDS);
        return Result.success();
    }
    // 简单的邮箱格式校验方法
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // 生成验证码的简单逻辑（实际生产环境中可能需要更复杂的验证码）
    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 生成 6 位随机数字验证码
    }
}
