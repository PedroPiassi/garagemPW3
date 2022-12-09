const sessionID = sessionStorage.getItem('id');
if (sessionID !== null) {
  window.location.replace('../home/Home.html');
}
window.onload = function () {
  $(document).on('click', '.btn-save', function (event) {
    event.preventDefault();
    let name = $('#name').val();
    let email = $('#email').val();
    let cpf = $('#cpf').val();
    let phone = $('#phone').val();
    let address = $('#address').val();
    let password = $('#password').val();
    let confirmPassword = $('#confirmPassword').val();

    if (name === null || name === '') {
      swal("Warning", "Name is null or empty", "warning");
    } else if (email === null || email === '') {
      swal("Warning", "Email is null or empty", "warning");
    } else if (cpf === null || cpf === '') {
      swal("Warning", "CPF is null or empty", "warning");
    } else if (phone === null || phone === '') {
      swal("Warning", "Phone is null or empty", "warning");
    } else if (address === null || address === '') {
      swal("Warning", "Address is null or empty", "warning");
    } else if (password === null || password === '') {
      swal("Warning", "Password is null or empty", "warning");
    } else if (confirmPassword === null || confirmPassword === '') {
      swal("Warning", "Confirm password is null or empty", "warning");
    } else if (password !== confirmPassword) {
      $('#password').val("");
      $('#confirmPassword').val("");
      swal("Warning", "Passwords are not the same", "warning");
    } else {
      $.ajax({
        url: "http://localhost:8080/client",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
          "name": name,
          "email": email,
          "cpf": cpf,
          "phone": phone,
          "address": address,
          "password": password
        })
      }).done(function (data) {
        swal("Success", data.name + " registered", "success");
      }).fail(function (errorThrown) {
        swal("Error", errorThrown.responseText, "error");
      });
    }
  });
}