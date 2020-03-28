$(document).ready(function() {
    $("#btn-sign-in").on("click",function (){
        if($('#sign-in-email').val().trim() === '' || $('#sign-in-password').val().trim() === '') {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }

        var data = {
            email: $('#sign-in-email').val(),
            password: $('#sign-in-password').val()
        }
        axios.post("/api/authenticate", data).then(function(res){
            if(res.data.success) {
                swal(
                    'Done!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.replace("/frontend/client");
                });
            } else  {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        });
    });
});