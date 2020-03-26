$(document).ready(function () {

    $(".change-product-amount").change(function () {
        dataCartProduct = {};
        dataCartProduct.id = $(this).attr("cartprodid");
        dataCartProduct.amount = $(this).val();


        NProgress.start();

        var linkPut = "/api/carts";

        axios.put(linkPut, dataCartProduct).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                location.reload();
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function () {
                    location.reload();
                });
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });
    $(".delete-cart-product").on("click", function () {
        var id = $(this).attr("cartprodid");
        NProgress.start();
        var linkDelete = "/api/carts/" + id;
        axios.delete(linkDelete).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    })


});