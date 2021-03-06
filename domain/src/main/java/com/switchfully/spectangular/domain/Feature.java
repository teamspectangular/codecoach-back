package com.switchfully.spectangular.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.switchfully.spectangular.domain.Role.*;

public enum Feature {


    GET_USER_INFORMATION(ADMIN, COACH, COACHEE),
    COACH_REQUEST(COACHEE),
    TOPICS_REQUEST(COACH, ADMIN),
    GET_ALL_COACHES(ADMIN, COACH, COACHEE),
    GET_ALL_COACHEES(ADMIN, COACH, COACHEE),
    CREATE_SESSION(ADMIN, COACH, COACHEE),
    GET_ALL_COACHING_SESSION(ADMIN, COACH),
    GET_ALL_COACHEE_SESSIONS(ADMIN, COACH, COACHEE),
    UPDATE_SESSION_STATUS(ADMIN, COACH, COACHEE),
    UPDATE_SESSION(ADMIN),
    UPDATE_PROFILE(ADMIN, COACH, COACHEE),
    GET_ALL_USERS(ADMIN),
    GET_ALL_TOPICS(ADMIN, COACH, COACHEE),
    GET_ALL_SESSIONS(ADMIN),
    UPDATE_TOPICS(ADMIN),
    GET_SESSION_BY_ID(ADMIN);

    private final List<Role> roleList;

    Feature(Role... roleList) {
        this.roleList = Arrays.asList(roleList);
    }

    public static List<Feature> getForRole(Role role) {
        return Arrays.stream(Feature.values()).filter(f -> f.roleList.contains(role)).collect(Collectors.toList());
    }

    public List<Role> getRoleList() {
        return Collections.unmodifiableList(roleList);
    }
}
