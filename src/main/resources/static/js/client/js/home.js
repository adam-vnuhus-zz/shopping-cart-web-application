$(document).ready(function() {

    $(".add-to-cart").on("click", function () {
        var dataCart = {};
        var product = $(this).attr("prodid");
        dataCart.amount = 1;
        dataCart.productId = product;
        // dataCart.guid = getCookie("guid");

        NProgress.start();
        var linkPost = "/api/carts";

        axios.post(linkPost, dataCart).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.replace("/cart/add");
                });
            } else {
                swal(
                    'You need to login!',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.replace("/login");
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

});