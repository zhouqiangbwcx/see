package com.see.service.acp.controller;

 
import org.springframework.amqp.rabbit.core.RabbitTemplate;  
import org.springframework.amqp.rabbit.support.CorrelationData;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.see.service.callbak.ProductService;
import com.see.service.configure.RabbitMQConfig;

import java.io.UnsupportedEncodingException;  
import java.util.UUID;  
  
/**  
 * <dl>  
 * <dt>SendController</dt>  
 * <dd>Description:</dd>  
 * <dd>CreateDate: 2017/9/1</dd>  
 * </dl>  
 *  
 * @author Administrator  
 */  
@Controller  
public class SendController{  
  
    @Autowired  
    private ProductService productService;  
  
    @RequestMapping("/send")  
    public String send3() throws UnsupportedEncodingException {  
        String uuid = UUID.randomUUID().toString();  
        CorrelationData correlationId = new CorrelationData(uuid);  
        productService.send("哈哈");
        return uuid;  
    }  
  
}  
