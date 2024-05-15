<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="com.secure.web.entity.Questions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Questions</title>
</head>
<body>
    <h1>All Questions ${questions.size()}</h1>
    <div>
       <%-- Initialize score --%>
    <%
        int score = 0;
        List<Questions> questionsList = (List<Questions>) request.getAttribute("questions");
        if (questionsList != null) {
            for (int i = 0; i < questionsList.size(); i++) {
                Questions question = questionsList.get(i);
    %>
                <div>
                    <p>Question <%= i + 1 %>: <%= question.getQuest() %></p>
                    <p>Options:</p>
                    <%
                        for (String option : question.getOptions()) {
                    %>
                            <p><a href="javascript:void(0);" onclick="checkAnswer('<%= option %>','<%= question.getAnswers().get(0) %>')"> <%= option %> </a></p>
                    <%
                        }
                    %>
                </div>
    <%
            }
        }
    %>

    <p>Score: <span id="score"><%= score %></span></p>
    <button onclick="submitQuiz()">Submit</button>

    <script>
        var score = 0;

        function checkAnswer(option, answer) {
            if (option === answer) {
                score += 5;
            }
        }

        function submitQuiz() {
            document.getElementById("score").innerText = score;
        }
    </script>

    </div>
</body>
</html>
