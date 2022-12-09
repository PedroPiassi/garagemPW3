const sessionID = sessionStorage.getItem('id');
if (sessionID === null) {
  window.location.replace('../Login/login.html');
}
window.onload = function () {
  const loadData = function () {
    $.ajax({
      url: "http://localhost:8080/client/" + sessionID,
      type: "GET",
    }).done(function (client) {
      $('.name').text(client.name);
      $('.email').text(client.email);
      $('.cpf').text(client.cpf);
      $('.phone').text(client.phone);
      $('.address').text(client.address);
    }).fail(function (errorThrown) {
      console.log(errorThrown);
    });
  }

  $(document).on('click', '.btn-update', function (e) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/client/" + sessionID,
      type: "GET",
    }).done(function (client) {
      $('#name').val(client.name);
      $('#email').val(client.email);
      $('#cpf').val(client.cpf);
      $('#phone').val(client.phone);
      $('#address').val(client.address);
      $('#password').val(client.password);
      $('#confirmPassword').val(client.password);
    }).fail(function (errorThrown) {
      console.log(errorThrown);
    });
  });

  $(document).on('click', '.update', function (e) {
    e.preventDefault();
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
        url: "http://localhost:8080/client/" + sessionID,
        type: "PUT",
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
        swal("Success", data.name + " updated", "success");
        loadData();
      }).fail(function (errorThrown) {
        swal("Error", errorThrown.responseText, "error");
      });
    }
  });

  $(document).on('click', '.btn-delete', function () {
    $.ajax({
      url: "http://localhost:8080/client/" + sessionID,
      type: "DELETE",
    }).done(function (data) {
      sessionStorage.removeItem('id');
      window.location.replace('../home/Home.html');
    }).fail(function (errorThrown) {
      swal("Error", errorThrown.responseText, "error");
    });
  });

  loadData();
}