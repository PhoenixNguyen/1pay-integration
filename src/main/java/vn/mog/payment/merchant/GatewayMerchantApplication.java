package vn.mog.payment.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class GatewayMerchantApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(GatewayMerchantApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GatewayMerchantApplication.class);
    }
}
