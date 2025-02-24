<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <title>Note Taker :  Home page</title>

    <%@include file="all_js_cs.jsp"%>

  </head>
  <body>
  <!-- -fluid p-0 m-0-->
<div class ="container">
<%@include file="navbar.jsp"%>

<br>


		<div class="card  shadow bg-white py-5">
			<img alt="" class="img-fluid mx-auto" style="max-width: 400px;"
				src="notes.png">
			<h1 class="text-primary text-uppercase text-center mt-3">Start
				Taking your notes</h1>

			<div class="container text-center">
				<a href="add_notes.jsp" class="btn btn-primary text-center">Start
					here</a>
					<a href="dashboard.jsp" class="btn btn-primary text-center">Check Dash Board</a>
					<a href="LogOut" class="btn btn-primary text-center">LogOut
                    					here</a>
			</div>

		</div>

	</div>

</div>


  </body>
</html>
