package com.baeldung.webclient.authcodexxx.client.configuration.filters;
//package com.baeldung.webclient.authcode.client.configuration.filters;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.oauth2.client.ClientAuthorizationRequiredException;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
//import org.springframework.security.web.server.savedrequest.ServerRequestCache;
//import org.springframework.security.web.server.savedrequest.WebSessionServerRequestCache;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import org.springframework.web.server.WebSession;
//
//import com.baeldung.webclient.clientcredentials.service.WebClientChonJob;
//
//import reactor.core.publisher.Mono;
//
//public class OAuth2ClientOnlyAuthorizationRequestRedirectWebFilter implements WebFilter {
//    
//    Logger logger = LoggerFactory.getLogger(OAuth2ClientOnlyAuthorizationRequestRedirectWebFilter.class);
//    
//    private ServerRequestCache requestCache = new WebSessionServerRequestCache();
////    private final ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver;
//    
////    public OAuth2ClientOnlyAuthorizationRequestRedirectWebFilter(ReactiveClientRegistrationRepository clientRegistrationRepository) {
////        this.authorizationRequestResolver = new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
////    }
//
////    @Override
////    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
////            return this.authorizationRequestResolver.resolve(exchange)
////                    .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
////                    .doOnError(ClientAuthorizationRequiredException.class, e -> this.requestCache.saveRequest(exchange)).then();
////    }
//    
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
////        
//        return chain.filter(exchange).onErrorResume(ClientAuthorizationRequiredException.class, e -> {
//            logger.info("GERGERGER: EXCHANGE: {}",exchange.getRequest().getPath());
//            exchange.getSession().doOnNext(a -> logger.info("GERGERGER2: EXCHANGE: {}" + a.getAttributes()));
//            exchange.getSession().doOnNext(a -> logger.info("GERGERGER2: EXCHANGE: {}" + a.getAttributes()));
//            exchange.getSession().map(a -> {
//                logger.info("GERGERGER3: EXCHANGE: {}" + a.getAttributes());
//                return a;
//            });
//            this.requestCache.saveRequest(exchange);
//            throw e;
////            logger.info("GERGERGER7: EXCHANGE: {}",exchange.getRequest().getPath());
////            exchange.getSession().doOnNext(a -> logger.info("GERGERGER8: EXCHANGE: {}" + a.getAttributes()));
////            exchange.getSession().map(a -> {
////                logger.info("GERGERGER9: EXCHANGE: {}" + a.getAttributes());
////                return a;
////            });
//        }).flatMap();
//        
//        
////        return chain.filter(exchange)
////            .doOnError(ClientAuthorizationRequiredException.class, 
////                e -> exchange.getSession()
////                  .map(WebSession::getAttributes)
////                  .doOnNext(sessionAttrs -> {
////                    sessionAttrs.putIfAbsent("SPRING_SECURITY_SAVED_REQUEST", exchange.getRequest().getPath().pathWithinApplication().value());
////        }));
//        
//        
////            return this.authorizationRequestResolver.resolve(exchange)
////                    .switchIfEmpty(chain.filter(exchange).then(Mono.empty()))
////                    .doOnError(ClientAuthorizationRequiredException.class, e -> this.requestCache.saveRequest(exchange)).then();
//    }
//}
