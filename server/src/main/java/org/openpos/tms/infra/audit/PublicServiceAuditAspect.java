package org.openpos.tms.infra.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PublicServiceAuditAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicServiceAuditAspect.class);

	@Around("@annotation(org.openpos.tms.infra.PublicServiceMethod)")
	public Object auditServiceAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		String currentUser = getCurrentUser();
		// TODO audit framework
		LOGGER.info("User {} called service {}. Entering method: ", currentUser,
				joinPoint.getSignature().toShortString());
		Object result = joinPoint.proceed();
		LOGGER.info("User {} called service {}. Exiting method: ", currentUser,
				joinPoint.getSignature().toShortString());
		return result;
	}
	
	private String getCurrentUser() {
		String currentUser = "<undetermined>";
		try {
//			currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		} catch (Throwable t) {
			LOGGER.warn("Failed to retrieve current user");
		}
		return currentUser;
	}
}
