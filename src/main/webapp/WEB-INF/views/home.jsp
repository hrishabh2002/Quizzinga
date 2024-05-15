<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.secure.web.entity.Student" %>
<%@ page import="com.secure.web.entity.ScoreEntry" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style><%@include file="home.css"%></style>
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <p>Quizzinga</p>
        </div>
        <div class="user-info">
            <p class="name">Welcome <span>${loggedInStudent}!</span></p>
            <button class="logout"><a href="/logout">Logout</a></button>
        </div>
        <div class="quiz-form">
            <form action="/quizzes/fetch-questions-for-user" method="get">
                <div class="inputs">
                    Number of questions: <input type="number" name="num"/><br/>  
                </div> 
                <div class="inputs">
                    Topic of the Quiz: <input type="text" name="topic" /><br/>
                </div>
                <div class="inputs">
                    <input type="submit" value="Take Quiz" class="take-quiz"/> 
                </div>
            </form>
        </div>
        <div class="quiz-scores">
           <table border="1">
    <tr>
        <th>Category</th>
        <th>Date</th>
        <th>Score</th>
    </tr>
    <% 
        List<ScoreEntry> loggedInScores = (List<ScoreEntry>) request.getAttribute("loggedInScores");
        if (loggedInScores != null) {
            for (ScoreEntry score : loggedInScores) {
    %>
                <tr>
                    <td><%= score.getCategory() %></td>
                    <td><%= score.getDate() %></td>
                    <td><%= score.getScore() %> </td>
                </tr>
    <%
            }
        }
    %>
</table>

        </div>
    </div>
</body>
</html>
