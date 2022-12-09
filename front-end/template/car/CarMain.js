const sessionID = sessionStorage.getItem('id');
if (sessionID === null) {
  window.location.replace('../home/Home.html');
}
window.onload = function () {
  const loadData = function () {
    $.ajax({
      url: "http://localhost:8080/car/" + sessionID,
      type: "GET",
    }).done(function (data) {
      console.log(data);
    }).fail(function (errorThrown) {
      console.log(errorThrown);
    });
  }
  $(document).on('click', '.btn-logout', function (e) {
    e.preventDefault();
    sessionStorage.removeItem('id');
    window.location.replace('../home/Home.html');
  });

  loadData();
}