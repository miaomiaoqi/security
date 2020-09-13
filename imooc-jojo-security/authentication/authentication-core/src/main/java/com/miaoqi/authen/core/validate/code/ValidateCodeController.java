package com.miaoqi.authen.core.validate.code;

import com.miaoqi.authen.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController {

    // public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    // private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    // @Autowired
    // private ValidateCodeGenerator imageCodeGenerator;
    // @Autowired
    // private ValidateCodeGenerator smsCodeGenerator;
    // @Autowired
    // private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        // this.validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
        this.validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

    // @GetMapping("/code/image")
    // public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //     ImageCode imageCode = (ImageCode) this.imageCodeGenerator.generate(new ServletWebRequest(request));
    //     this.sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    //     ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    // }
    //
    // @GetMapping("/code/sms")
    // public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
    //     ValidateCode smsCode = this.smsCodeGenerator.generate(new ServletWebRequest(request));
    //     this.sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
    //     String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
    //     this.smsCodeSender.send(mobile, smsCode.getCode());
    // }


}
