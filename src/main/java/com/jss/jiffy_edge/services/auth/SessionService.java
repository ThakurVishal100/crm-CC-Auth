package com.jss.jiffy_edge.services.auth;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.dao.entities.auth.TblUserSession;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.repo.auth.TblUserSessionRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.UserLoginRequest;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SessionService {

    @Autowired
    private TblUserSessionRepository sessionRepository;

    @Autowired
    private TblUsersRepository usersRepository;

    public void createNewSession(String sessionId, Integer userId, Integer roleId, String jwt, HttpServletRequest request, UserLoginRequest loginDetails) {
        TblUsers user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for session creation"));

        TblUserSession session = new TblUserSession();
        session.setSessionId(sessionId);
        session.setUser(user);
        session.setRoleId(roleId);
        session.setJwtToken(jwt);
        session.setIpAddress(request.getRemoteAddr());
        session.setDeviceDetails(request.getHeader("User-Agent"));
        
       
        if (loginDetails != null) {
            session.setGeoCity(loginDetails.getGeoCity());
            session.setGeoCountry(loginDetails.getGeoCountry());
            session.setGeoState(loginDetails.getGeoState());
        }

        session.setStatus(TblUserSession.SessionStatus.ACTIVE);
        session.setCreatedAt(LocalDateTime.now());
        session.setLastAccessedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(24));

        sessionRepository.save(session);
    }
}