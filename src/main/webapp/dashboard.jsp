<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Reminder" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="Styledashboard.css"> <!-- Link to your CSS file -->
    <script>
        function showDueReminders() {
            document.getElementById('dueReminders').style.display = 'block';
            document.getElementById('futureReminders').style.display = 'none';
            document.getElementById('addReminderForm').style.display = 'none';
        }

        function showFutureReminders() {
            document.getElementById('dueReminders').style.display = 'none';
            document.getElementById('futureReminders').style.display = 'block';
            document.getElementById('addReminderForm').style.display = 'none';
        }

        function showAddReminderForm() {
            document.getElementById('dueReminders').style.display = 'none';
            document.getElementById('futureReminders').style.display = 'none';
            document.getElementById('addReminderForm').style.display = 'block';
        }

        // Complete Task and remove from the page
        function completeTask(taskElement) {
            const completeBtn = taskElement.querySelector('.complete-btn');

            // Disable the button to prevent multiple clicks
            completeBtn.disabled = true;

            // Simulate marking the task as complete
            taskElement.style.textDecoration = 'line-through';

            // Send a request to the server to mark the task complete and delete it from the DB
            fetch('CompleteTaskServlet', {
                method: 'POST',
                body: JSON.stringify({ content_title: taskElement.dataset.contentTitle }), // Pass the content title only
                headers: { 'Content-Type': 'application/json' }
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    taskElement.remove(); // Remove the task from the DOM
                } else {
                    // Re-enable the button if there was an error
                    completeBtn.disabled = false;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                // Re-enable the button if there was an error
                completeBtn.disabled = false;
            });
        }
    </script>
</head>
<body>
    <h2>Welcome to Your Dashboard</h2>

    <div class="button-group">
        <button onclick="showDueReminders()">View Due Reminders</button>
        <button onclick="showFutureReminders()">View Future Reminders</button>
        <button onclick="showAddReminderForm()">Add Reminder</button>
        <button onclick="window.location.href='index.jsp'" class="btn btn-primary text-center">Come back to home</button>
    </div>

    <!-- Reminder Form -->
    <div id="addReminderForm" style="display: none;">
        <h3>Add Reminder</h3>
        <form action="AddReminderServlet" method="post">
            <label for="content_title">Content Title:</label>
            <input type="text" id="content_title" name="content_title" required>

            <label for="reminderDatetime">Set Reminder:</label>
            <input type="datetime-local" id="reminderDatetime" name="reminderDatetime" required>

            <button type="submit">Add Reminder</button>
        </form>
    </div>

    <!-- Due Reminders -->
    <div id="dueReminders" style="display: none;">
        <h3>Due Reminders</h3>
        <ul>
            <%
                List<Reminder> dueReminders = (List<Reminder>) request.getAttribute("dueReminders");
                if (dueReminders != null && !dueReminders.isEmpty()) {
                    for (Reminder reminder : dueReminders) {
            %>
                <li data-content-title="<%= reminder.getContent_title() %>">
                    Reminder for Content Title: <%= reminder.getContent_title() %>
                    <button class="complete-btn" onclick="completeTask(this.parentNode)">Complete</button>
                </li>
            <%
                    }
                } else {
            %>
                <li>No due reminders.</li>
            <%
                }
            %>
        </ul>
    </div>

    <!-- Future Reminders -->
    <div id="futureReminders" style="display: none;">
        <h3>Future Reminders</h3>
        <ul>
            <%
                List<Reminder> futureReminders = (List<Reminder>) request.getAttribute("futureReminders");
                if (futureReminders != null && !futureReminders.isEmpty()) {
                    for (Reminder reminder : futureReminders) {
            %>
                <li data-content-title="<%= reminder.getContent_title() %>">
                    Reminder for Content Title: <%= reminder.getContent_title() %> at <%= reminder.getReminderDateTime() %>
                    <button class="complete-btn" onclick="completeTask(this.parentNode)">Complete</button>
                </li>
            <%
                    }
                } else {
            %>
                <li>No future reminders.</li>
            <%
                }
            %>
        </ul>
    </div>
</body>
</html>