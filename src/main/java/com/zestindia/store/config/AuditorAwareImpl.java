package com.zestindia.store.config;

// import com.zestindia.store.entity.AppUser;
// import com.zestindia.store.repository.AppUserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);



    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("No authentication found or not authenticated");
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        // Assume the principal is a UserDetails with getUsername() and getId() (custom UserDetails implementation)
        if (principal instanceof UserDetails userDetails) {
            // If your UserDetails implementation has getId(), use it directly
            try {
                java.lang.reflect.Method getIdMethod = userDetails.getClass().getMethod("getId");
                Object id = getIdMethod.invoke(userDetails);
                if (id instanceof Integer userId) {
                    logger.info("Resolved user id for auditing: {}", userId);
                    return Optional.of(userId);
                }
            } catch (Exception e) {
                logger.warn("Could not extract user id from UserDetails", e);
            }
        }
        logger.warn("Could not resolve user id for auditing");
        return Optional.empty();
    }
}
