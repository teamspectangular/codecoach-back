package com.switchfully.spectangular.dtos;

import org.hibernate.Session;

import java.util.Objects;

public class SessionDto {

    private int id;
    private String subject;
    private String date;
    private String startTime;
    private String location;
    private String remarks;
    private int coachId;
    private int coacheeId;
    private String coachProfileName;
    private String coacheeProfileName;
    private String status;
    private FeedbackForCoachDto feedbackForCoach;
    private FeedbackForCoacheeDto feedbackForCoachee;


    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLocation() {
        return location;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getCoachId() {
        return coachId;
    }

    public int getCoacheeId() {
        return coacheeId;
    }

    public SessionDto setId(int id) {
        this.id = id;
        return this;
    }

    public SessionDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public SessionDto setDate(String date) {
        this.date = date;
        return this;
    }

    public SessionDto setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public SessionDto setLocation(String location) {
        this.location = location;
        return this;
    }

    public SessionDto setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public SessionDto setCoachId(int coachId) {
        this.coachId = coachId;
        return this;
    }

    public SessionDto setCoacheeId(int coacheeId) {
        this.coacheeId = coacheeId;
        return this;
    }

    public String getCoachProfileName() {
        return coachProfileName;
    }

    public SessionDto setCoachProfileName(String coachProfileName) {
        this.coachProfileName = coachProfileName;
        return this;
    }

    public String getCoacheeProfileName() {
        return coacheeProfileName;
    }

    public SessionDto setCoacheeProfileName(String coacheeProfileName) {
        this.coacheeProfileName = coacheeProfileName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public SessionDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public FeedbackForCoachDto getFeedbackForCoach() {
        return feedbackForCoach;
    }

    public SessionDto setFeedbackForCoach(FeedbackForCoachDto feedbackForCoach) {
        this.feedbackForCoach = feedbackForCoach;
        return this;
    }

    public FeedbackForCoacheeDto getFeedbackForCoachee() {
        return feedbackForCoachee;
    }

    public SessionDto setFeedbackForCoachee(FeedbackForCoacheeDto feedbackForCoachee) {
        this.feedbackForCoachee = feedbackForCoachee;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDto that = (SessionDto) o;
        return coachId == that.coachId && coacheeId == that.coacheeId && Objects.equals(subject, that.subject) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(location, that.location) && Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, date, startTime, location, remarks, coachId, coacheeId);
    }

    @Override
    public String toString() {
        return "SessionDto{" + "id=" + id + ", subject='" + subject + '\'' + ", date='" + date + '\'' + ", startTime='" + startTime + '\'' + ", location='" + location + '\'' + ", remarks='" + remarks + '\'' + ", coachId=" + coachId + ", coacheeId=" + coacheeId + ", coachProfileName='" + coachProfileName + '\'' + ", coacheeProfileName='" + coacheeProfileName + '\'' + ", status='" + status + '\'' + ", feedbackForCoach=" + feedbackForCoach + ", feedbackForCoachee=" + feedbackForCoachee + '}';
    }
}
