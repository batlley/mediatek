// aniamtion to remove alert-info messages
function fadeAwayAlertMessage() {
  $(".alert").fadeTo(2000, 500).slideUp(500, function(){
    $(".alert").alert('close');
  });
}

// add a product to shopping cart
$('.addProductToCart').click(function(event) {
  event.preventDefault();
  var ajax_url = $(this).attr('ajax-href');
  if(!ajax_url) {
    console.log("Oups, an error occured when adding a product to basket");
    return;
  }
  
  $.ajax(ajax_url, {
    success: function(data) {
      $('.navbar').after('<div class="alert alert-success">Product added!</div>');
      fadeAwayAlertMessage();
    },
    error: function(data) {
      $('.navbar').after('<div class="alert alert-danger">Oups! An error occured!</div>');
      fadeAwayAlertMessage();
    }
  });
});

