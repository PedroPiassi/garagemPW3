let sessionID = sessionStorage.getItem('id');
if (sessionID !== null) {
  window.location.replace('../home/Home.html');
}
window.onload = function () {
  $(document).on('click', '.btn-login', function (event) {
    event.preventDefault();
    let email = $('#email').val();
    let password = $('#password').val();

    if (email === null || email === '') {
      swal("Warning", "Email is null or empty", "warning");
    } else if (password === null || password === '') {
      swal("Warning", "Password is null or empty", "warning");
    } else {
      $.ajax({
        url: "http://localhost:8080/client/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
          "email": email,
          "password": password,
        })
      }).done(function (client) {
        sessionStorage.setItem('id', client.id);
        window.location.replace('../home/Home.html');
      }).fail(function (errorThrown) {
        swal("Error", errorThrown.responseText, "error");
      });
    }
  });
}