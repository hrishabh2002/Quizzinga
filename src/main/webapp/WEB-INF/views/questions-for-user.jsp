<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.secure.web.entity.Questions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Questions for User</title>
    <style><%@include file="home.css"%></style>
</head>
<body>
	<div class="quiz-container">
    <div class="header"><p>Quizzinga</p></div>
    
    <div>
        <div id="timer">Timer: 30:00</div>
        <div>
            <%-- Initialize score and timer variables --%>
            <%	
            	String category=(String)request.getAttribute("category");
                int score = 0;
                long startTime = System.currentTimeMillis(); 
                long endTime = startTime + 30 * 60 * 1000; 
                List<Questions> questionsList = (List<Questions>) request.getAttribute("questions");
                if (questionsList != null) {
                    for (int i = 0; i < questionsList.size(); i++) {
                        Questions question = questionsList.get(i);
            %>
                            <div class="ques">
                                <p>Question <%= i + 1 %>: <%= question.getQuest() %></p>
                                <p>Options:</p>
                                <form id="questionForm">
                                    <% for (String option : question.getOptions()) { %>
                                        <input type="radio" name="answer<%= i %>" value="<%= option %>" onclick="checkAnswer('<%= option %>','<%= question.getAnswers().get(0) %>')"><%= option %><br>
                                    <% } %>
                                </form>
                            </div>
            <%
                    }
                }
            %>

           
            <form action="/submit-process" method="post" id="quizForm">
                <input type="hidden" name="score" id="hiddenScore" value="<%= score*100/ questionsList.size() %>">
                <input type="hidden" name="duration" id="duration" value="0">
                  <input type="hidden" name="category" id="category" value="<%= category %>">
                <button type="submit" onclick="stopTimer()" class="submit-quiz">Submit</button>
            </form>
        </div>
    </div>
</div>
    <script>
        var score = 0;
        var startTime = <%= startTime %>; 
        var endTime = <%= endTime %>; 
        var timerInterval;

        function checkAnswer(option, answer) {
            if (option === answer) {
                score += 5;
            }
        }

        function submitQuiz() {
            document.getElementById("hiddenScore").value = score;
            document.getElementById("quizForm").submit();
        }

        function updateTimer() {
            var currentTime = new Date().getTime();
            var remainingTime = Math.max(0, endTime - currentTime); 
            var minutes = Math.floor(remainingTime / (1000 * 60));
            var seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);
            document.getElementById("timer").innerText = "Timer: " + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
            document.getElementById("duration").value = <%= endTime %> - currentTime; 
            if (remainingTime <= 0) {
                stopTimer(); 
            }
        }

        function startTimer() {
            timerInterval = setInterval(updateTimer, 1000);
        }

        function stopTimer() {
            clearInterval(timerInterval);
            updateTimer(); 
            submitQuiz();
        }

        startTimer(); 
    </script>
</body>
</html>
