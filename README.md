# sample-microservice
Sample Learning project For Microservice Architecture
      http:
        GET: getAllProduct
        DEFAULT getAllProduct1 is get but we can make it post
        POST: getAllProduct1
      routing-expression: headers.x-function-route
    DEFAULT ROUTING FUNCTION > org.springframework.cloud.function.context.config.RoutingFunction
    PROVIDE custom message convertor
    @Bean
    public MessageConverter customMessageConverter() {
    return new MyCustomMessageConverter();
  }