window.onload = function () {

    const loadData = function () {
      $.ajax({
        url: "http://localhost:8080/car",
        type: "GET",
      }).done(function (msg) {
        
      }).fail(function (errorThrown) {
        console.log(errorThrown);
      });
    }
  }